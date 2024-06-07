package com.thanhti.academyit.service.impl;

import com.thanhti.academyit.entity.Account;
import com.thanhti.academyit.repository.AccountRepository;
import com.thanhti.academyit.service.AccountService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public Account registerAccount(Account account) throws MessagingException {
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));

        accountRepository.save(account);

        return account;
    }

    @Override
    public boolean confirmUser(String confirmationToken) {
        Account account = accountRepository.findByConfirmationToken(confirmationToken);

        if (account != null) {
            accountRepository.save(account);
            return true;
        }

        return false;
    }
}
