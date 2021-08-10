package com.example.shop.model.service;

import com.example.shop.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {
    List<Product> findAllProducts() throws ServiceException;
    List<Product> findProductsByType(String type) throws ServiceException;
    List<Product> findProductsByTeamAndType(String team, String type) throws ServiceException;
    List<Product> findProductsByIds(Set<Integer> productIds) throws ServiceException;
    Map<Integer, Integer> addUnitOfProductToCart(Map<Integer, Integer> cart, int productId);
    Map<Integer, Integer> deleteUnitOfProductFromCart(Map<Integer, Integer> cart, int productId);
    Map<Integer, Integer> changeQuantityOfProductInCart(Map<Integer, Integer> cart, int productId, int quantity);
}
