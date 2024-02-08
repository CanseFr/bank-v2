package org.bank.bankv2.services;

import org.bank.bankv2.models.Client;
import org.bank.bankv2.models.OverAccount;

import java.util.List;

public interface OverAccountService extends AbstractService<OverAccount> {
    String debitByUserId(Integer amount, Integer userId);
    String creditByUserId(Integer amount, Integer userId);

}
