package com.example.shop.model.dao;

import com.example.shop.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Order> findAllOrders() throws DaoException;
    void addOrder(Map<Integer, Integer> cart, String nickname,
                  String methodOfReceiving, String methodOfPayment) throws DaoException;
    void addOrderHasProduct(Map<Integer, Integer> cart, int productId) throws DaoException;
    List<Order> findOrdersByNickname(String nickname) throws DaoException;
    Map<Integer, Integer> findInfoAboutOrder(int orderId) throws DaoException;
}
