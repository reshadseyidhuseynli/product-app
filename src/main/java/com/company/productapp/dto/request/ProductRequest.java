package com.company.productapp.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotBlank
    private String name;

    @NotNull
    @Min(value = 0, message = "price must be greater than 0")
    private BigDecimal price;

}
