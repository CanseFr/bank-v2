package org.bank.bankv2.controllers.authwebservice;

import lombok.RequiredArgsConstructor;
import org.bank.bankv2.web_service.models.authentication.User;
import org.bank.bankv2.web_service.models.authentication.UserAuth;
import org.bank.bankv2.web_service.services.AuthenticationService;
import org.bank.bankv2.web_service.services.Impl.UserServiceImple;
import org.bank.bankv2.web_service.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControllers{

    private final UserServiceImple userService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody User user) throws IOException {
        return userService.create(user);
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity patch(@RequestBody User user, @PathVariable("user-id") int userId) throws IOException {
        return userService.patch(user, userId);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity delete(@PathVariable("user-id") int userId) throws IOException {
        return userService.delete(userId);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getById(@PathVariable("user-id") int userId) throws IOException {
        return userService.getById(userId);
    }

    @GetMapping()
    public ResponseEntity findAll() throws IOException {
        return userService.findAll();
    }

}