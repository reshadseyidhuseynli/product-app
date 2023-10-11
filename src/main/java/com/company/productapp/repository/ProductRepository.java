package com.company.productapp.repository;

import com.company.productapp.domain.Product;
import com.company.productapp.error.model.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final JdbcTemplate jdbc;

    public Product getById(Integer id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        List<Product> result = jdbc.query(sql, getRowMapper(), id);

        if (result.isEmpty()) {
            throw new ProductNotFoundException("Product was not found with given id: " + id);
        }

        return result.get(0);
    }

    public List<Product> getAll() {
        String sql = "SELECT * FROM product";
        return jdbc.query(sql, getRowMapper());
    }

    public void add(Product product) {
        String sql = "INSERT INTO product (name, price, created_at, updated_at) VALUES (?,?,?,?)";
        jdbc.update(
                sql,
                product.getName(),
                product.getPrice(),
                Date.valueOf(product.getCreatedAt()),
                Date.valueOf(product.getUpdatedAt()));
    }

    public void update(Integer id, Product product) {
        String sql = "UPDATE product SET " +
                "name = ?, price = ?, updated_at = ? " +
                "WHERE id = ?";
        jdbc.update(
                sql,
                product.getName(),
                product.getPrice(),
                Date.valueOf(product.getUpdatedAt()),
                id
        );
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM product WHERE id = ?";
        int numberOfAffectedRows = jdbc.update(sql, id);

        if (numberOfAffectedRows == 0) {
            throw new ProductNotFoundException("Product was not found with given id: " + id);
        }
    }

    private RowMapper<Product> getRowMapper() {
        return (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setCreatedAt(rs.getDate("created_at").toLocalDate());
            product.setUpdatedAt(rs.getDate("updated_at").toLocalDate());
            return product;
        };
    }
}
