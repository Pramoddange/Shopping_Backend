package com.shoppingbackend.Front.API.DTO;

import com.shoppingbackend.Front.API.Enum.UserType;
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
    UserType type;
}
