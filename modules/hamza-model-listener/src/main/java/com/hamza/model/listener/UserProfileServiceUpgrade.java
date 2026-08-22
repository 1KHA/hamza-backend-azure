package com.hamza.model.listener;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class UserProfileServiceUpgrade implements UpgradeStepRegistrator {
    @Override
    public void register(Registry registry) {
        registry.register("0.0.0", "1.0.0", new UpgradeUserProfile());
    }
}
