package com.seraphia.seraphia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCartItemRequestDTO {
    private Long productId;
    private Long cartId;
}