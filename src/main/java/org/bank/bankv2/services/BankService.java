package org.bank.bankv2.services;

import org.bank.bankv2.classes.Currencies;
import org.bank.bankv2.models.Account;
import org.bank.bankv2.models.Bank;
import org.bank.bankv2.models.Client;

import java.util.List;

public interface BankService extends AbstractService<Bank>{
    List<Client> getAllClientByBankId(Integer idBank);
    public Account debitByAccountId(Integer idCLient, Float amount) ;
    public Account creditByAccountId(Integer idCLient, Float amount);
    Account createAccount(Integer idClient, Integer banId, Integer over);

    public Currencies convertFromEuro(Float amount, String currency);
    public Currencies convertToEuro(Float amount, String currency);

}
