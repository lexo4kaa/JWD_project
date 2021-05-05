package com.example.final_project_shop.model.service;

import com.example.final_project_shop.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> findAllProducts() throws ServiceException;
    List<Product> findProductsByTeam(String team) throws ServiceException;
    void addProductToCart(Map<Integer, Integer> cast, int productId);
}
