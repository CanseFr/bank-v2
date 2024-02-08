package org.bank.bankv2.controllers;

import lombok.RequiredArgsConstructor;
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


}
