package com.example.final_project_shop.model.dao.impl;

import com.example.final_project_shop.entity.Product;
import com.example.final_project_shop.model.dao.BaseDao;
import com.example.final_project_shop.model.dao.ProductColumn;
import com.example.final_project_shop.model.dao.UsersColumn;
import com.example.final_project_shop.model.dao.DaoException;
import com.example.final_project_shop.entity.User;
import com.example.final_project_shop.model.connection.ConnectionPoolException;
import com.example.final_project_shop.model.connection.CustomConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseDaoImpl implements BaseDao {
    private static final char PERCENT = '%';
    private static final BaseDaoImpl instance = new BaseDaoImpl();
    private static final String SQL_FIND_ALL_USERS = "SELECT user_id,user_name,user_surname,user_nickname,user_password," +
                                                     "user_DOB,user_phone_number,user_email,user_role FROM users";
    private static final String SQL_FIND_ALL_PRODUCTS = "SELECT product_id,product_type, product_team, product_year, " +
                                                        "product_specification, product_quantity, product_price, " +
                                                        "product_path FROM products";
    private static final String SQL_FIND_PRODUCTS_BY_TEAM = "SELECT product_id,product_type, product_team, product_year," +
                                                            "product_specification,product_quantity, product_price, " +
                                                            "product_path FROM products WHERE product_team = ?";
    private static final String SQL_FIND_USER_BY_NICKNAME = "SELECT user_id,user_name,user_surname,user_nickname," +
                                                            "user_password,user_DOB,user_phone_number,user_email," +
                                                            "user_role FROM users WHERE user_nickname LIKE ?";
    private static final String SQL_FIND_ROLE_BY_NICKNAME = "SELECT user_role FROM users WHERE user_nickname = ?";
    private static final String SQL_FIND_PASSWORD_BY_NICKNAME = "SELECT user_password FROM users WHERE user_nickname = ?";
    private static final String SQL_ADD_USER = "INSERT INTO users (user_name,user_surname,user_nickname,user_password," +
                                                "user_DOB, user_phone_number, user_email) VALUES (?,?,?,?,?,?,?)";


    private BaseDaoImpl(){}

    public static BaseDao getInstance(){
        return instance;
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                users.add(createUserFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding users", e);
        }
        return users;
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
    public List<User> findUsersByNickname(String nickname) throws DaoException {
        List<User> users = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_NICKNAME)) {
            statement.setString(1, nickname + PERCENT);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                users.add(createUserFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding users", e);
        }
        return users;
    }

    @Override
    public String findUserRole(String nickname) throws DaoException {
        String role = "";
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ROLE_BY_NICKNAME)) {
            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                role = resultSet.getString(UsersColumn.USER_ROLE);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding users", e);
        }
        return role;
    }

    @Override
    public String findPasswordByNickname(String nickname) throws DaoException {
        String password = "";
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_PASSWORD_BY_NICKNAME)) {
            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                password = resultSet.getString(UsersColumn.USER_PASSWORD);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding users", e);
        }
        return password;
    }

    @Override
    public void addNewUser(String name, String surname, String nickname, String password,
                           String dob, String phone, String email) throws DaoException{
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, nickname);
            statement.setString(4, password);
            statement.setString(5, dob);
            statement.setString(6, phone);
            statement.setString(7, email);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while adding user", e);
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        int userId = resultSet.getInt(UsersColumn.USER_ID);
        String name = resultSet.getString(UsersColumn.USER_NAME);
        String surname = resultSet.getString(UsersColumn.USER_SURNAME);
        String nickname = resultSet.getString(UsersColumn.USER_NICKNAME);
        String password = resultSet.getString(UsersColumn.USER_PASSWORD);
        Date dob = resultSet.getDate(UsersColumn.USER_DOB);
        String phone = resultSet.getString(UsersColumn.USER_PHONE);
        String email = resultSet.getString(UsersColumn.USER_EMAIL);
        String role = resultSet.getString(UsersColumn.USER_ROLE);
        user.setUserId(userId);
        user.setName(name);
        user.setSurname(surname);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setDob(dob);
        user.setPhone(phone);
        user.setEmail(email);
        user.setRole(role);
        return user;
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
