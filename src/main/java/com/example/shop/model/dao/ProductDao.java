package com.example.shop.model.dao;

import com.example.shop.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    List<Product> findAllProducts() throws DaoException;
    List<Product> findProductsByTeam(String team) throws DaoException;
    Product findProductById(int productId) throws DaoException;
    void addOrder(Map<Integer, Integer> cart, String nickname) throws DaoException;
}
