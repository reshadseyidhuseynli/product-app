package com.company.productapp.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Product {

    private Integer id;
    private String name;
    private BigDecimal price;
    private LocalDate createdDate;
    private LocalDate updatedDate;

}
