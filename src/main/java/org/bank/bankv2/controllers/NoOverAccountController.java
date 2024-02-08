package org.bank.bankv2.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.bankv2.models.NoOverAccount;
import org.bank.bankv2.services.NoOverAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/no-over-account")
@RequiredArgsConstructor
public class NoOverAccountController {

    private  final NoOverAccountService service;

    // CRUD

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody NoOverAccount userDto){
        return ResponseEntity.ok(service.save(userDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<NoOverAccount>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/no-over-account-id")
    public ResponseEntity<NoOverAccount> findById(@PathVariable("no-over-account-id")Integer accountId){
        return ResponseEntity.ok(service.findById(accountId));
    }

    @DeleteMapping("/{no-over-account-id}")
    public ResponseEntity<Void> delete(@PathVariable("no-over-account-id") Integer accountId){
        service.delete(accountId);
        return ResponseEntity.accepted().build();
    }

    // Metier

    @PostMapping("/debit/{amount}/id/{user-id}")
    public ResponseEntity<String> debitByUserId(@PathVariable("amount") Integer amount, @PathVariable("user-id") Integer userId ){
        return ResponseEntity.ok(service.debitByUserId(amount, userId));
    }

    @PostMapping("/credit/{amount}/id/{user-id}")
    public ResponseEntity<String> creditByUserId(@PathVariable("amount") Integer amount, @PathVariable("user-id") Integer userId ){
        return ResponseEntity.ok(service.creditByUserId(amount, userId));
    }

}
