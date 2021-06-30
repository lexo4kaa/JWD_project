package com.example.shop.model.service;

import com.example.shop.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {
    List<Product> findAllProducts() throws ServiceException;
    List<Product> findProductsByTeam(String team) throws ServiceException;
    List<Product> findProductsByIds(Set<Integer> productIds) throws ServiceException;
    void addOrder(Map<Integer, Integer> cart, String nickname) throws ServiceException;
    void addProductToCart(Map<Integer, Integer> cart, int productId);
    void deleteProductFromCart(Map<Integer, Integer> cart, int productId);
}
