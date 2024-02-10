package org.bank.bankv2.web_service.services;

import org.bank.bankv2.web_service.authentication.UserAuth;

import java.io.IOException;

public interface AuthenticationService {
    boolean authenticate(UserAuth userAuth) throws IOException;
}
