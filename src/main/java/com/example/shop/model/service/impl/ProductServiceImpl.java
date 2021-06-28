package com.example.shop.model.service.impl;

import com.example.shop.entity.Product;
import com.example.shop.model.dao.DaoException;
import com.example.shop.model.dao.ProductDao;
import com.example.shop.model.dao.impl.ProductDaoImpl;
import com.example.shop.model.service.ProductService;
import com.example.shop.model.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao = ProductDaoImpl.getInstance();
    private static Logger logger = LogManager.getLogger();

    @Override
    public List<Product> findAllProducts() throws ServiceException {
        List<Product> products;
        try {
            products = productDao.findAllProducts();
        } catch (DaoException e) {
            logger.info("productDao.findAllProducts() is failed in ProductServiceImpl", e);
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public List<Product> findProductsByTeam(String team) throws ServiceException {
        List<Product> products;
        try {
            products = productDao.findProductsByTeam(team);
        } catch (DaoException e) {
            logger.info("productDao.findProductsByTeam(" + team + ") is failed in ProductServiceImpl", e);
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public List<Product> findProductsByIds(Set<Integer> productIds) throws ServiceException {
        List<Product> products = new ArrayList<>();
        try {
            for (int productId: productIds) {
                Product product = productDao.findProductById(productId);
                products.add(product);
            }
        } catch (DaoException e) {
            logger.info("productDao.findProductsInCart(" + productIds + ") is failed in ProductServiceImpl", e);
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public void addProductToCart(Map<Integer, Integer> cast, int productId) {
        if(cast.containsKey(productId)) {
            cast.put(productId, cast.get(productId) + 1);
        } else {
            cast.put(productId, 1);
        }
    }

    @Override
    public void deleteProductFromCart(Map<Integer, Integer> cast, int productId) {
        if(cast.containsKey(productId)) {
            if(cast.get(productId) > 1) {
                cast.put(productId, cast.get(productId) - 1);
            }
            else {
                cast.remove(productId);
            }
        }
    }
}