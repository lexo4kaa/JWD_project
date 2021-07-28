package com.example.shop.model.dao.impl;

import com.example.shop.entity.Product;
import com.example.shop.model.pool.ConnectionPoolException;
import com.example.shop.model.pool.CustomConnectionPool;
import com.example.shop.model.dao.DaoException;
import com.example.shop.model.dao.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.shop.model.dao.ProductColumn.*;

public class ProductDaoImpl implements ProductDao {
    private static final ProductDao instance = new ProductDaoImpl();

    private static final String SQL_FIND_ALL_PRODUCTS = "SELECT product_id,product_type, product_team, product_year," +
                                                        "product_specification, product_price, " +
                                                        "product_path FROM products";
    private static final String SQL_FIND_PRODUCTS_BY_TYPE = "SELECT product_id,product_type, product_team, product_year," +
                                                            "product_specification,product_price,product_path " +
                                                            "FROM products WHERE product_type LIKE ?";
    private static final String SQL_FIND_PRODUCTS_BY_TEAM_AND_TYPE = "SELECT product_id,product_type, product_team, product_year," +
                                                            "product_specification,product_price,product_path " +
                                                            "FROM products WHERE product_team = ? && product_type LIKE ?";
    private static final String SQL_FIND_PRODUCTS_BY_IDS =  "SELECT product_id,product_type, product_team, product_year," +
                                                            "product_specification, product_price, product_path " +
                                                            "FROM products WHERE product_id = ?";

    private ProductDaoImpl(){}

    public static ProductDao getInstance(){
        return instance;
    }

    @Override
    public List<Product> findAllProducts() throws DaoException {
        List<Product> products = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PRODUCTS);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()){
                products.add(createProductsFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding products", e);
        }
        return products;
    }

    @Override
    public List<Product> findProductsByType(String type) throws DaoException {
        List<Product> products = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRODUCTS_BY_TYPE)) {
            statement.setString(1, type);
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
    public List<Product> findProductsByTeamAndType(String team, String type) throws DaoException {
        List<Product> products = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRODUCTS_BY_TEAM_AND_TYPE)) {
            statement.setString(1, team);
            statement.setString(2, type);
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
    public Product findProductById(int productId) throws DaoException {
        Product product = new Product();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRODUCTS_BY_IDS)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                product = createProductsFromResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding products", e);
        }
        return product;
    }

    @Override
    public double findPriceById(int productId) throws DaoException {
        Product product = new Product();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRODUCTS_BY_IDS)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                product = createProductsFromResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding price", e);
        }
        return product.getPrice();
    }

    private Product createProductsFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        int productId = resultSet.getInt(PRODUCT_ID);
        String type = resultSet.getString(PRODUCT_TYPE);
        String team = resultSet.getString(PRODUCT_TEAM);
        int year = resultSet.getInt(PRODUCT_YEAR);
        String specification = resultSet.getString(PRODUCT_SPECIFICATION);
        double price = resultSet.getDouble(PRODUCT_PRICE);
        String path = resultSet.getString(PRODUCT_PATH);
        product.setProductId(productId);
        product.setType(type);
        product.setTeam(team);
        product.setYear(year);
        product.setSpecification(specification);
        product.setPrice(price);
        product.setPath(path);
        return product;
    }
}
