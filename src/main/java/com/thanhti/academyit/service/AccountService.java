package com.thanhti.academyit.service;

import com.thanhti.academyit.entity.Account;
import jakarta.mail.MessagingException;

public interface AccountService {

    public Account registerAccount(Account account) throws MessagingException;

    public boolean confirmUser(String confirmationToken);
}
