package com.Shopping.Backend.User.User.API.DTO.RequstBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductMailDto {
    String mailId;
    String mailMessage;
    String subjectLine;
    String userName;
}
