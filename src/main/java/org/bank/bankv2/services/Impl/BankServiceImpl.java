package org.bank.bankv2.services.Impl;

import lombok.RequiredArgsConstructor;
import org.bank.bankv2.enums.Currency;
import org.bank.bankv2.models.Bank;
import org.bank.bankv2.models.Client;
import org.bank.bankv2.repositories.BankRepository;
import org.bank.bankv2.services.BankService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    @Override
    public Integer save(Bank bank) {
        return bankRepository.save(bank).getId();
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public Bank findById(Integer id) {
        return bankRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        bankRepository.deleteById(id);
    }

    @Override
    public List<Client> getAllClientByBankId(Integer idBank) {
        return bankRepository.findAllClientByBankId(idBank);
    }

    @Override
    public Float debitByAccountId() {
        return null;
    }

    @Override
    public Float creditByAccountId() {
        return null;
    }

    @Override
    public Float createAccount() {
        return null;
    }

    @Override
    public Float convertFromEuro(Float amount, String currency) {
        Float montantConverti = 0.0F;

        switch (currency.toLowerCase()) {
            case "usd":
                montantConverti = (float) (amount * Currency.USD.getRate());
                break;
            case "gbp":
                montantConverti = (float) (amount * Currency.GBP.getRate());
                break;
            case "yen":
                montantConverti = (float) (amount * Currency.YUAN.getRate());
                break;
            default:
                System.out.println("Devise non prise en charge");
        }

        return montantConverti;
    }

    @Override
    public Float convertToEuro(Double amount, String currency) {
        double montantConverti = 0.0;

        switch (currency.toLowerCase()) {
            case "usd":
                montantConverti = amount / Currency.USD.getRate();
                break;
            case "gbp":
                montantConverti = amount / Currency.GBP.getRate();
                break;
            case "yen":
                montantConverti = amount / Currency.YUAN.getRate();
                break;
            default:
                System.out.println("Devise non prise en charge");
        }

        return (float) montantConverti;
    }
}
