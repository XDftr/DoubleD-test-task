package com.doubled.testwork.dto;

import java.util.List;

public record MissingFieldsResponse (List<String> missingFields) {
}
