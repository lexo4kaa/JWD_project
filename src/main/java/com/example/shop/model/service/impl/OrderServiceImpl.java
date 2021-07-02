package com.example.shop.model.service.impl;

import com.example.shop.model.dao.DaoException;
import com.example.shop.model.dao.OrderDao;
import com.example.shop.model.dao.impl.OrderDaoImpl;
import com.example.shop.model.service.OrderService;
import com.example.shop.model.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private static Logger logger = LogManager.getLogger();

    @Override
    public void addOrder(Map<Integer, Integer> cart, String nickname) throws ServiceException {
        try {
            orderDao.addOrder(cart, nickname);

            int[] keys = cart.keySet().stream()
                            .mapToInt(Integer::intValue)
                            .toArray();
            for(int i = 0; i < cart.size(); i++) {
                int productId = keys[i];
                orderDao.addOrderHasProduct(cart, productId);
            }
        } catch (DaoException e) {
            logger.info("orderDao.addOrder(" + cart + "," + nickname + ") is failed in OrderServiceImpl", e);
            throw new ServiceException(e);
        }
    }
}
