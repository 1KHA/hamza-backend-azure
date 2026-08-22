package com.hamza.profile.rest.application.profile.util;

import com.hamza.profile.rest.application.profile.modal.CreateProfileRequest;
import com.hamza.service.model.UserProfile;
import com.hamza.service.model.UserProfileAddress;
import com.hamza.service.service.UserProfileAddressLocalService;
import com.hamza.service.service.UserProfileLocalService;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.Locale;

public class CreateProfileUtil {

    private static final Log LOG = LogFactoryUtil.getLog(CreateProfileUtil.class);

    private static ServiceTracker<UserLocalService, UserLocalService> userLocalServiceServiceTracker = ServiceTrackerFactory.open(UserLocalService.class);
    private static ServiceTracker<CounterLocalService, CounterLocalService> counterLocalServiceTracker = ServiceTrackerFactory.open(CounterLocalService.class);
    private static ServiceTracker<UserProfileLocalService, UserProfileLocalService> userProfileLocalServiceTracker = ServiceTrackerFactory.open(UserProfileLocalService.class);
    private static ServiceTracker<UserProfileAddressLocalService, UserProfileAddressLocalService> userProfileAddressLocalServiceTracker = ServiceTrackerFactory.open(UserProfileAddressLocalService.class);

    public static User createLiferayUser(CreateProfileRequest request, long companyId) {

        long creatorUserId = 0;
        String emailAddress = request.getEmailId();
        String screenName = request.getEmailId();
        String firstName = request.getFirstName();
        String middleName = request.getSecondName();
        String lastName = request.getLastName();
        boolean autoPassword = false;
        String password1 = request.getPassword();
        String password2 = request.getPassword();
        boolean autoScreenName = true;
        Locale locale = LocaleUtil.getDefault();
        long prefixId = 0;
        long suffixId = 0;
        int type = 1;
        boolean male = true;
        int birthdayMonth = (request.getMonthOfBirth() - 1);
        int birthdayDay = request.getDayOfBirth();
        int birthdayYear = request.getYearOfBirth();
        String jobTitle = StringPool.BLANK;
        long[] groupIds = null;
        long[] organizationIds = null;
        long[] roleIds = null;
        long[] userGroupIds = null;
        boolean sendEmail = false;

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setCompanyId(companyId);

        try {
            User user = userLocalServiceServiceTracker.getService().addUser(creatorUserId, companyId, autoPassword,
                    password1, password2, autoScreenName, screenName, emailAddress, locale, firstName, middleName, lastName,prefixId, suffixId, male,
                    birthdayMonth, birthdayDay, birthdayYear,
                    jobTitle, type, groupIds, organizationIds,
                    roleIds, userGroupIds, sendEmail,
                    serviceContext);

            // The OAuth2 password grant (ROPC) used at sign-in runs the full login
            // pipeline, which rejects users who are flagged for a forced password
            // reset, have not agreed to the terms of use, or whose email address is
            // unverified — returning invalid_grant even with a correct password
            // (Basic auth, used by /login, does a lighter check and lets them
            // through). Clear all three so the new account can authenticate.
            UserLocalService userLocalService = userLocalServiceServiceTracker.getService();
            user = userLocalService.updatePasswordReset(user.getUserId(), false);
            user = userLocalService.updateAgreedToTermsOfUse(user.getUserId(), true);
            user = userLocalService.updateEmailAddressVerified(user.getUserId(), true);

            LOG.error("Basic user created successfully: " + user.getEmailAddress());
            return user;

        } catch (PortalException e) {
            LOG.error("Error creating basic user: " + emailAddress, e);
        }

        return null;
    }

    public static UserProfile createUserProfile(CreateProfileRequest profileRequest, User user, long fileEntryId, long groupId) throws PortalException {

        UserProfile profile = userProfileLocalServiceTracker.getService().
                createUserProfile(counterLocalServiceTracker.getService().increment(UserProfile.class.getName()));

        profile.setGroupId(groupId);
        profile.setUserId(user.getUserId());
        profile.setUserName(user.getFirstName());
        profile.setCompanyId(user.getCompanyId());

        Date now = new Date();
        profile.setCreateDate(now);
        profile.setModifiedDate(now);

        profile.setFirstName(profileRequest.getFirstName());
        profile.setSecondName(profileRequest.getSecondName());
        profile.setLastName(profileRequest.getLastName());
        profile.setFirstNameInEnglish(profileRequest.getFirstNameInEnglish());
        profile.setSecondNameInEnglish(profileRequest.getSecondNameInEnglish());
        profile.setLastNameInEnglish(profileRequest.getLastNameInEnglish());
        profile.setBirthDate(user.getBirthday());
        profile.setPhoneExtension(profileRequest.getPhoneExtension());
        profile.setPhoneNumber(profileRequest.getPhoneNumber());
        profile.setNationality(profileRequest.getNationality());
        profile.setMotherTongue(profileRequest.getMotherTongue());
        profile.setProofName(profileRequest.getProofName());
        profile.setProofNumber(profileRequest.getPassportNumber());
        profile.setUniversity(profileRequest.getUniversity());
        profile.setLastEducationalQualification(profileRequest.getLastEducationalQualification());
        profile.setAcademicSpecialization(profileRequest.getAcademicSpecialization());
        profile.setPrimaryLanguageEducation(profileRequest.getPrimaryLanguageOfEducation());
        profile.setTimeZone(profileRequest.getTimeZone());
        profile.setTermsAccepted(true);
        profile.setFileEntryId(fileEntryId);

        profile = userProfileLocalServiceTracker.getService().updateUserProfile(profile);

        return profile;
    }

    public  static UserProfileAddress createUserProfileAddress(CreateProfileRequest profileRequest, UserProfile userProfile, User user) {

        UserProfileAddress userProfileAddress = userProfileAddressLocalServiceTracker.getService().
                createUserProfileAddress(counterLocalServiceTracker.getService().increment(UserProfileAddress.class.getName()));

        userProfileAddress.setProfileId(userProfile.getProfileId());
        userProfileAddress.setUserId(user.getUserId());

        Date now = new Date();
        userProfileAddress.setCreateDate(now);
        userProfileAddress.setModifiedDate(now);

        userProfileAddress.setCountry(profileRequest.getCountry());
        userProfileAddress.setState(profileRequest.getState());
        userProfileAddress.setProvince(profileRequest.getProvince());
        userProfileAddress.setCity(profileRequest.getCity());
        userProfileAddress.setStreet(profileRequest.getStreet());
        userProfileAddress.setPostalCode(profileRequest.getPostalCode());

        userProfileAddress = userProfileAddressLocalServiceTracker.getService().updateUserProfileAddress(userProfileAddress);

        return userProfileAddress;
    }
}
