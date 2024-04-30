package org.bank.bankv2.web_service.services;

import org.bank.bankv2.web_service.models.authentication.User;
import org.bank.bankv2.web_service.models.authentication.UserAuth;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface UserService {
    ResponseEntity create(User userCreate) throws IOException;
    ResponseEntity patch(User userCreate, int userId) throws IOException;
    ResponseEntity delete(int userId) throws IOException;
    ResponseEntity getById(int userId) throws IOException;
    ResponseEntity findAll() throws IOException;
}
