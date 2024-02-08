package org.bank.bankv2.services.Impl;

import lombok.RequiredArgsConstructor;
import org.bank.bankv2.classes.Currencies;
import org.bank.bankv2.enums.Currency;
import org.bank.bankv2.models.*;
import org.bank.bankv2.repositories.BankRepository;
import org.bank.bankv2.repositories.NoOverAccountRepository;
import org.bank.bankv2.repositories.OverAccountRepository;
import org.bank.bankv2.services.BankService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    private final NoOverAccountRepository noOverAccountRepository;
    private final OverAccountRepository overAccountRepository;
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
    public Account debitByAccountId(Integer idCLient, Float amount) {

        // Over Account
        // The bank has the right to put the customer overdrawn
        try {
            OverAccount account;
            account = overAccountRepository.findById(idCLient).get();
            account.setSolde(account.getSolde() - amount);
            overAccountRepository.save(account);
            return account;
        }catch (Exception e){
//            System.out.println(e.getMessage());
        }

        try {
            NoOverAccount account;
            account = noOverAccountRepository.findById(idCLient).get();
            account.setSolde(account.getSolde() - amount);
            noOverAccountRepository.save(account);
            return account;
        }catch (Exception e){
//            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Account creditByAccountId(Integer idCLient, Float amount) {

        // Over Account
        try {
            OverAccount account;
            account = overAccountRepository.findById(idCLient).get();
            account.setSolde(account.getSolde() + amount);
            overAccountRepository.save(account);
            return account;
        }catch (Exception e){
//            System.out.println(e.getMessage());
        }

        try {
            NoOverAccount account;
            account = noOverAccountRepository.findById(idCLient).get();
            account.setSolde(account.getSolde() + amount);
            noOverAccountRepository.save(account);
            return account;
        }catch (Exception e){
//            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Account createAccount(Integer idClient, Integer banId, Integer over) {
        Bank bank = bankRepository.findById(banId).orElseThrow();
        if (over > 0){
            OverAccount overAccount = new OverAccount();
            overAccount.setSolde((float) 0);
            overAccount.setBank(bank);
            overAccount.setOverdrawn(Float.valueOf(over));
            overAccountRepository.save(overAccount);
            return overAccount;
        } else {
            NoOverAccount noOverAccount = new NoOverAccount();
            noOverAccount.setSolde((float) 0);
            noOverAccount.setBank(bank);
            noOverAccountRepository.save(noOverAccount);
            return noOverAccount;
        }
    }

    @Override
    public Currencies convertFromEuro(Float amount, String currency) {
        Currencies cur = new Currencies();
        cur.setBaseAmountFrom(amount);
        cur.setTo(currency);

        switch (currency.toLowerCase()) {
            case "usd":
                cur.setConvertedAmountTo((float) (amount * Currency.USD.getRate()));
                cur.setFrom(currency);
                break;
            case "gbp":
                cur.setConvertedAmountTo((float) (amount * Currency.GBP.getRate()));
                cur.setFrom(currency);
                break;
            case "yen":
                cur.setConvertedAmountTo((float) (amount * Currency.YUAN.getRate()));
                cur.setFrom(currency);
                break;
            default:
                System.out.println("Devise non prise en charge");
        }

        return cur;
    }

    @Override
    public Currencies convertToEuro(Float amount, String currency) {
        Currencies cur = new Currencies();
        cur.setBaseAmountFrom(amount);
        cur.setTo("eur");

        switch (currency.toLowerCase()) {
            case "usd":
                cur.setConvertedAmountTo((float) (amount / Currency.USD.getRate()));
                cur.setFrom(currency);
                break;
            case "gbp":
                cur.setConvertedAmountTo((float) (amount / Currency.USD.getRate()));
                cur.setFrom(currency);
                break;
            case "yen":
                cur.setConvertedAmountTo((float) (amount / Currency.USD.getRate()));
                cur.setFrom(currency);
                break;
            default:
                System.out.println("Devise non prise en charge");
        }

        return cur;
    }
}
