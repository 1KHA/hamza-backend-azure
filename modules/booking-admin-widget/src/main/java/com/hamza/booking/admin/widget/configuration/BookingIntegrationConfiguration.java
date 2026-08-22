package com.hamza.booking.admin.widget.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * Configuration interface for the Booking Admin Widget integration with the
 * Liferay headless (Objects) APIs.
 *
 * <p>
 * Exposes the Liferay API base URL and the OAuth2 client-credentials used by
 * the booking scheduler and admin services, so they no longer need to be
 * hardcoded in source. Editable at runtime from
 * Control Panel &rarr; System Settings &rarr; Hamza.
 * </p>
 *
 * @author Hamza
 */
@ExtendedObjectClassDefinition(
    category = "hamza",
    scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
    id = "com.hamza.booking.admin.widget.configuration.BookingIntegrationConfiguration",
    name = "Booking Integration Configuration",
    description = "Liferay API base URL and OAuth2 client credentials used by the booking scheduler and admin services"
)
public interface BookingIntegrationConfiguration {

    @Meta.AD(
        deflt = "http://localhost:8080",
        description = "Base URL of the Liferay server used when calling the headless OAuth2 token and Objects APIs (scheme, host and port, no trailing slash)",
        name = "Liferay : API Base URL",
        required = false
    )
    public String liferayApiBaseUrl();

    @Meta.AD(
        deflt = "",
        description = "OAuth2 client_id (client-credentials grant) used to obtain an access token for the headless Objects APIs. Must match an OAuth2 application registered in Liferay.",
        name = "OAuth2 : Client ID",
        required = false
    )
    public String oauthClientId();

    @Meta.AD(
        deflt = "",
        description = "OAuth2 client_secret matching the Client ID above. Treat as a secret credential.",
        name = "OAuth2 : Client Secret",
        required = false
    )
    public String oauthClientSecret();

    @Meta.AD(
        deflt = "30",
        description = "How often the booking scheduler runs. Combined with the time unit below (e.g. 30 + SECOND = every 30 seconds). Must be greater than 0.",
        name = "Scheduler : Interval",
        required = false
    )
    public int schedulerInterval();

    @Meta.AD(
        deflt = "SECOND",
        description = "Time unit for the scheduler interval. Allowed values: SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR.",
        name = "Scheduler : Interval Time Unit",
        optionValues = {"SECOND", "MINUTE", "HOUR", "DAY", "WEEK", "MONTH", "YEAR"},
        optionLabels = {"Second(s)", "Minute(s)", "Hour(s)", "Day(s)", "Week(s)", "Month(s)", "Year(s)"},
        required = false
    )
    public String schedulerTimeUnit();

}
