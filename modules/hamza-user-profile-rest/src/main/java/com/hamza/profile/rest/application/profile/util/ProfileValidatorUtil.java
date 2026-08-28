package com.hamza.profile.rest.application.profile.util;


import com.hamza.profile.rest.application.profile.enums.ErrorCode;
import com.hamza.profile.rest.application.profile.enums.Status;
import com.hamza.profile.rest.application.profile.modal.CreateProfileRequest;
import com.hamza.profile.rest.application.profile.modal.CreateProfileResponse;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.util.tracker.ServiceTracker;

import java.io.File;
import java.time.LocalDate;

public class ProfileValidatorUtil {

    private static final Log LOG = LogFactoryUtil.getLog(ProfileValidatorUtil.class);

    private static ServiceTracker<UserLocalService, UserLocalService> userLocalServiceServiceTracker = ServiceTrackerFactory.open(UserLocalService.class);


    public static boolean validateCreateProfileRequest(CreateProfileRequest request, CreateProfileResponse response, UploadRequest uploadRequest) {

        if (ProfileValidatorUtil.validateRequestPayload(request, response, false)) {
           return true;
        }

        // NOTE: an already-registered e-mail is intentionally NOT rejected here.
        // Doing so would reveal which addresses have accounts (enumeration). The
        // sign-up handler instead returns the same generic success response and
        // e-mails the existing user a heads-up. Existence is checked via
        // isExistingUser() at the handler, not surfaced as a validation error.

        if (uploadRequest instanceof UploadRequest) {
            File file = uploadRequest.getFile("file");

            if (file == null || !file.exists()) {
                response.setCode(ErrorCode.NO_PROOF_FILE_FOUND.name());
                response.setMessage(ErrorCode.NO_PROOF_FILE_FOUND.getMessage());
                response.setStatus(Status.FAIL.name());

                // Returning true is what makes the caller send the error
                // response; without it the sign-up proceeded despite the
                // failure being recorded here.
                return true;
            }

            // Size / type / content restrictions, scoped to this endpoint so
            // other sites on this Liferay instance are unaffected.
            UploadPolicy.Result uploadCheck = UploadPolicy.check(
                    file,
                    uploadRequest.getFileName("file"),
                    uploadRequest.getContentType("file"));

            if (!uploadCheck.isAccepted()) {
                LOG.warn("Rejected sign-up ID proof upload: " + uploadCheck.getMessage());

                response.setCode(ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT.name());
                response.setMessage(uploadCheck.getMessage());
                response.setStatus(Status.FAIL.name());

                return true;
            }
        }

       

        return false;
    }

    public static boolean isExistingUser(String emailId) {
        User user = userLocalServiceServiceTracker.getService().
                fetchUserByEmailAddress(PortalUtil.getDefaultCompanyId(), emailId);
        return user != null;
    }

    public static boolean validateRequestPayload(CreateProfileRequest request, CreateProfileResponse response, boolean isUpdateRequest) {

        // Helper method for validation with common logic
        class ValidatorHelper {
            boolean validateField(boolean condition, String errorMessage) {
                if (condition) {
                    LOG.error(errorMessage);
                    response.setMessage(errorMessage);
                    response.setCode(ErrorCode.MANDATORY_FIELD_MISSING.name());
                    response.setStatus(Status.FAIL.name());
                    return true;
                }
                return false;
            }
        }

        ValidatorHelper validator = new ValidatorHelper();

        if (validator.validateField(!Validator.isEmailAddress(request.getEmailId()), "Invalid email Id provided")) return true;
        if (validator.validateField(Validator.isBlank(request.getFirstName()), "First name is blank")) return true;
        if (validator.validateField(Validator.isBlank(request.getSecondName()), "Second name is blank")) return true;
        if (validator.validateField(Validator.isBlank(request.getLastName()), "Last name is blank")) return true;
        if (validator.validateField(Validator.isBlank(request.getFirstNameInEnglish()), "First name in english is blank")) return true;
        if (validator.validateField(Validator.isBlank(request.getSecondNameInEnglish()), "Second name in english is blank")) return true;
        if (validator.validateField(Validator.isBlank(request.getLastNameInEnglish()), "Last name in english is blank")) return true;
        if (validator.validateField(Validator.isBlank(request.getEmailId()), "Email ID is blank")) return true;
        if (!isUpdateRequest) {
            if (validator.validateField(Validator.isBlank(request.getPassword()), "Password is blank")) return true;
        }
        if (validator.validateField(Validator.isBlank(request.getPhoneNumber()), "Phone number is blank")) return true;

        boolean invalidDOB = request.getDayOfBirth() <= 0
                || request.getMonthOfBirth() <= 0
                || request.getYearOfBirth() <= 0;
        if (validator.validateField(invalidDOB, "Invalid date of birth")) return true;

        if (validator.validateField(Validator.isBlank(request.getNationality()), "Nationality is blank")) return true;
        if (validator.validateField(Validator.isBlank(request.getMotherTongue()), "Mother tongue is blank")) return true;

        LocalDate date = LocalDate.of(request.getYearOfBirth(), request.getMonthOfBirth(), request.getDayOfBirth());
        LocalDate today = LocalDate.now();

        if (validator.validateField(!date.isBefore(today), "Given birth date is in future")) return true;

        // Add more validations similarly

        return false;
    }

}
