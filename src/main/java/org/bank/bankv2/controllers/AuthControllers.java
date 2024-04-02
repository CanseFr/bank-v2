package org.bank.bankv2.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.bankv2.web_service.models.authentication.UserAuth;
import org.bank.bankv2.web_service.services.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthControllers {

    private final AuthenticationService authenticationService;

    @PostMapping("/")
    public ResponseEntity<?> login(@RequestBody UserAuth userAuth) throws IOException {
        String authenticationResult = authenticationService.authenticate(userAuth);
        if (authenticationResult.length() > 6) {
            String token = authenticationResult.toString().split(":")[1];
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("accessToken", "Bearer " + token);
            return ResponseEntity.ok().headers(responseHeaders).build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
