package com.company.productapp.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class Product {

    private Integer id;
    private String name;
    private BigDecimal price;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
