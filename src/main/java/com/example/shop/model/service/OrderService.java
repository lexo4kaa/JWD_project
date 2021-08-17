package com.example.shop.model.service;

import com.example.shop.entity.Order;
import com.example.shop.model.dao.DaoException;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void addOrder(Map<Integer, Integer> cart, int cost, int userId,
                  String methodOfReceiving, String methodOfPayment) throws ServiceException;
    List<Order> findOrdersByNickname(String nickname) throws ServiceException;
    Map<Integer, Integer> findInfoAboutOrder(int orderId) throws ServiceException;
}
