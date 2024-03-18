package com.shoppingwebsite_mail.Mail.Service.controller;

import com.shoppingwebsite_mail.Mail.Service.DTO.request.AddOrder;
import com.shoppingwebsite_mail.Mail.Service.DTO.response.GeneralMessage;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
@RequestMapping("/mail/buyer")
public class BuyerController {
    @Autowired
    JavaMailSender javaMailSender;
    @RequestMapping("/buyeProduct")
    public ResponseEntity orderMail(@RequestBody AddOrder addOrder) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        try {
            mimeMessageHelper.setText(addOrder.getMailText());
            mimeMessageHelper.setSubject(addOrder.getSubjectLine());
            mimeMessageHelper.setTo(addOrder.getUserMailId());
        } catch (Exception e) {
            return new ResponseEntity(new GeneralMessage(false), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity(new GeneralMessage(true), HttpStatus.CREATED);
    }

}
