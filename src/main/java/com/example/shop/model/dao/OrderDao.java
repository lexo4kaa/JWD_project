package com.example.shop.model.dao;

import com.example.shop.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * The interface for working with orders
 */
public interface OrderDao {
    /**
     * Find all orders.
     *
     * @return list of orders
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    List<Order> findAllOrders() throws DaoException;

    /**
     * Add order.
     *
     * @param cost              order cost
     * @param userId            user id
     * @param methodOfReceiving method of receiving
     * @param methodOfPayment   method of payment
     * @param address           address
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void addOrder(double cost, int userId, String methodOfReceiving, String methodOfPayment, String address) throws DaoException;

    /**
     * Add products of order.
     *
     * @param productId product id
     * @param quantity  quantity
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void addOrderHasProduct(int productId, int quantity) throws DaoException;

    /**
     * Find orders by user nickname.
     *
     * @param nickname user nickname
     * @return list of orders
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    List<Order> findOrdersByNickname(String nickname) throws DaoException;

    /**
     * Find product ids and quantity of them in order.
     *
     * @param orderId order id
     * @return map of product ids and quantity of them in order
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    Map<Integer, Integer> findInfoAboutOrder(int orderId) throws DaoException;
}
