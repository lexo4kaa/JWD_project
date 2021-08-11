package com.example.shop.model.dao;

import com.example.shop.entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductDao {
    List<Product> findAllProducts() throws DaoException;
    List<Product> findProductsByType(String type) throws DaoException;
    List<Product> findProductsByTeamAndType(String team, String type) throws DaoException;
    Product findProductById(int productId) throws DaoException;
    double findPriceById(int productId) throws DaoException;
    boolean isFavouriteProduct(int userId, int productId) throws DaoException;
    void addProductToFavourites(int userId, int productId) throws DaoException;
    void deleteProductFromFavourites(int userId, int productId) throws DaoException;
    Set<Integer> findFavouriteProducts(int userId) throws DaoException;
}
