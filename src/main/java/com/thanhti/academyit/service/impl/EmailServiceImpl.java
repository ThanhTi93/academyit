package com.thanhti.academyit.service.impl;


import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private  String fromEmailId;

    public void sendConfirmationEmail(String to, String token) throws MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Xác nhận đăng ký tài khoản");
        simpleMailMessage.setFrom(fromEmailId);

        simpleMailMessage.setText("Mã xác nhận của bạn là :" + token);

        mailSender.send(simpleMailMessage);
    }


}

