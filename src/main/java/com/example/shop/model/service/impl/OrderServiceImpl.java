package com.example.shop.model.service.impl;

import com.example.shop.entity.Order;
import com.example.shop.model.dao.DaoException;
import com.example.shop.model.dao.OrderDao;
import com.example.shop.model.dao.impl.OrderDaoImpl;
import com.example.shop.model.service.OrderService;
import com.example.shop.model.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private static Logger logger = LogManager.getLogger();
    private static final String EMPTY_STRING = "";
    private static final String DEFAULT_ADDRESS = "Minsk, Oktyabrskaya st., 10/2";

    @Override
    public void addOrder(Map<Integer, Integer> cart, double cost, int userId,
                         String methodOfReceiving, String methodOfPayment, String address) throws ServiceException {
        try {
            if(address.equals(EMPTY_STRING)) {
                address = DEFAULT_ADDRESS;
            }
            orderDao.addOrder(cost, userId, methodOfReceiving, methodOfPayment, address);
            Set<Integer> keys = cart.keySet();
            for (int productId : keys) {
                int quantity = cart.get(productId);
                orderDao.addOrderHasProduct(productId, quantity);
            }
        } catch (DaoException e) {
            logger.error("adding order is failed in OrderServiceImpl", e);
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

    @Override
    public Map<Integer, Integer> findInfoAboutOrder(int orderId) throws ServiceException {
        Map<Integer, Integer> cart;
        try {
            cart = orderDao.findInfoAboutOrder(orderId);
        } catch (DaoException e) {
            logger.error("orderDao.findInfoAboutOrder(" + orderId + ") is failed in OrderServiceImpl", e);
            throw new ServiceException(e);
        }
        return cart;
    }
}
