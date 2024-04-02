package org.bank.bankv2.web_service.services;

import org.bank.bankv2.web_service.models.authentication.UserAuth;

import java.io.IOException;

public interface AuthenticationService {
    String authenticate(UserAuth userAuth) throws IOException;
}
