package com.thanhti.academyit.service.impl;

import com.thanhti.academyit.dto.AccountDTO;
import com.thanhti.academyit.entity.Account;
import com.thanhti.academyit.entity.UserRole;
import com.thanhti.academyit.repository.AccountRepository;
import com.thanhti.academyit.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private BCryptPasswordEncoder  bCryptPasswordEncoder;

    @Override
    public Account registerAccount(AccountDTO accountDTO) {

        accountDTO.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);

        account.setRole(UserRole.USER);
        accountRepository.save(account);

        return account;
    }

   public  Account login(AccountDTO accountDTO) {
        Optional<Account> opt = accountRepository.findById(accountDTO.getEmail());

        if(opt.isPresent() && bCryptPasswordEncoder.matches(accountDTO.getPassword(), opt.get().getPassword())) {
            return  opt.get();
        }
        return null ;
   }
}
