package com.seraphia.seraphia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private List<CartItemDTO> items;
}
