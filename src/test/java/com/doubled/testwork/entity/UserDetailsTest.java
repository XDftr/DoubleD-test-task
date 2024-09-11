package com.doubled.testwork.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDetailsTest {
    @Test
    void testUserDetailsSettersAndGetters() {
        UserDetails userDetails = new UserDetails();

        userDetails.setUserId(1L);
        userDetails.setFirstName("John");
        userDetails.setLastName("Doe");
        userDetails.setBirthdate(LocalDate.parse("1990-01-01"));
        userDetails.setBirthplace("New York");
        userDetails.setSex("Male");
        userDetails.setCurrentAddress("123 Main St, New York, NY");

        assertEquals(1L, userDetails.getUserId());
        assertEquals("John", userDetails.getFirstName());
        assertEquals("Doe", userDetails.getLastName());
        assertEquals(LocalDate.parse("1990-01-01"), userDetails.getBirthdate());
        assertEquals("New York", userDetails.getBirthplace());
        assertEquals("Male", userDetails.getSex());
        assertEquals("123 Main St, New York, NY", userDetails.getCurrentAddress());
    }

}