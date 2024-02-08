package org.bank.bankv2.services.Impl;

import lombok.RequiredArgsConstructor;
import org.bank.bankv2.exceptions.customs.InsufficientFinancialFunds;
import org.bank.bankv2.models.NoOverAccount;
import org.bank.bankv2.repositories.NoOverAccountRepository;
import org.bank.bankv2.services.NoOverAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoOverAccountServiceImpl implements NoOverAccountService {

    private final NoOverAccountRepository noOverAccountRepository;

    @Override
    public Integer save(NoOverAccount noOverAccount) {
        return noOverAccountRepository.save(noOverAccount).getId();
    }

    @Override
    public List<NoOverAccount> findAll() {
        return noOverAccountRepository.findAll();
    }

    @Override
    public NoOverAccount findById(Integer id) {
        return noOverAccountRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        noOverAccountRepository.deleteById(id);
    }

    @Override
    public String debitByUserId(Integer amount, Integer userId) {
        NoOverAccount noOverAccount = noOverAccountRepository.findById(userId).orElseThrow();
        Float checkOver = noOverAccount.getSolde() - amount;

        if (checkOver < 0) {
            return "Fond insufisant";
        }
        noOverAccount.setSolde(noOverAccount.getSolde() - amount);
        noOverAccountRepository.save(noOverAccount);
        return "Votre solde actuel est maintenant de : " + noOverAccount.getSolde().toString() + " €";
    }

    @Override
    public String creditByUserId(Integer amount, Integer userId) {
        if (amount < 0) return "Montant invalide pour la procedure procedure";
        NoOverAccount noOverAccount = noOverAccountRepository.findById(userId).orElseThrow();
        noOverAccount.setSolde(noOverAccount.getSolde() + amount);
        noOverAccountRepository.save(noOverAccount);
        return "Votre solde actuel est maintenant de : " + noOverAccount.getSolde().toString() + " €";
    }

}
