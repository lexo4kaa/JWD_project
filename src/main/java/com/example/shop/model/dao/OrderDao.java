package com.example.shop.model.dao;

import com.example.shop.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Order> findAllOrders() throws DaoException;
    void addOrder(int cost, int userId,
                  String methodOfReceiving, String methodOfPayment) throws DaoException;
    void addOrderHasProduct(int productId, int quantity) throws DaoException;
    List<Order> findOrdersByNickname(String nickname) throws DaoException;
    Map<Integer, Integer> findInfoAboutOrder(int orderId) throws DaoException;
}
