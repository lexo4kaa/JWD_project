package com.example.final_project_shop.model.service;

import com.example.final_project_shop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts() throws ServiceException;
    List<Product> findProductsByTeam(String team) throws ServiceException;
}
