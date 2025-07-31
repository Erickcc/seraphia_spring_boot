package com.seraphia.seraphia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddCartItemRequestDTO {
    private Long productId;
    private Long colorId;
    private Long sizeId;
    private Long cartId;
    private Integer quantity;
    private String category;
}
