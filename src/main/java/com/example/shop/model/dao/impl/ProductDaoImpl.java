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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private static final String SQL_FIND_PRODUCTS_BY_ID =  "SELECT product_id,product_type, product_team, product_year," +
                                                            "product_specification, product_price, product_path " +
                                                            "FROM products WHERE product_id = ?";
    private static final String SQL_ADD_PRODUCT_TO_FAVOURITES = "INSERT INTO favourite_products(ref_user_id,ref_product_id) " +
                                                                "VALUES (?,?)";
    private static final String SQL_DELETE_PRODUCT_FROM_FAVOURITES = "DELETE FROM favourite_products WHERE " +
                                                                    "ref_user_id = ? && ref_product_id = ?";
    private static final String SQL_FIND_FAVOURITE_PRODUCT = "SELECT ref_user_id,ref_product_id FROM favourite_products" +
                                                            " WHERE ref_user_id = ? && ref_product_id = ?";
    private static final String SQL_FIND_FAVOURITE_PRODUCTS = "SELECT ref_product_id FROM favourite_products" +
                                                            " WHERE ref_user_id = ?";

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
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRODUCTS_BY_ID)) {
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
    public boolean isFavouriteProduct(int userId, int productId) throws DaoException {
        boolean isFavouriteProduct = false;
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_FAVOURITE_PRODUCT)) {
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                isFavouriteProduct = true;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while checking status of favourite product", e);
        }
        return isFavouriteProduct;
    }

    @Override
    public void addProductToFavourites(int userId, int productId) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_PRODUCT_TO_FAVOURITES)) {
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while adding product to favourites", e);
        }
    }

    @Override
    public void deleteProductFromFavourites(int userId, int productId) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PRODUCT_FROM_FAVOURITES)) {
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while deleting product from favourites", e);
        }
    }

    @Override
    public Set<Integer> findFavouriteProducts(int userId) throws DaoException {
        Set<Integer> productsIds = new HashSet<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_FAVOURITE_PRODUCTS)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                productsIds.add(resultSet.getInt(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding favourite products", e);
        }
        return productsIds;
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
