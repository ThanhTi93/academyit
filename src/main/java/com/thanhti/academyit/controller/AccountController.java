package com.thanhti.academyit.controller;

import com.thanhti.academyit.entity.Account;
import com.thanhti.academyit.service.AccountService;
import com.thanhti.academyit.service.impl.EmailServiceImpl;
import com.thanhti.academyit.service.impl.TemporaryTokenStorage;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Random;


@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    EmailServiceImpl emailService ;

    @Autowired
    TemporaryTokenStorage temporaryTokenStorage;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("account", new Account());
        return "client/auth/Authentication";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Account account, Model model) {
        Random random = new Random();
        String token = String.format("%04d", random.nextInt(10000));
        temporaryTokenStorage.save(token, account);

        try {
            emailService.sendConfirmationEmail(account.getEmail(), token);
            } catch (MessagingException e) {
            model.addAttribute("error", "Failed to send confirmation email.");
            return "client/auth/Register";
        }

        return "client/auth/Token";
    }


    @PostMapping("/verify-token")
    public String verifyToken( @RequestParam String token, Model model) {
        Account account = temporaryTokenStorage.getAccountByToken(token);

        if (account != null) {
            try {
                accountService.registerAccount(account);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return "redirect:/register"; // Redirect to login page
        } else {
            // Token is invalid
            model.addAttribute("error", "Invalid token.");
            return "client/auth/VerifyToken";
        }
    }


}
