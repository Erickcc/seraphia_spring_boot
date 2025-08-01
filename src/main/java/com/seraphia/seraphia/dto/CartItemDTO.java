package com.seraphia.seraphia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDTO {
    private Long id;
    private Long productId;
    private Long colorId;
    private Long sizeId;
    private Integer quantity;
}
