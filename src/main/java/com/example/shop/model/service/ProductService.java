package com.example.shop.model.service;

import com.example.shop.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The interface for working with products
 */
public interface ProductService {
    /**
     * Find all products.
     *
     * @return list of products
     * @throws ServiceException if DaoException occur
     */
    List<Product> findAllProducts() throws ServiceException;

    /**
     * Find products by type.
     *
     * @param type product type
     * @return list of products
     * @throws ServiceException if DaoException occur
     */
    List<Product> findProductsByType(String type) throws ServiceException;

    /**
     * Find products by team and type.
     *
     * @param team product team
     * @param type product type
     * @return list of products
     * @throws ServiceException if DaoException occur
     */
    List<Product> findProductsByTeamAndType(String team, String type) throws ServiceException;

    /**
     * Find products by ids.
     *
     * @param productIds product ids
     * @return list of products
     * @throws ServiceException if DaoException occur
     */
    List<Product> findProductsByIds(Set<Integer> productIds) throws ServiceException;

    /**
     * Change quantity of product in cart.
     *
     * @param cart      cart
     * @param productId product id
     * @param quantity  new quantity
     * @return updated cart
     */
    Map<Integer, Integer> changeQuantityOfProductInCart(Map<Integer, Integer> cart, int productId, int quantity);

    /**
     * Change status of favourite product.
     *
     * @param userId    user id
     * @param productId product id
     * @throws ServiceException if DaoException occur
     */
    void changeStatusOfFavouriteProduct(int userId, int productId) throws ServiceException;

    /**
     * Find favourite product ids.
     *
     * @param userId user id
     * @return set of favourite product ids
     * @throws ServiceException if DaoException occur
     */
    Set<Integer> findFavouriteProducts(int userId) throws ServiceException;
}
