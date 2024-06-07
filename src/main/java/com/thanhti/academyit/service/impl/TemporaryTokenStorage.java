package com.thanhti.academyit.service.impl;

import com.thanhti.academyit.entity.Account;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class TemporaryTokenStorage {
    private Map<String, Account> tokenStorage = new ConcurrentHashMap<>();

    public void save(String token, Account account) {
        tokenStorage.put(token, account);
    }

    public Account getAccountByToken(String token) {
        return tokenStorage.get(token);
    }
}
