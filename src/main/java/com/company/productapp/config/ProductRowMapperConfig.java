package com.company.productapp.config;

import com.company.productapp.domain.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import java.util.Objects;


@Configuration
public class ProductRowMapperConfig {

    @Bean("productRowMapper")
    public RowMapper<Product> getRowMapper() {
        return (rs, rowNum) -> Product.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .price(rs.getBigDecimal("price"))
                .createdAt(rs.getDate("created_at").toLocalDate())
                .updatedAt(Objects.isNull(
                        rs.getDate("updated_at")) ? null : rs.getDate("updated_at").toLocalDate()
                )
                .build();
    }
}
