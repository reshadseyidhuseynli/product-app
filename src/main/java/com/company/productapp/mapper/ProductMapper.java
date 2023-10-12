package com.company.productapp.mapper;

import com.company.productapp.dto.request.ProductRequest;
import com.company.productapp.dto.response.ProductResponse;
import com.company.productapp.domain.Product;
import org.mapstruct.*;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product toProduct(ProductRequest productRequest);

    @AfterMapping
    default void mapCreatedDate(@MappingTarget Product product) {
        product.setCreatedAt(LocalDate.now());
        product.setUpdatedAt(LocalDate.now());
    }

    ProductResponse toProductResponseDto(Product product);

    List<ProductResponse> toProductResponseDtoList(List<Product> products);
}
