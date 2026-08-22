package com.hamza.profile.rest.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Utility class for reading language information from HTTP headers
 * 
 * @author Hamza
 */
public class HeaderLanguageUtil {

    private static final Log LOG = LogFactoryUtil.getLog(HeaderLanguageUtil.class);
    private static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";
    private static final String DEFAULT_LANGUAGE = "en_US";

    /**
     * Get language ID from Accept-Language header
     * 
     * @param request the HTTP request
     * @return the language ID (e.g., "en_US", "ar_SA")
     */
    public static String getLanguageIdFromAcceptLanguage(HttpServletRequest request) {
        try {
            if (request == null) {
                LOG.warn("Request is null, returning default language: " + DEFAULT_LANGUAGE);
                return DEFAULT_LANGUAGE;
            }

            String acceptLanguage = request.getHeader(ACCEPT_LANGUAGE_HEADER);
            
            if (acceptLanguage == null || acceptLanguage.trim().isEmpty()) {
                LOG.info("Accept-Language header not found, returning default language: " + DEFAULT_LANGUAGE);
                return DEFAULT_LANGUAGE;
            }

            // Parse Accept-Language header (e.g., "en-US,en;q=0.9,ar;q=0.8")
            String primaryLanguage = parseAcceptLanguage(acceptLanguage);
            
            // Convert to Liferay format (e.g., "en-US" -> "en_US")
            String languageId = convertToLanguageId(primaryLanguage);
            
            LOG.info("Language ID from Accept-Language header: " + languageId);
            return languageId;
            
        } catch (Exception e) {
            LOG.error("Error reading language from Accept-Language header", e);
            return DEFAULT_LANGUAGE;
        }
    }

    /**
     * Parse Accept-Language header to get the primary language
     * 
     * @param acceptLanguage the Accept-Language header value
     * @return the primary language (e.g., "en-US", "ar-SA")
     */
    private static String parseAcceptLanguage(String acceptLanguage) {
        if (acceptLanguage == null || acceptLanguage.trim().isEmpty()) {
            return "en-US"; // Default fallback
        }

        // Split by comma and take the first language (highest priority)
        String[] languages = acceptLanguage.split(",");
        String primaryLanguage = languages[0].trim();
        
        // Remove quality values (e.g., "en-US;q=0.9" -> "en-US")
        if (primaryLanguage.contains(";")) {
            primaryLanguage = primaryLanguage.split(";")[0].trim();
        }
        
        return primaryLanguage;
    }

    /**
     * Convert language tag to Liferay language ID format
     * 
     * @param languageTag the language tag (e.g., "en-US", "ar-SA")
     * @return the language ID (e.g., "en_US", "ar_SA")
     */
    private static String convertToLanguageId(String languageTag) {
        if (languageTag == null || languageTag.trim().isEmpty()) {
            return DEFAULT_LANGUAGE;
        }

        // Handle common language mappings
        switch (languageTag.toLowerCase()) {
            case "en":
            case "en-us":
                return "en_US";
            case "ar":
            case "ar-sa":
                return "ar_SA";
            case "fr":
            case "fr-fr":
                return "fr_FR";
            case "es":
            case "es-es":
                return "es_ES";
            case "de":
            case "de-de":
                return "de_DE";
            default:
                // Convert format: "en-US" -> "en_US"
                return languageTag.replace("-", "_");
        }
    }

    /**
     * Get language ID from Accept-Language header with fallback to default locale
     * 
     * @param request the HTTP request
     * @param defaultLocale the default locale to use if header is not available
     * @return the language ID
     */
    public static String getLanguageIdFromAcceptLanguage(HttpServletRequest request, Locale defaultLocale) {
        String languageId = getLanguageIdFromAcceptLanguage(request);
        
        // If we got the default and a specific default locale was provided, use it
        if (DEFAULT_LANGUAGE.equals(languageId) && defaultLocale != null) {
            return convertLocaleToLanguageId(defaultLocale);
        }
        
        return languageId;
    }

    /**
     * Convert Locale to language ID format
     * 
     * @param locale the locale
     * @return the language ID
     */
    private static String convertLocaleToLanguageId(Locale locale) {
        if (locale == null) {
            return DEFAULT_LANGUAGE;
        }
        
        String language = locale.getLanguage();
        String country = locale.getCountry();
        
        if (country != null && !country.isEmpty()) {
            return language + "_" + country;
        }
        
        return language + "_" + language.toUpperCase();
    }
}
