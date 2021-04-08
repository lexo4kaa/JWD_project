package com.example.final_project_shop.dao.impl;

import com.example.final_project_shop.dao.BaseDao;
import com.example.final_project_shop.dao.UsersColumn;
import com.example.final_project_shop.dao.DaoException;
import com.example.final_project_shop.entity.User;
import com.example.final_project_shop.pool.ConnectionPoolException;
import com.example.final_project_shop.pool.CustomConnectionPool;

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
    private static final String SQL_FIND_ALL = "SELECT user_id,user_name,user_surname,user_nickname,user_password," +
                                            "user_DOB,user_phone_number,user_email,user_role FROM users";
    private static final String SQL_FIND_BY_NICKNAME = "SELECT user_id,user_name,user_surname,user_nickname,user_password," +
                                                    "user_DOB,user_phone_number,user_email,user_role FROM users " +
                                                    "WHERE user_nickname LIKE ?";
    private static final String SQL_FIND_PASSWORD_BY_NICKNAME = "SELECT user_password FROM users WHERE user_nickname = ?";
    private static final String SQL_ADD_USER = "INSERT INTO users (user_name, user_surname, user_nickname, user_password," +
                                                "user_DOB, user_phone_number, user_email, user_role) VALUES " +
                                                "(?,?,?,?,?,?,?,client)";


    private BaseDaoImpl(){}

    public static BaseDao getInstance(){
        return instance;
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
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
    public List<User> findUsersByNickname(String nickname) throws DaoException {
        List<User> users = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NICKNAME);
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
    public String findPasswordByNickname(String nickname) throws DaoException {
        String password = "";
        try(Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_PASSWORD_BY_NICKNAME);
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
                           String dob, String phone,String email) throws DaoException{
        try(Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, nickname);
            statement.setString(4, password);
            statement.setString(5, dob);
            statement.setString(6, phone);
            statement.setString(7, email);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding users", e);
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
}
