package com.example.shop.model.dao;

import com.example.shop.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Order> findAllOrders() throws DaoException;
    void addOrder(Map<Integer, Integer> cart, String nickname) throws DaoException;
    void addOrderHasProduct(Map<Integer, Integer> cart, int productId) throws DaoException;
}
