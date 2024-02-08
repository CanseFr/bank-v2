package org.bank.bankv2.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.bankv2.models.Client;
import org.bank.bankv2.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private  final ClientService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody Client userDto){
        return ResponseEntity.ok(service.save(userDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/client-id")
    public ResponseEntity<Client> findById(@PathVariable("client-id")Integer userId){
        return ResponseEntity.ok(service.findById(userId));
    }

    @DeleteMapping("/{client-id}")
    public ResponseEntity<Void> delete(@PathVariable("client-id") Integer userId){
        service.delete(userId);
        return ResponseEntity.accepted().build();
    }

}