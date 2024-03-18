package com.shoppingwebsite_mail.Mail.Service.service;

import com.shoppingwebsite_mail.Mail.Service.DTO.request.AddProductDto;
import com.shoppingwebsite_mail.Mail.Service.utils.MailUtils;
import com.shoppingwebsite_mail.Mail.Service.utils.ShoppingLogger;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SellerMailService {

    @Autowired
    MailUtils mailUtils;
  @Autowired
  JavaMailSender mailSender;
    public void sendAddProductMail(AddProductDto addProductDto) throws Exception{
        ShoppingLogger.logger.info("AddProductDTO RequestBody : " + addProductDto.toString());
        ShoppingLogger.logger.info("Setting up mail configuration");
        String emailId = addProductDto.getMailId();
        String subject = addProductDto.getSubjectLine();
        MimeMessage message = mailUtils.getMimeMessage();
       // JavaMailSender mailSender = mailUtils.getJavaMailSender();
        ShoppingLogger.logger.info("Mail configuration setup done");
        MimeMessageHelper messageHelper = mailUtils.getMimeMessageHelper();
        try{
            messageHelper.setTo(emailId);
            messageHelper.setSubject(subject);
            messageHelper.setText(addProductDto.getMailMessage());
            ShoppingLogger.logger.info("Mail Sent successfully");
            mailSender.send(message);

        }catch (Exception e){
            throw e;
        }
    }
}
