package com.example.shop.model.dao;

import com.example.shop.entity.Product;

import java.util.List;
import java.util.Set;

/**
 * The interface for working with products
 */
public interface ProductDao {
    /**
     * Find all products.
     *
     * @return list of products
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    List<Product> findAllProducts() throws DaoException;

    /**
     * Find products by type.
     *
     * @param type product type
     * @return list of products
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    List<Product> findProductsByType(String type) throws DaoException;

    /**
     * Find products by team and type.
     *
     * @param team product team
     * @param type product type
     * @return list of products
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    List<Product> findProductsByTeamAndType(String team, String type) throws DaoException;

    /**
     * Find product by product id.
     *
     * @param productId product id
     * @return product
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    Product findProductById(int productId) throws DaoException;

    /**
     * Check if favourite product or not.
     *
     * @param userId    user id
     * @param productId product id
     * @return boolean:favourite product or not
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    boolean isFavouriteProduct(int userId, int productId) throws DaoException;

    /**
     * Add product to favourites.
     *
     * @param userId    user id
     * @param productId product id
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void addProductToFavourites(int userId, int productId) throws DaoException;

    /**
     * Delete product from favourites.
     *
     * @param userId    user id
     * @param productId product id
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void deleteProductFromFavourites(int userId, int productId) throws DaoException;

    /**
     * Find set of favourite product ids.
     *
     * @param userId user id
     * @return set of favourite product ids
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    Set<Integer> findFavouriteProducts(int userId) throws DaoException;
}
