package com.shoppingwebsite_mail.Mail.Service.DTO.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddProductDto {
    String mailId;
    String mailMessage;
    String subjectLine;
    String userName;
}
