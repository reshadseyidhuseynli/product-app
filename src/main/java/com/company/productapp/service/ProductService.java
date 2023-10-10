package com.company.productapp.service;

import com.company.productapp.domain.Product;
import com.company.productapp.dto.request.ProductRequest;
import com.company.productapp.dto.response.ProductResponse;
import com.company.productapp.error.model.ProductNotFoundException;
import com.company.productapp.mapper.ProductMapper;
import com.company.productapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final ProductMapper productMapper;

    public ProductResponse getById(Integer id) {
        final Product product = repo.getById(id);

        if (Objects.isNull(product)) {
            throw new ProductNotFoundException("Product was not found with given id: " + id);
        }

        return productMapper.toProductResponseDto(product);
    }

    public List<ProductResponse> getAll() {
        return productMapper.toProductResponseDtoList(repo.getAll());
    }

    public void add(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        repo.add(product);
    }

    public void update(Integer id, ProductRequest productRequest) {
        repo.update(id, productMapper.toProduct(productRequest));
    }

    public void delete(Integer id) {
        repo.delete(id);
    }
}
