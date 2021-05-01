package com.example.final_project_shop.model.service.impl;

import com.example.final_project_shop.entity.Product;
import com.example.final_project_shop.model.dao.BaseDao;
import com.example.final_project_shop.model.dao.DaoException;
import com.example.final_project_shop.model.dao.impl.BaseDaoImpl;
import com.example.final_project_shop.model.service.ProductService;
import com.example.final_project_shop.model.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final BaseDao baseDao = BaseDaoImpl.getInstance();
    private static Logger logger = LogManager.getLogger();

    @Override
    public List<Product> findAllProducts() throws ServiceException {
        List<Product> products;
        try {
            products = baseDao.findAllProducts();
        } catch (DaoException e) {
            logger.info("baseDao.findAllProducts() is failed in ProductServiceImpl", e);
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public List<Product> findProductsByTeam(String team) throws ServiceException {
        List<Product> products;
        try {
            products = baseDao.findProductsByTeam(team);
        } catch (DaoException e) {
            logger.info("baseDao.findProductsByTeam() is failed in ProductServiceImpl", e);
            throw new ServiceException(e);
        }
        return products;
    }
}
