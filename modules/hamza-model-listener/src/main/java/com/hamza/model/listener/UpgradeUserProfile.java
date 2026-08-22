package com.hamza.model.listener;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

public class UpgradeUserProfile extends UpgradeProcess {

    @Override
    protected void doUpgrade() throws Exception {
        alterTableAddColumn("HAMZA_UserProfile", "university", "VARCHAR(75) null");
    }
}
