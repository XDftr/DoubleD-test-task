package com.doubled.testwork.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequiredFieldsTest {
    @Test
    void testRequiredFieldsSettersAndGetters() {
        RequiredFields requiredFields = new RequiredFields();

        requiredFields.setRequiredId(1L);
        requiredFields.setUrl("https://example.com");
        requiredFields.setFirstName(true);
        requiredFields.setLastName(true);
        requiredFields.setBirthdate(false);
        requiredFields.setBirthplace(true);
        requiredFields.setSex(true);
        requiredFields.setCurrentAddress(false);

        assertEquals(1L, requiredFields.getRequiredId());
        assertEquals("https://example.com", requiredFields.getUrl());
        assertTrue(requiredFields.isFirstName());
        assertTrue(requiredFields.isLastName());
        assertFalse(requiredFields.isBirthdate());
        assertTrue(requiredFields.isBirthplace());
        assertTrue(requiredFields.isSex());
        assertFalse(requiredFields.isCurrentAddress());
    }
}