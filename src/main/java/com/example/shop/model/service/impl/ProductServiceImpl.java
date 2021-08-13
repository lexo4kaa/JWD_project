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
    public static final String ALL = "all";
    public static final String PERCENT = "%";
    private final ProductDao productDao;
    private static Logger logger = LogManager.getLogger();

    public ProductServiceImpl() {
        productDao = ProductDaoImpl.getInstance();
    }

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAllProducts() throws ServiceException {
        List<Product> products;
        try {
            products = productDao.findAllProducts();
        } catch (DaoException e) {
            logger.error("productDao.findAllProducts() is failed in ProductServiceImpl", e);
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public List<Product> findProductsByType(String type) throws ServiceException {
        List<Product> products;
        try {
            if(type.equals(ALL)) {
                type = PERCENT;
            }
            products = productDao.findProductsByType(type);
        } catch (DaoException e) {
            logger.error("productDao.findProductsByType(" + type + ") is failed in ProductServiceImpl", e);
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public List<Product> findProductsByTeamAndType(String team, String type) throws ServiceException {
        List<Product> products;
        try {
            if(type.equals(ALL)) {
                type = PERCENT;
            }
            if(team.equals(ALL)) {
                products = productDao.findProductsByType(type);
            } else {
                products = productDao.findProductsByTeamAndType(team, type);
            }
        } catch (DaoException e) {
            logger.error("findProductsByTeamAndType(" + team + ", " + type + ") is failed in ProductServiceImpl", e);
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
            logger.error("productDao.findProductsInCart(" + productIds + ") is failed in ProductServiceImpl", e);
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public Map<Integer, Integer> addUnitOfProductToCart(Map<Integer, Integer> cart, int productId) {
        if(cart.containsKey(productId)) {
            cart.put(productId, cart.get(productId) + 1);
        } else {
            cart.put(productId, 1);
        }
        return cart;
    }

    @Override
    public Map<Integer, Integer> deleteUnitOfProductFromCart(Map<Integer, Integer> cart, int productId) {
        if(cart.containsKey(productId)) {
            if(cart.get(productId) > 1) {
                cart.put(productId, cart.get(productId) - 1);
            }
            else {
                cart.remove(productId);
            }
        }
        return cart;
    }

    @Override
    public Map<Integer, Integer> changeQuantityOfProductInCart(Map<Integer, Integer> cart, int productId, int newQuantity) {
        if(newQuantity > 0) {
            cart.put(productId, newQuantity);
        }
        else {
            cart.remove(productId);
        }
        return cart;
    }

    @Override
    public void changeStatusOfFavouriteProduct(int userId, int productId) throws ServiceException {
        try {
            if(productDao.isFavouriteProduct(userId, productId)) {
                productDao.deleteProductFromFavourites(userId, productId);
            } else {
                productDao.addProductToFavourites(userId, productId);
            }
        } catch (DaoException e) {
            logger.error("Exception in changeStatusOfFavouriteProduct(" + userId + "," + productId + ")", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Set<Integer> findFavouriteProducts(int userId) throws ServiceException {
        Set<Integer> products;
        try {
            products = productDao.findFavouriteProducts(userId);
        } catch (DaoException e) {
            logger.error("productDao.findFavouriteProducts(" + userId + ") is failed in ProductServiceImpl", e);
            throw new ServiceException(e);
        }
        return products;
    }
}
