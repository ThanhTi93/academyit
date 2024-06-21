package com.thanhti.academyit.controller;

import com.thanhti.academyit.dto.AccountDTO;
import com.thanhti.academyit.entity.Account;
import com.thanhti.academyit.entity.UserRole;
import com.thanhti.academyit.service.AccountService;
import com.thanhti.academyit.service.impl.EmailServiceImpl;
import com.thanhti.academyit.service.impl.TemporaryTokenStorage;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;


@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    EmailServiceImpl emailService ;

    @Autowired
    TemporaryTokenStorage temporaryTokenStorage;

    @Autowired
    HttpSession session;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("accountDTO", new AccountDTO());
        return "client/auth/Authentication";
    }

    @PostMapping("/login")
 public ModelAndView login(@ModelAttribute AccountDTO accountDTO, ModelMap model)  {

      Account  account = accountService.login(accountDTO);
        if(account == null) {
            model.addAttribute("message", "Mật khẩu hoặc tên đăng nhập không đúng.");
            return  new ModelAndView("client/auth/Authentication", model);
        }
        session.setAttribute("account",account);
        session.setAttribute("checkLogin", true);
        if(account.getRole() == UserRole.USER) {
            return  new ModelAndView("client/home/Home", model);
        }
        return  new ModelAndView("redirect:/categories", model);
 }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute AccountDTO accountDTO, Model model) {
        Random random = new Random();
        String token = String.format("%04d", random.nextInt(10000));
        temporaryTokenStorage.save(token, accountDTO);

        try {
            emailService.sendConfirmationEmail(accountDTO.getEmail(), token);
            } catch (MessagingException e) {
            model.addAttribute("error", "Failed to send confirmation email.");
            return "client/auth/Register";
        }

        return "client/auth/Token";
    }


    @PostMapping("/verify-token")
    public String verifyToken( @RequestParam String token, Model model) {
        AccountDTO accountDTO = temporaryTokenStorage.getAccountByToken(token);

        if (accountDTO != null) {
            try {
                accountService.registerAccount(accountDTO);
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
