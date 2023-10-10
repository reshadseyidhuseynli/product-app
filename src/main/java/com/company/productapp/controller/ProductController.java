package com.company.productapp.controller;

import com.company.productapp.dto.request.ProductRequest;
import com.company.productapp.dto.response.ProductResponse;
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
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@Min(1) @PathVariable("id") Integer id) {
        return productService.getById(id);
    }

    @PostMapping
    public void add(@Valid @RequestBody ProductRequest product) {
        productService.add(product);
    }

    @PutMapping()
    public void updateProduct(@RequestParam("id") Integer id,
                              @Valid @RequestBody ProductRequest product) {
        productService.update(id, product);
    }

    @DeleteMapping()
    public void deleteProduct(@RequestParam("id") Integer id) {
        productService.delete(id);
    }
}
