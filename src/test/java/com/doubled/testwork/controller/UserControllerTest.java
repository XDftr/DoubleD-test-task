package com.doubled.testwork.controller;

import com.doubled.testwork.client.ExternalServiceClient;
import com.doubled.testwork.dto.UserDetailsSubmissionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExternalServiceClient externalServiceClient;

    @Test
    void getMissingFields() throws Exception {
        mockMvc.perform(get("/api/users/1/missing-fields?requiredId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.missingFields").isArray())
                .andExpect(jsonPath("$.missingFields[0]").value("birthdate"))
                .andExpect(jsonPath("$.missingFields[1]").value("birthplace"))
                .andExpect(jsonPath("$.missingFields[2]").value("sex"))
                .andExpect(jsonPath("$.missingFields[3]").value("currentAddress"));
    }

    @Test
    void submitUserDetails() throws Exception {
        UserDetailsSubmissionRequest request = new UserDetailsSubmissionRequest(
                "John",
                "Smith",
                LocalDate.of(2024, 1, 1),
                "Tallinn",
                "Male",
                "Tallinn, Estonia"
        );

        doNothing().when(externalServiceClient).callExternalService("https://example.com/", request);

        mockMvc.perform(post("/api/users/1/submit?requiredId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}