package org.bank.bankv2.services;

import org.bank.bankv2.models.NoOverAccount;
import org.bank.bankv2.models.OverAccount;

public interface NoOverAccountService extends AbstractService<NoOverAccount> {
    String debitByUserId(Integer amount, Integer userId);
    String creditByUserId(Integer amount, Integer userId);

}
