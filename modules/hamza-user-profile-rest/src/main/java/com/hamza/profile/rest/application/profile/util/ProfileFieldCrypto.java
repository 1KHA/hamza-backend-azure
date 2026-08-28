package com.hamza.profile.rest.application.profile.util;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * Reversible encryption for personal-data fields stored in the profile tables
 * (currently the identity/passport number).
 *
 * <p>The value is encrypted before it is written to the database and decrypted
 * when it is read back, so users and administrators continue to see and edit
 * the number in plain text while the stored column holds ciphertext. This
 * protects the data against database-level exposure — a dump, a backup, or
 * direct table access — not against anyone authorised to use the application.</p>
 *
 * <p><b>Key management.</b> The key is read from the portal property
 * {@code hamza.profile.encryption.key}, falling back to the environment
 * variable {@code HAMZA_PROFILE_ENCRYPTION_KEY}. It must be a Base64-encoded
 * 32-byte (256-bit) value and must be stored outside the database and backed up
 * separately — if it is lost the stored values cannot be recovered. Generate one
 * with:</p>
 *
 * <pre>openssl rand -base64 32</pre>
 *
 * <p>If no key is configured the value is stored unencrypted and an error is
 * logged on every call, so a misconfigured environment is obvious rather than
 * silently insecure.</p>
 *
 * <p><b>Format.</b> {@code enc:v1:} + Base64(IV ‖ ciphertext ‖ GCM tag). The
 * prefix lets {@link #decrypt(String)} pass through values written before
 * encryption was enabled, so existing rows keep working and are converted the
 * next time the profile is saved.</p>
 *
 * @author Stockfish Technology
 */
public final class ProfileFieldCrypto {

	private static final Log LOG = LogFactoryUtil.getLog(ProfileFieldCrypto.class);

	private static final String PROPERTY_NAME = "hamza.profile.encryption.key";
	private static final String ENV_NAME = "HAMZA_PROFILE_ENCRYPTION_KEY";

	private static final String PREFIX = "enc:v1:";
	private static final String TRANSFORMATION = "AES/GCM/NoPadding";
	private static final int GCM_IV_BYTES = 12;
	private static final int GCM_TAG_BITS = 128;
	private static final int KEY_BYTES = 32;

	/**
	 * Width of the Hamza_UserProfile.proofNumber column. Encrypting a value that
	 * would exceed it must fail rather than be silently truncated by the driver.
	 */
	private static final int MAX_STORED_LENGTH = 75;

	private static final SecureRandom RANDOM = new SecureRandom();

	private ProfileFieldCrypto() {
	}

	/**
	 * Encrypts a value for storage. Null and blank values are returned
	 * unchanged, as is any value that is already encrypted.
	 *
	 * @throws IllegalStateException if the ciphertext would not fit the column
	 */
	public static String encrypt(String plainText) {
		if (Validator.isNull(plainText) || isEncrypted(plainText)) {
			return plainText;
		}

		byte[] key = readKey();

		if (key == null) {
			LOG.error(
				"No encryption key configured (" + PROPERTY_NAME + " / " + ENV_NAME +
					") — the identity number is being stored WITHOUT encryption");

			return plainText;
		}

		try {
			byte[] iv = new byte[GCM_IV_BYTES];
			RANDOM.nextBytes(iv);

			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(
				Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"),
				new GCMParameterSpec(GCM_TAG_BITS, iv));

			byte[] cipherText = cipher.doFinal(
				plainText.getBytes(StandardCharsets.UTF_8));

			byte[] combined = new byte[iv.length + cipherText.length];
			System.arraycopy(iv, 0, combined, 0, iv.length);
			System.arraycopy(cipherText, 0, combined, iv.length, cipherText.length);

			String encoded = PREFIX + Base64.getEncoder().encodeToString(combined);

			if (encoded.length() > MAX_STORED_LENGTH) {
				throw new IllegalStateException(
					"Encrypted identity number is " + encoded.length() +
						" characters but the column holds " + MAX_STORED_LENGTH +
						" — widen Hamza_UserProfile.proofNumber before storing values this long");
			}

			return encoded;
		}
		catch (IllegalStateException illegalStateException) {
			throw illegalStateException;
		}
		catch (Exception exception) {
			LOG.error("Failed to encrypt identity number", exception);

			throw new IllegalStateException(
				"Unable to encrypt identity number", exception);
		}
	}

	/**
	 * Decrypts a stored value. Values without the encryption prefix are returned
	 * as-is, so rows written before encryption was enabled still read correctly.
	 */
	public static String decrypt(String storedValue) {
		if (Validator.isNull(storedValue) || !isEncrypted(storedValue)) {
			return storedValue;
		}

		byte[] key = readKey();

		if (key == null) {
			LOG.error(
				"Stored identity number is encrypted but no key is configured (" +
					PROPERTY_NAME + " / " + ENV_NAME + ") — cannot decrypt");

			return null;
		}

		try {
			byte[] combined = Base64.getDecoder().decode(
				storedValue.substring(PREFIX.length()));

			byte[] iv = new byte[GCM_IV_BYTES];
			System.arraycopy(combined, 0, iv, 0, GCM_IV_BYTES);

			byte[] cipherText = new byte[combined.length - GCM_IV_BYTES];
			System.arraycopy(
				combined, GCM_IV_BYTES, cipherText, 0, cipherText.length);

			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(
				Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"),
				new GCMParameterSpec(GCM_TAG_BITS, iv));

			return new String(
				cipher.doFinal(cipherText), StandardCharsets.UTF_8);
		}
		catch (Exception exception) {
			// Never log the value itself. A failure here usually means the key
			// changed, or the stored bytes were altered (GCM authentication).
			LOG.error(
				"Failed to decrypt identity number — check that the encryption " +
					"key matches the one used when the value was stored",
				exception);

			return null;
		}
	}

	public static boolean isEncrypted(String value) {
		return (value != null) && value.startsWith(PREFIX);
	}

	/**
	 * @return the configured 32-byte key, or {@code null} when unset or invalid
	 */
	private static byte[] readKey() {
		String configured = PropsUtil.get(PROPERTY_NAME);

		if (Validator.isNull(configured)) {
			configured = System.getenv(ENV_NAME);
		}

		if (Validator.isNull(configured)) {
			return null;
		}

		try {
			byte[] key = Base64.getDecoder().decode(configured.trim());

			if (key.length != KEY_BYTES) {
				LOG.error(
					"Encryption key must decode to " + KEY_BYTES + " bytes but is " +
						key.length + " — generate one with: openssl rand -base64 32");

				return null;
			}

			return key;
		}
		catch (IllegalArgumentException illegalArgumentException) {
			LOG.error(
				"Encryption key is not valid Base64 — generate one with: " +
					"openssl rand -base64 32");

			return null;
		}
	}

}
