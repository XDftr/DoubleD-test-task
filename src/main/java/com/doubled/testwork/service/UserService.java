package com.doubled.testwork.service;

import com.doubled.testwork.client.ExternalServiceClient;
import com.doubled.testwork.dto.MissingFieldsResponse;
import com.doubled.testwork.dto.UserDetailsSubmissionRequest;
import com.doubled.testwork.entity.RequiredFields;
import com.doubled.testwork.entity.UserDetails;
import com.doubled.testwork.exception.MissingFieldException;
import com.doubled.testwork.exception.UserNotFoundException;
import com.doubled.testwork.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RequiredFieldsService requiredFieldsService;

    private final UserDetailsRepository userDetailsRepository;

    private final ExternalServiceClient externalServiceClient;

    /**
     * Determines which required fields for a user are missing based on a given set of required fields.
     *
     * @param userId the ID of the user for whom to check the missing fields
     * @param requiredId the ID of the set of required fields to check against
     * @return a MissingFieldsResponse containing a list of the missing fields
     */
    public MissingFieldsResponse getMissingFields(Long userId, Long requiredId) {
        List<String> missingFields = new ArrayList<>();

        UserDetails userDetails = getUserDetails(userId);

        RequiredFields requiredFields = requiredFieldsService.findRequiredFieldsById(requiredId);

        if (requiredFields.isFirstName() && userDetails.getFirstName() == null) {
            missingFields.add("firstName");
        }

        if (requiredFields.isLastName() && userDetails.getLastName() == null) {
            missingFields.add("lastName");
        }

        if (requiredFields.isBirthdate() && userDetails.getBirthdate() == null) {
            missingFields.add("birthdate");
        }

        if (requiredFields.isBirthplace() && userDetails.getBirthplace() == null) {
            missingFields.add("birthplace");
        }

        if (requiredFields.isSex() && userDetails.getSex() == null) {
            missingFields.add("sex");
        }

        if (requiredFields.isCurrentAddress() && userDetails.getCurrentAddress() == null) {
            missingFields.add("currentAddress");
        }

        return new MissingFieldsResponse(missingFields);
    }

    /**
     * Submits the user details after validating each required field.
     *
     * @param userId the ID of the user whose details are to be submitted
     * @param requiredId the ID of the required fields configuration
     * @param request the request object containing the details to be submitted
     */
    public void submitUserDetails(Long userId, Long requiredId, UserDetailsSubmissionRequest request) {
        UserDetails userDetails = getUserDetails(userId);
        RequiredFields requiredFields = requiredFieldsService.findRequiredFieldsById(requiredId);

        String firstName = validateField(requiredFields.isFirstName(), request.firstName(),
                userDetails.getFirstName(), "firstName");
        String lastName = validateField(requiredFields.isLastName(), request.lastName(),
                userDetails.getLastName(), "lastName");
        LocalDate birthdate = validateField(requiredFields.isBirthdate(), request.birthdate(),
                userDetails.getBirthdate(), "birthdate");
        String birthplace = validateField(requiredFields.isBirthplace(), request.birthplace(),
                userDetails.getBirthplace(), "birthplace");
        String sex = validateField(requiredFields.isSex(), request.sex(), userDetails.getSex(), "sex");
        String currentAddress = validateField(requiredFields.isCurrentAddress(), request.currentAddress(),
                userDetails.getCurrentAddress(), "currentAddress");

        UserDetailsSubmissionRequest validatedUserDetails = new UserDetailsSubmissionRequest(
                firstName,
                lastName,
                birthdate,
                birthplace,
                sex,
                currentAddress);

        externalServiceClient.callExternalService(requiredFields.getUrl(), validatedUserDetails);
    }

    /**
     * Retrieves the details of a user based on the provided user ID.
     *
     * @param userId the ID of the user whose details are to be retrieved
     * @return the UserDetails object containing the user's details
     * @throws UserNotFoundException if no user is found with the specified ID
     */
    private UserDetails getUserDetails(Long userId) {
        return userDetailsRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with id " + userId + " not found")
        );
    }

    /**
     * Validates a field based on its requirement status, the provided request value, and the stored value.
     *
     * @param <T> The type of the field being validated.
     * @param isRequired A boolean indicating whether the field is required.
     * @param requestValue The value of the field from the request.
     * @param storedValue The existing stored value of the field.
     * @param fieldName The name of the field being validated.
     * @return The validated field value.
     * @throws MissingFieldException if the field is required and both requestValue and storedValue are null.
     */
    private <T> T validateField(boolean isRequired, T requestValue, T storedValue, String fieldName) {
        if (isRequired) {
            if (requestValue != null) {
                return requestValue;
            } else if (storedValue != null) {
                return storedValue;
            } else {
                throw new MissingFieldException("Missing required field: " + fieldName);
            }
        }
        return null;
    }
}
