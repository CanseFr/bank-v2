package org.bank.bankv2.services.Impl;

import lombok.RequiredArgsConstructor;
import org.bank.bankv2.models.Client;
import org.bank.bankv2.repositories.ClientRepository;
import org.bank.bankv2.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;

    @Override
    public Integer save(Client client) {
        return repository.save(client).getId();
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Client findById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
