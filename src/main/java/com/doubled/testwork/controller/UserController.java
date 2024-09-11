package com.doubled.testwork.controller;

import com.doubled.testwork.dto.MissingFieldsResponse;
import com.doubled.testwork.dto.UserDetailsSubmissionRequest;
import com.doubled.testwork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}/missing-fields")
    public MissingFieldsResponse getMissingFields(@PathVariable Long userId, @RequestParam Long requiredId) {
        return userService.getMissingFields(userId, requiredId);
    }

    @PostMapping("/{userId}/submit")
    public void submitUserDetails(@PathVariable Long userId,
                                  @RequestParam Long requiredId,
                                  @RequestBody UserDetailsSubmissionRequest request) {
        userService.submitUserDetails(userId, requiredId, request);
    }
}
