package com.example.shop.model.dao;

import com.example.shop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAllProducts() throws DaoException;
    List<Product> findProductsByType(String type) throws DaoException;
    List<Product> findProductsByTeamAndType(String team, String type) throws DaoException;
    Product findProductById(int productId) throws DaoException;
    double findPriceById(int productId) throws DaoException;
}
