package com.example.final_project_shop.model.dao;

import com.example.final_project_shop.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    List<Product> findAllProducts() throws DaoException;
    List<Product> findProductsByTeam(String team) throws DaoException;
    void addProductToCart(Map<Integer, Integer> cast, int productId) throws DaoException;
}
