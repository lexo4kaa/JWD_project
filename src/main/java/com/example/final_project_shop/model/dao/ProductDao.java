package com.example.final_project_shop.model.dao;

import com.example.final_project_shop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAllProducts() throws DaoException;
    List<Product> findProductsByTeam(String team) throws DaoException;
}
