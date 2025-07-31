package com.seraphia.seraphia.dto;

import com.seraphia.seraphia.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Cart cart;
}
