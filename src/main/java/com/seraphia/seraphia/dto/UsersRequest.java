package com.seraphia.seraphia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UsersRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String street;
    private String numInt;
    private String numExt;
    private String suburb;
    private String zipCode;
    private String city;
    private String state;

}
