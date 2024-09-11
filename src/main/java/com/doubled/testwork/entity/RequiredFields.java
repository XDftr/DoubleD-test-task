package com.doubled.testwork.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RequiredFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requiredId;
    private String url;
    private boolean firstName;
    private boolean lastName;
    private boolean birthdate;
    private boolean birthplace;
    private boolean sex;
    private boolean currentAddress;
}
