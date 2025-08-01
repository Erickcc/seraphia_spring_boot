package com.seraphia.seraphia.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private Long idOrder;
    private BigDecimal netSale;
    private String orderDate;
    private UsersRequest user;
    private List<ProductsSummary> products;
}
