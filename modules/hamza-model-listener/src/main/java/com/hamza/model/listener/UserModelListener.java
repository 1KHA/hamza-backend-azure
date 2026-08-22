package com.hamza.model.listener;


import com.hamza.service.exception.NoSuchUserProfileAddressException;
import com.hamza.service.exception.NoSuchUserProfileException;
import com.hamza.service.model.UserProfile;
import com.hamza.service.model.UserProfileAddress;
import com.hamza.service.service.UserProfileAddressLocalService;
import com.hamza.service.service.UserProfileLocalService;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pranavsinh Parmar
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends BaseModelListener<User> {

	private static final Log LOG = LogFactoryUtil.getLog(UserModelListener.class);

	@Reference
	private DLAppService dlAppService;

	@Reference
	private UserProfileLocalService userProfileLocalService;

	@Reference
	private UserProfileAddressLocalService userProfileAddressLocalService;

	@Override
	public void onAfterRemove(User model) throws ModelListenerException {
		// Called after a User is removed
        try {
			UserProfileAddress userProfileAddress = userProfileAddressLocalService.
					findByUserId(model.getUserId());
			UserProfile userProfile = userProfileLocalService.findByUserId(model.getUserId());

			userProfileAddressLocalService.deleteUserProfileAddress(userProfileAddress.getAddressId());
			userProfileLocalService.deleteUserProfile(userProfile.getProfileId());

			dlAppService.deleteFileEntry(userProfile.getFileEntryId());


			LOG.error("User profile and address has been deleted for user: " + model.getEmailAddress());

        } catch (NoSuchUserProfileAddressException e) {
            LOG.error("User profile address not found while deletion. Message :" + e.getMessage(), e);
        } catch (NoSuchUserProfileException e) {
			LOG.error("User profile not found while deletion. Message :" + e.getMessage(), e);
        } catch (PortalException e) {
            LOG.error("No file found while deleting user :" + model.getEmailAddress(), e);
        }
    }

}