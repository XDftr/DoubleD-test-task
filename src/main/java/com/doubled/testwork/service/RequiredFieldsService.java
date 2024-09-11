package com.doubled.testwork.service;

import com.doubled.testwork.entity.RequiredFields;
import com.doubled.testwork.exception.UrlNotFoundException;
import com.doubled.testwork.repository.RequiredFieldsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequiredFieldsService {
    private final RequiredFieldsRepository requiredFieldsRepository;

    /**
     * Retrieves the required fields based on the provided ID.
     *
     * @param requiredId the ID of the required fields to retrieve
     * @return the RequiredFields object containing the required fields information
     * @throws UrlNotFoundException if no required fields are found with the specified ID
     */
    public RequiredFields findRequiredFieldsById(Long requiredId) {
        return requiredFieldsRepository.findById(requiredId).orElseThrow(
                () -> new UrlNotFoundException("No required fields with ID " + requiredId)
        );
    }
}
