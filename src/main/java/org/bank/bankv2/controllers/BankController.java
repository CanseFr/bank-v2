package org.bank.bankv2.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.bankv2.classes.Currencies;
import org.bank.bankv2.models.Account;
import org.bank.bankv2.models.Bank;
import org.bank.bankv2.models.Client;
import org.bank.bankv2.services.BankService;
import org.bank.bankv2.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankController {
    private  final BankService service;

    // CRUD

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody Bank bankId){
        return ResponseEntity.ok(service.save(bankId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Bank>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/bank-id")
    public ResponseEntity<Bank> findById(@PathVariable("bank-id")Integer bankId){
        return ResponseEntity.ok(service.findById(bankId));
    }

    @DeleteMapping("/{bank-id}")
    public ResponseEntity<Void> delete(@PathVariable("bank-id") Integer bankId){
        service.delete(bankId);
        return ResponseEntity.accepted().build();
    }

    // Metier

    @PostMapping("/create/{id-user}/{id-bank}/{over}")
    public ResponseEntity<Account> createAccountOnUserIdAndBankId(@PathVariable("id-user") Integer userId, @PathVariable("id-bank") Integer bankId, @PathVariable("over") Integer over ){
        return ResponseEntity.ok(service.createAccount(userId, bankId, over));
    }

    @PostMapping("/debit/{id-user}/{amount}")
    public ResponseEntity<Account> debitByUserId(@PathVariable("id-user") Integer userId,@PathVariable("amount") Float amount ){
        return ResponseEntity.ok(service.debitByAccountId(userId, amount));
    }

    @PostMapping("/credit/{id-user}/{amount}")
    public ResponseEntity<Account> creditByUserId(@PathVariable("id-user") Integer userId,@PathVariable("amount") Float amount ){
        return ResponseEntity.ok(service.creditByAccountId(userId, amount));
    }

    @PostMapping("/conv-from/{amount}/{currency}")
    public ResponseEntity<Currencies> convertToEuro(@PathVariable("amount") Float amount, @PathVariable("currency") String currency ){
        return ResponseEntity.ok(service.convertFromEuro(amount, currency));
    }

    @PostMapping("/conv-to/{amount}/{currency}")
    public ResponseEntity<Currencies> convertFromEuro(@PathVariable("amount") Float amount, @PathVariable("currency") String currency ){
        return ResponseEntity.ok(service.convertToEuro(amount, currency));
    }

}
