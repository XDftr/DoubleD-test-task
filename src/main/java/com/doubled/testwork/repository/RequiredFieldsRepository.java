package com.doubled.testwork.repository;

import com.doubled.testwork.entity.RequiredFields;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequiredFieldsRepository extends JpaRepository<RequiredFields, Long> {
    Optional<RequiredFields> findByUrl(String url);
}
