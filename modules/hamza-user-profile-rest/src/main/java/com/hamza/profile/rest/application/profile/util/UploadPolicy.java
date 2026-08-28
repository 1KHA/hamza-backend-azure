package com.hamza.profile.rest.application.profile.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * Upload restrictions for the identity-document endpoints in this module.
 *
 * <p>Applied per endpoint rather than through the portal-wide
 * {@code upload.servlet.request.max.size} / {@code dl.file.extensions}
 * properties, so other sites on the same Liferay instance and the Documents and
 * Media library are unaffected.</p>
 *
 * <p>Mirrors the browser-side policy in {@code lib/upload-policy.ts}: the two
 * must be kept in step. The check here is the control — the front end can be
 * bypassed by posting directly to these endpoints.</p>
 *
 * <p>Extension and declared content type are both supplied by the caller, so
 * they are treated as hints; the file's leading bytes are checked against the
 * permitted formats as well, which is what stops an executable renamed to
 * {@code .pdf}.</p>
 *
 * @author Stockfish Technology
 */
public final class UploadPolicy {

	private static final Log LOG = LogFactoryUtil.getLog(UploadPolicy.class);

	public static final long MAX_UPLOAD_BYTES = 5L * 1024L * 1024L;

	private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(
		".pdf", ".png", ".jpg", ".jpeg");

	private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList(
		"application/pdf", "image/png", "image/jpeg");

	private UploadPolicy() {
	}

	/**
	 * Result of a check: either accepted, or rejected with a message suitable
	 * for returning to the caller.
	 */
	public static final class Result {

		public static Result accepted() {
			return new Result(true, null);
		}

		public static Result rejected(String message) {
			return new Result(false, message);
		}

		public String getMessage() {
			return _message;
		}

		public boolean isAccepted() {
			return _accepted;
		}

		private Result(boolean accepted, String message) {
			_accepted = accepted;
			_message = message;
		}

		private final boolean _accepted;
		private final String _message;

	}

	/**
	 * @param file        the uploaded file, as returned by UploadRequest
	 * @param fileName    the client-supplied file name
	 * @param contentType the client-supplied content type (may be null)
	 */
	public static Result check(File file, String fileName, String contentType) {
		if ((file == null) || !file.exists()) {
			return Result.rejected("No file provided");
		}

		if (Validator.isNull(fileName)) {
			return Result.rejected("No file name provided");
		}

		long length = file.length();

		if (length <= 0) {
			return Result.rejected("The file is empty");
		}

		if (length > MAX_UPLOAD_BYTES) {
			return Result.rejected(
				"File exceeds the maximum allowed size of " +
					(MAX_UPLOAD_BYTES / (1024 * 1024)) + " MB");
		}

		if (!ALLOWED_EXTENSIONS.contains(_extensionOf(fileName))) {
			return Result.rejected(
				"Unsupported file type — allowed formats are PDF, PNG, JPG");
		}

		if (Validator.isNotNull(contentType) &&
			!ALLOWED_CONTENT_TYPES.contains(
				contentType.toLowerCase().trim())) {

			return Result.rejected(
				"Unsupported file type — allowed formats are PDF, PNG, JPG");
		}

		if (!_hasAllowedSignature(file)) {
			return Result.rejected(
				"File content does not match the allowed formats");
		}

		return Result.accepted();
	}

	private static String _extensionOf(String fileName) {
		int index = fileName.lastIndexOf('.');

		if (index == -1) {
			return "";
		}

		return fileName.substring(index).toLowerCase();
	}

	/**
	 * Confirms the leading bytes are those of a PDF, PNG or JPEG.
	 */
	private static boolean _hasAllowedSignature(File file) {
		byte[] header = new byte[8];

		try (InputStream inputStream = new FileInputStream(file)) {
			int read = inputStream.read(header);

			if (read < 3) {
				return false;
			}
		}
		catch (Exception exception) {
			LOG.error("Unable to read uploaded file header", exception);

			return false;
		}

		// %PDF
		if (_startsWith(header, 0x25, 0x50, 0x44, 0x46)) {
			return true;
		}

		// PNG
		if (_startsWith(
				header, 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A)) {

			return true;
		}

		// JPEG (JFIF / Exif / raw)
		if (_startsWith(header, 0xFF, 0xD8, 0xFF)) {
			return true;
		}

		return false;
	}

	private static boolean _startsWith(byte[] header, int... signature) {
		if (header.length < signature.length) {
			return false;
		}

		for (int i = 0; i < signature.length; i++) {
			if ((header[i] & 0xFF) != signature[i]) {
				return false;
			}
		}

		return true;
	}

}
