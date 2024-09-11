package com.doubled.testwork.dto;

import java.time.LocalDate;

public record UserDetailsSubmissionRequest(String firstName,
                                           String lastName,
                                           LocalDate birthdate,
                                           String birthplace,
                                           String sex,
                                           String currentAddress) {
}
