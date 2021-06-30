package com.example.shop.model.dao;

import com.example.shop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAllProducts() throws DaoException;
    List<Product> findProductsByTeam(String team) throws DaoException;
    Product findProductById(int productId) throws DaoException;
    double findPriceById(int productId) throws DaoException;
}
