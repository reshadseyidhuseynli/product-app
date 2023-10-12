package com.company.productapp.repository;

import com.company.productapp.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Product> productRowMapper;

    public Product getById(Integer id) {
        String sql = "SELECT * FROM product WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject(
                sql,
                params,
                productRowMapper
        );
    }

    public List<Product> getAll() {
        String sql = "SELECT * FROM product";

        return jdbc.query(
                sql,
                productRowMapper
        );
    }

    public void add(Product product) {
        String sql = "INSERT INTO product (name, price, created_at) VALUES (:name, :price,:created_at)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", product.getName());
        params.addValue("price", product.getPrice());
        params.addValue("created_at", Date.valueOf(LocalDate.now()));

        jdbc.update(sql, params, new GeneratedKeyHolder());
    }

    public void update(Integer id,Product product) {
        String sql = "UPDATE product SET name = :name, price = :price, updated_at = :updated_at WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", product.getName());
        params.addValue("price", product.getPrice());
        params.addValue("updated_at", Date.valueOf(LocalDate.now()));
        params.addValue("id", id);

        jdbc.update(sql,params);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM product WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        jdbc.update(sql,params);
    }
}
