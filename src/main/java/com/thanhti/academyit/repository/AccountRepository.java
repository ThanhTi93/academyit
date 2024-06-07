package com.thanhti.academyit.repository;

import com.thanhti.academyit.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long> {
    Account findByConfirmationToken(String confirmationToken);
}
