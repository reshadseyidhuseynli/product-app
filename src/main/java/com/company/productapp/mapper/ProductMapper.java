package com.company.productapp.mapper;

import com.company.productapp.dto.request.ProductRequestDto;
import com.company.productapp.dto.response.ProductResponseDto;
import com.company.productapp.domain.Product;
import org.mapstruct.*;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product toProduct(ProductRequestDto productRequestDto);

    @AfterMapping
    default void mapCreatedDate(@MappingTarget Product product) {
        product.setCreatedDate(LocalDate.now());
    }

    ProductResponseDto toProductResponseDto(Product product);

    List<ProductResponseDto> toProductResponseDtoList(List<Product> products);
}
