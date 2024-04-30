package org.bank.bankv2.controllers.authwebservice;

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
@CrossOrigin
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthControllers {

    private final AuthenticationService authenticationService;

    @PostMapping("/")
    public ResponseEntity login(@RequestBody UserAuth userAuth) throws IOException {
        return authenticationService.authenticate(userAuth);
    }
}
