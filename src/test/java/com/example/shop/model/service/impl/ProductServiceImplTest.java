package com.example.shop.model.service.impl;

import com.example.shop.entity.Product;
import com.example.shop.model.dao.DaoException;
import com.example.shop.model.dao.ProductDao;
import com.example.shop.model.dao.impl.ProductDaoImpl;
import com.example.shop.model.service.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    @Mock
    private ProductDao productDao;
    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;
    private List<Product> products;

    @BeforeMethod
    public void setUp() {
        productDao = mock(ProductDaoImpl.class);
        productService = new ProductServiceImpl(productDao);

        product1 = new Product();
        product1.setProductId(1);
        product1.setType("jersey");
        product1.setTeam("PSG");
        product1.setYear(2020);
        product1.setSpecification("good jersey for good person");
        product1.setPrice(48.99);
        product1.setPath("/images/products/jersey_psg_20.jpg");

        product2 = new Product();
        product2.setProductId(2);
        product2.setType("cap");
        product2.setTeam("Milan");
        product2.setYear(2021);
        product2.setSpecification("simple and beautiful cap");
        product2.setPrice(11.99);
        product2.setPath("/images/products/cap_milan_21.jpg");

        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
    }

    @AfterMethod
    public void tearDown() {
        productDao = null;
        productService = null;
        product1 = null;
        product2 = null;
        products = null;
    }

    @Test
    public void testFindAllProducts() throws ServiceException, DaoException {
        List<Product> expected = products;
        when(productDao.findAllProducts()).thenReturn(expected);
        List<Product> actual = productService.findAllProducts();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindProductsByType() throws ServiceException, DaoException {
        String type = "cap";
        List<Product> expected = List.of(product2);
        when(productDao.findProductsByType(type)).thenReturn(expected);
        List<Product> actual = productService.findProductsByType(type);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindProductsByTypeWithEmptyResult() throws ServiceException, DaoException {
        String type = "uniform";
        List<Product> expected = new ArrayList<>();
        when(productDao.findProductsByType(type)).thenReturn(expected);
        List<Product> actual = productService.findProductsByType(type);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindProductsByTeamAndType() throws ServiceException, DaoException {
        String team = "Milan";
        String type = "cap";
        List<Product> expected = List.of(product2);
        when(productDao.findProductsByTeamAndType(team, type)).thenReturn(expected);
        List<Product> actual = productService.findProductsByTeamAndType(team, type);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindProductsByTeamAndTypeWithEmptyResult() throws ServiceException, DaoException {
        String team = "Barcelona";
        String type = "cap";
        List<Product> expected = new ArrayList<>();
        when(productDao.findProductsByTeamAndType(team, type)).thenReturn(expected);
        List<Product> actual = productService.findProductsByTeamAndType(team, type);
        Assert.assertEquals(actual, expected);
    }
}