package com.hamza.profile.rest.application.controller;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * @author Pranavsinh Parmar
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/hamza-test-language",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=hamza-test-language.Rest"
	},
	service = Application.class
)
public class LanguageRestControllerApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/language/english/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEnglishBundle() {

		ResourceBundle bundle = LanguageUtil.getResourceBundleLoader().loadResourceBundle(Locale.US);
		Enumeration<String> keys = bundle.getKeys();
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if(key.startsWith("hamza")) {
				jsonObject.put(key, String.valueOf(bundle.getObject(key)));
			}
		}

		return jsonObject.toJSONString();
	}

	@GET
	@Path("/language/arabic/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getArabicBundle() {

		Locale arabicSaudi = new Locale.Builder()
				.setLanguage("ar")
				.setRegion("SA")
				.build();

		ResourceBundle bundle = LanguageUtil.getResourceBundleLoader().loadResourceBundle(arabicSaudi);
		Enumeration<String> keys = bundle.getKeys();
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if(key.startsWith("hamza")) {
				jsonObject.put(key, String.valueOf(bundle.getObject(key)));
			}
		}

		return jsonObject.toJSONString();
	}

}