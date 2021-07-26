package com.example.shop.model.service;

import com.example.shop.model.dao.DaoException;

import java.util.Map;

public interface OrderService {
    void addOrder(Map<Integer, Integer> cart, String nickname,
                  String methodOfReceiving, String methodOfPayment) throws ServiceException;
}
