package com.company.productapp.repository;

import com.company.productapp.domain.Product;
import com.company.productapp.error.model.ProductNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class ProductRepository {

    private static final Map<Integer, Product> products = new HashMap<>();
    private static Integer lastId;

    private static Integer getNextId() {
        return Objects.isNull(lastId) ? lastId = 1 : ++lastId;
    }

    public Product getById(Integer id) {
        Product product = products.get(id);

        if (Objects.isNull(product)) {
            throw new ProductNotFoundException("Product was not found with given id: " + id);
        }

        return product;
    }

    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    public void add(Product product) {
        products.put(getNextId(), product);
    }

    public void update(Integer id, Product newProduct) {
        Product product = products.get(id);
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setUpdatedDate(LocalDate.now());
    }

    public void delete(Integer id) {
        Product product = products.remove(id);

        if (Objects.isNull(product)) {
            throw new ProductNotFoundException("Product was not found with given id: " + id);
        }
    }
}
