//package org.bank.bankv2.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.bank.bankv2.web_service.models.authentication.User;
//import org.bank.bankv2.web_service.models.authentication.UserAuth;
//import org.bank.bankv2.web_service.services.AuthenticationService;
//import org.bank.bankv2.web_service.services.UserService;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/user")
//@RequiredArgsConstructor
//public class UserControllers{
//
//    private final UserService userService;
//
//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestBody User user) throws IOException {
//        boolean authenticationResult = userService.create(user);
//        if (authenticationResult) {
//            String token = "Valid Token";
//            HttpHeaders responseHeaders = new HttpHeaders();
//            responseHeaders.set("accessToken", "Bearer " + token);
//            return ResponseEntity.ok().headers(responseHeaders).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//    }
//
//}