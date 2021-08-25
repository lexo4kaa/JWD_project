package com.example.shop.model.service;

import com.example.shop.entity.Order;
import com.example.shop.model.dao.DaoException;

import java.util.List;
import java.util.Map;

/**
 * TThe interface for working with orders
 */
public interface OrderService {
    /**
     * Add order.
     *
     * @param cart              cart
     * @param cost              order cost
     * @param userId            user id
     * @param methodOfReceiving method of receiving
     * @param methodOfPayment   method of payment
     * @param address           address
     * @throws ServiceException if DaoException occur
     */
    void addOrder(Map<Integer, Integer> cart, double cost, int userId,
                  String methodOfReceiving, String methodOfPayment, String address) throws ServiceException;

    /**
     * Find orders by nickname.
     *
     * @param nickname user nickname
     * @return list of orders
     * @throws ServiceException if DaoException occur
     */
    List<Order> findOrdersByNickname(String nickname) throws ServiceException;

    /**
     * Find product ids and quantity of them in order.
     *
     * @param orderId order id
     * @return map of product ids and quantity of them in order
     * @throws ServiceException if DaoException occur
     */
    Map<Integer, Integer> findInfoAboutOrder(int orderId) throws ServiceException;
}
