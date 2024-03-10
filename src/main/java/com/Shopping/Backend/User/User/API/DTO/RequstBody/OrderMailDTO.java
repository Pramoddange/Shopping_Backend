package com.Shopping.Backend.User.User.API.DTO.RequstBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderMailDTO{
    String userMailId;
    String mailText;
    String subjectLine;
}