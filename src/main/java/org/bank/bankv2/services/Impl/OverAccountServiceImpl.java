package org.bank.bankv2.services.Impl;

import lombok.RequiredArgsConstructor;
import org.bank.bankv2.models.NoOverAccount;
import org.bank.bankv2.models.OverAccount;
import org.bank.bankv2.repositories.OverAccountRepository;
import org.bank.bankv2.services.OverAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OverAccountServiceImpl implements OverAccountService {

    private final OverAccountRepository overtAccountRepository;

    @Override
    public Integer save(OverAccount overAccount) {
        return overtAccountRepository.save(overAccount).getId();
    }

    @Override
    public List<OverAccount> findAll() {
        return overtAccountRepository.findAll();
    }

    @Override
    public OverAccount findById(Integer id) {
        return overtAccountRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        overtAccountRepository.deleteById(id);
    }


    @Override
    public String debitByUserId(Integer amount, Integer idAccount) {
        OverAccount overaccount = overtAccountRepository.findById(idAccount).orElseThrow();
        Float checkOver = overaccount.getSolde() - amount;
        if (checkOver < 0 ) {
            return "Fond insufisant";
        }
        overaccount.setSolde(overaccount.getSolde() - amount);
        return overaccount.getSolde().toString();
    }

    @Override
    public String creditByUserId(Integer amount, Integer userId) {
        if (amount < 0) return "Montant invalide pour la procedure procedure";
        OverAccount OverAccount = overtAccountRepository.findById(userId).orElseThrow();
        OverAccount.setSolde(OverAccount.getSolde() + amount);
        overtAccountRepository.save(OverAccount);
        return "Votre solde actuel est maintenant de : " + OverAccount.getSolde().toString() + " €";
    }

}
