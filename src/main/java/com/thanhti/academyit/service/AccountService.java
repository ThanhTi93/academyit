package com.thanhti.academyit.service;

import com.thanhti.academyit.dto.AccountDTO;
import com.thanhti.academyit.entity.Account;
import jakarta.mail.MessagingException;

public interface AccountService {

    public Account registerAccount(AccountDTO accountDTO) throws MessagingException;

    public  Account login(AccountDTO accountDTO);
}
