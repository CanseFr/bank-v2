package org.bank.bankv2.web_service.services;

import org.bank.bankv2.web_service.models.authentication.UserAuth;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthenticationService {
    ResponseEntity authenticate(UserAuth userAuth) throws IOException;
}
