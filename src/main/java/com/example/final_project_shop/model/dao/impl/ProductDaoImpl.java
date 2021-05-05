package com.example.final_project_shop.model.dao.impl;

import com.example.final_project_shop.entity.Product;
import com.example.final_project_shop.model.connection.ConnectionPoolException;
import com.example.final_project_shop.model.connection.CustomConnectionPool;
import com.example.final_project_shop.model.dao.DaoException;
import com.example.final_project_shop.model.dao.ProductColumn;
import com.example.final_project_shop.model.dao.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {
    private static final ProductDao instance = new ProductDaoImpl();
    private static final String SQL_FIND_ALL_PRODUCTS = "SELECT product_id,product_type, product_team, product_year, " +
            "product_specification, product_quantity, product_price, " +
            "product_path FROM products";
    private static final String SQL_FIND_PRODUCTS_BY_TEAM = "SELECT product_id,product_type, product_team, product_year," +
            "product_specification,product_quantity, product_price, " +
            "product_path FROM products WHERE product_team = ?";
    private static final String SQL_ADD_PRODUCT_TO_CART = "INSERT INTO users (user_name,user_surname,user_nickname,user_password," +
            "user_DOB, user_phone_number, user_email) VALUES (?,?,?,?,?,?,?)";

    private ProductDaoImpl(){}

    public static ProductDao getInstance(){
        return instance;
    }

    @Override
    public List<Product> findAllProducts() throws DaoException {
        List<Product> products = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PRODUCTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                products.add(createProductsFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding products", e);
        }
        return products;
    }

    @Override
    public List<Product> findProductsByTeam(String team) throws DaoException {
        List<Product> products = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRODUCTS_BY_TEAM)) {
            statement.setString(1, team);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                products.add(createProductsFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding products", e);
        }
        return products;
    }



    @Override
    public void addProductToCart(Map<Integer, Integer> cast, int productId) throws DaoException {
        if(cast.containsKey(productId)) {
            System.out.println(productId);
            cast.put(productId, cast.get(productId) + 1);
        } else {
            cast.put(productId, 1);
        }
    }

    private Product createProductsFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        int productId = resultSet.getInt(ProductColumn.PRODUCT_ID);
        String type = resultSet.getString(ProductColumn.PRODUCT_TYPE);
        String team = resultSet.getString(ProductColumn.PRODUCT_TEAM);
        int year = resultSet.getInt(ProductColumn.PRODUCT_YEAR);
        String specification = resultSet.getString(ProductColumn.PRODUCT_SPECIFICATION);
        int quantity = resultSet.getInt(ProductColumn.PRODUCT_QUANTITY);
        double price = resultSet.getDouble(ProductColumn.PRODUCT_PRICE);
        String path = resultSet.getString(ProductColumn.PRODUCT_PATH);
        product.setProductId(productId);
        product.setType(type);
        product.setTeam(team);
        product.setYear(year);
        product.setSpecification(specification);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setPath(path);
        return product;
    }
}
