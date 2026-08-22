package com.hamza.booking.admin.widget.service.internal;

import com.hamza.booking.admin.widget.configuration.BookingIntegrationConfigurationReader;
import com.hamza.booking.admin.widget.service.OAuthTokenService;
import com.hamza.booking.admin.widget.service.TestCenterService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Default implementation of {@link TestCenterService} that calls the external
 * headless endpoint using Liferay's {@link Http} utility.
 *
 * <p>
 * This class acts as a concrete implementation behind the {@code TestCenterService}
 * facade, keeping HTTP and authorization details isolated from the portlet.
 * </p>
 */
@Component(
	immediate = true,
	service = TestCenterService.class
)
public class TestCenterServiceImpl implements TestCenterService {

	@Override
	public String fetchAllTestCenters() throws Exception {
		Http.Options options = new Http.Options();

		String url = _configurationReader.getTestCentersUrl();

		options.setLocation(url);
		options.setMethod(Http.Method.GET);

		String accessToken = _oAuthTokenService.getAccessToken();

		options.addHeader("Authorization", "Bearer " + accessToken);
		options.addHeader("Accept-Language", "en-US");

		if (_log.isDebugEnabled()) {
			_log.debug("Calling test centers endpoint: " + url);
		}

		return _http.URLtoString(options);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TestCenterServiceImpl.class);

	@Reference
	private BookingIntegrationConfigurationReader _configurationReader;

	@Reference
	private OAuthTokenService _oAuthTokenService;

	@Reference
	private Http _http;

}

