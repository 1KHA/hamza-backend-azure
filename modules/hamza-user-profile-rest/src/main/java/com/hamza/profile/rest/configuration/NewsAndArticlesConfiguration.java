package com.hamza.profile.rest.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * Configuration interface for News and Articles module
 * 
 * @author Hamza
 */
@ExtendedObjectClassDefinition(
    category = "hamza",
    scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
    id = "com.hamza.profile.rest.configuration.NewsAndArticlesConfiguration",
    name = "News and Articles Configuration",
    description = "Configuration for news and articles pages"
)
public interface NewsAndArticlesConfiguration {

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "News And Articles : Banner Content",
        required = false
    )
    public long newsAndArticlesBannerContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "News And Articles : Event And Activities Content",
        required = false
    )
    public long newsAndArticlesEventAndActivitiesContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "News And Articles : News And Articles List Content",
        required = false
    )
    public long newsAndArticlesListContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "News And Articles : Latest News : Banner Content",
        required = false
    )
    public long newsAndArticlesLatestNewsBannerContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "News And Articles : Latest News : News And Articles Carousel Content",
        required = false
    )
    public long newsAndArticlesLatestNewsCarouselContentKey();

}
