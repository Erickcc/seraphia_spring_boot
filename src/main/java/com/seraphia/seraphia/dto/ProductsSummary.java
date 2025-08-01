package com.seraphia.seraphia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductsSummary {
    private Long id;
    private String name;
    private Double price;
    private String imageUrl;
}
