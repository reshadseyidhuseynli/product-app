package com.company.productapp.controller;

import com.company.productapp.dto.request.ProductRequestDto;
import com.company.productapp.dto.response.ProductResponseDto;
import com.company.productapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@Min(1) @PathVariable("id") Integer id) {
        return productService.getById(id);
    }

    @PostMapping
    public void add(@Valid @RequestBody ProductRequestDto product) {
        productService.add(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") Integer id,
                              @Valid @RequestBody ProductRequestDto product) {
        productService.update(id, product);
    }
}
