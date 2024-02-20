package com.shoppingbackend.Front.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserSingUPDTO {
    String name;
    String email;
    String password;
    long contactNo;
    String address;
    String type;
}
