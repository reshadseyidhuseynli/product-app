package com.company.productapp.service;

import com.company.productapp.dto.request.ProductRequestDto;
import com.company.productapp.dto.response.ProductResponseDto;
import com.company.productapp.domain.Product;
import com.company.productapp.error.model.ProductNotFoundException;
import com.company.productapp.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Map<Integer, Product> mapByProductId = new HashMap<>();

    private final ProductMapper productMapper;

    public ProductResponseDto getById(Integer id) {
        Product product = mapByProductId.get(id);

        if (Objects.isNull(product)) {
            throw new ProductNotFoundException("Product was not found with given id: " + id);
        }

        return productMapper.toProductResponseDto(product);
    }

    public List<ProductResponseDto> getAll() {
        List<Product> products = new ArrayList<>(mapByProductId.values());
        return productMapper.toProductResponseDtoList(products);
    }

    public void add(ProductRequestDto productRequestDto) {
        Product product = productMapper.toProduct(productRequestDto);
        int mapSize = mapByProductId.keySet().size();
        mapByProductId.put(mapSize + 1, product);
    }

    public void update(Integer id, ProductRequestDto productRequestDto) {
        Product product = mapByProductId.get(id);
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setUpdatedDate(LocalDate.now());
        mapByProductId.put(id, product);
    }

}
