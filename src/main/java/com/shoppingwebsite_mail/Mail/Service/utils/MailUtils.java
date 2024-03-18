package com.shoppingwebsite_mail.Mail.Service.utils;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {

    // Java Mail Sender -> This istance we require to send mail
    // MimeMessage -> In the object of mimemessage our mail will get created
    // MimeMessageHelper -> With the help of mime Message helper we our going to create MimeMessage i.e. Mail

    JavaMailSender javaMailSender;
    MimeMessageHelper mimeMessageHelper;
    MimeMessage mimeMessage;

    public MailUtils(){
        javaMailSender = new JavaMailSenderImpl();
        mimeMessage = javaMailSender.createMimeMessage();
        mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    }
    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }
    public MimeMessageHelper getMimeMessageHelper() {
        return mimeMessageHelper;
    }
    public MimeMessage getMimeMessage() {
        return mimeMessage;
    }

}
