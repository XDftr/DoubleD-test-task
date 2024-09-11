package com.doubled.testwork.client;

import com.doubled.testwork.dto.UserDetailsSubmissionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "external-service-client")
public interface ExternalServiceClient {

    @PostMapping("/process")
    void callExternalService(@RequestParam("url") String url, @RequestBody UserDetailsSubmissionRequest userDetails);
}
