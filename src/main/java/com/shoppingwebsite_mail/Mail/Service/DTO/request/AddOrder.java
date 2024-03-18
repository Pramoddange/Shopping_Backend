package com.shoppingwebsite_mail.Mail.Service.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddOrder {
    String userMailId;
    String mailText;
    String subjectLine;
}
