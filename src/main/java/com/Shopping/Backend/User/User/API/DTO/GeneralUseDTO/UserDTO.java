package com.Shopping.Backend.User.User.API.DTO.GeneralUseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    UUID id;
    String name;
    String email;
    String password;
    Long contactNumber;
    String address;
    String type;
}
