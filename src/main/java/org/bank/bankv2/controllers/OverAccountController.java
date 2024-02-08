package org.bank.bankv2.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.bankv2.models.NoOverAccount;
import org.bank.bankv2.models.OverAccount;
import org.bank.bankv2.services.NoOverAccountService;
import org.bank.bankv2.services.OverAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/over-account")
@RequiredArgsConstructor
public class OverAccountController {

    private  final OverAccountService service;

    // CRUD

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody OverAccount overAccount){
        return ResponseEntity.ok(service.save(overAccount));
    }

    @GetMapping("/")
    public ResponseEntity<List<OverAccount>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/over-account-id")
    public ResponseEntity<OverAccount> findById(@PathVariable("over-account-id")Integer accountId){
        return ResponseEntity.ok(service.findById(accountId));
    }

    @DeleteMapping("/{over-account-id}")
    public ResponseEntity<Void> delete(@PathVariable("over-account-id") Integer accountId){
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
