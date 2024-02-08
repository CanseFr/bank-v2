package org.bank.bankv2.repositories;

import org.bank.bankv2.models.NoOverAccount;
import org.bank.bankv2.models.OverAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoOverAccountRepository extends JpaRepository<NoOverAccount, Integer> {

}
