package com.seraphia.seraphia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterDTO {
    private String name;
    private String phone;
    private String email;
    private String password;
}
