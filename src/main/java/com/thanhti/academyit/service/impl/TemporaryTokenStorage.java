package com.thanhti.academyit.service.impl;

import com.thanhti.academyit.dto.AccountDTO;
import com.thanhti.academyit.entity.Account;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class TemporaryTokenStorage {
    private Map<String, AccountDTO> tokenStorage = new ConcurrentHashMap<>();

    public void save(String token, AccountDTO accountDTO) {
        tokenStorage.put(token, accountDTO);
    }

    public AccountDTO getAccountByToken(String token) {
        return tokenStorage.get(token);
    }
}
