package com.hamza.profile.rest.application.profile.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.template.StringTemplateResource;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.StringWriter;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.mail.internet.InternetAddress;

/**
 * Renders a FreeMarker ({@code .ftl}) email template bundled in this module's
 * {@code src/main/resources} into an HTML string, substituting the supplied
 * variables. Localization (ar/en) is handled inside the templates via a
 * {@code lang} variable, so transactional emails stay externalized from Java
 * and translatable without code edits to the bodies.
 *
 * @author Stockfish Technology
 */
public class HamzaEmailRenderer {

	/** Language-key constants for the transactional email subjects. */
	public static final String SUBJECT_ACCOUNT_ACTIVATION = "email.account-activation.subject";
	public static final String SUBJECT_ACCOUNT_EXISTS = "email.account-exists.subject";
	public static final String SUBJECT_OTP = "email.otp.subject";
	public static final String SUBJECT_PASSWORD_CHANGED = "email.password-changed.subject";
	public static final String SUBJECT_PASSWORD_RESET = "email.password-reset.subject";

	private static final String _RESOURCE_BUNDLE_BASE_NAME = "content.Language";

	/**
	 * Normalize a Liferay languageId to the template language switch: {@code "ar"}
	 * for Arabic, otherwise {@code "en"}. Centralized so the ar/en rule lives in
	 * one place (used both for the {@code lang} template variable and the subject
	 * locale).
	 */
	public static String lang(String languageId) {
		return ((languageId != null) && languageId.startsWith("ar")) ? "ar" : "en";
	}

	/**
	 * Resolve the email sender from Liferay's Instance Settings — the portal
	 * {@code admin.email.from.address} / {@code admin.email.from.name} (Control
	 * Panel &rarr; Instance Settings &rarr; Email), with any per-instance override.
	 * Transactional emails use this rather than a custom from-address config.
	 */
	public static InternetAddress instanceFromAddress(long companyId) throws Exception {
		String fromAddress = PrefsPropsUtil.getString(
				companyId, PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
		String fromName = PrefsPropsUtil.getString(
				companyId, PropsKeys.ADMIN_EMAIL_FROM_NAME);

		if (Validator.isNull(fromName)) {
			return new InternetAddress(fromAddress);
		}

		return new InternetAddress(fromAddress, fromName);
	}

	/**
	 * Resolve a localized message (e.g. an email subject) from this module's
	 * {@code content/Language[_ar].properties} resource bundle.
	 */
	public static String message(String languageId, String key) {
		Locale locale = LocaleUtil.fromLanguageId(lang(languageId));

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				_RESOURCE_BUNDLE_BASE_NAME, locale,
				HamzaEmailRenderer.class.getClassLoader());

		return LanguageUtil.get(resourceBundle, key);
	}

	/**
	 * @param resourcePath classpath-relative path to the template, e.g.
	 *                     {@code "templates/email/otp.ftl"} (no leading slash)
	 * @param variables    values exposed to the template (FreeMarker {@code ${...}})
	 * @return the rendered HTML
	 * @throws Exception if the template is missing or fails to render
	 */
	public static String render(String resourcePath, Map<String, Object> variables)
			throws Exception {

		String templateContent = StringUtil.read(
				HamzaEmailRenderer.class.getClassLoader(), resourcePath);

		if (templateContent == null) {
			throw new IllegalArgumentException(
					"Email template not found on classpath: " + resourcePath);
		}

		TemplateResource templateResource = new StringTemplateResource(
				resourcePath, templateContent);

		Template template = TemplateManagerUtil.getTemplate(
				TemplateConstants.LANG_TYPE_FTL, templateResource, false);

		template.putAll(variables);

		StringWriter stringWriter = new StringWriter();

		template.processTemplate(stringWriter);

		return stringWriter.toString();
	}

}
