package com.example.shop.model.service.impl;

import com.example.shop.entity.Order;
import com.example.shop.entity.User;
import com.example.shop.model.dao.DaoException;
import com.example.shop.model.dao.OrderDao;
import com.example.shop.model.dao.impl.OrderDaoImpl;
import com.example.shop.model.service.OrderService;
import com.example.shop.model.service.ServiceException;
import com.example.shop.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private static Logger logger = LogManager.getLogger();

    @Override
    public void addOrder(Map<Integer, Integer> cart, String nickname,
                         String methodOfReceiving, String methodOfPayment) throws ServiceException {
        try {
            orderDao.addOrder(cart, nickname, methodOfReceiving, methodOfPayment);
            int[] keys = cart.keySet().stream()
                            .mapToInt(Integer::intValue)
                            .toArray();
            for(int i = 0; i < cart.size(); i++) {
                int productId = keys[i];
                orderDao.addOrderHasProduct(cart, productId);
            }
        } catch (DaoException e) {
            logger.error("orderDao.addOrder(" + cart + "," + nickname + "," +
                        methodOfReceiving + "," + methodOfPayment + ") is failed in OrderServiceImpl", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrdersByNickname(String nickname) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findOrdersByNickname(nickname);
        } catch (DaoException e) {
            logger.error("orderDao.findOrdersByNickname(" + nickname + ") is failed in OrderServiceImpl", e);
            throw new ServiceException(e);
        }
        return orders;
    }
}
