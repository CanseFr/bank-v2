package org.bank.bankv2.services;

import org.bank.bankv2.models.Bank;
import org.bank.bankv2.models.Client;

import java.util.List;

public interface BankService extends AbstractService<Bank>{
    List<Client> getAllClientByBankId(Integer idBank);
    Float debitByAccountId();
    Float creditByAccountId();
    Float createAccount();
    public Float convertFromEuro(Float amount, String currency);
    public Float convertToEuro(Double amount, String currency);

}
