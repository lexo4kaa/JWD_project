package com.example.shop.model.dao.impl;

import com.example.shop.entity.User;
import com.example.shop.model.pool.ConnectionPoolException;
import com.example.shop.model.pool.CustomConnectionPool;
import com.example.shop.model.dao.DaoException;
import com.example.shop.model.dao.UserDao;
import com.example.shop.model.dao.UsersColumn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final UserDao instance = new UserDaoImpl();
    private static final char PERCENT = '%';
    private static final String SQL_FIND_ALL_USERS = "SELECT user_id,user_name,user_surname,user_nickname,user_password," +
                                                     "user_DOB,user_phone_number,user_email,user_role FROM users";
    private static final String SQL_FIND_USER_BY_NICKNAME = "SELECT user_id,user_name,user_surname,user_nickname," +
                                                            "user_password,user_DOB,user_phone_number,user_email," +
                                                            "user_role FROM users WHERE user_nickname = ?";
    private static final String SQL_FIND_USER_BY_PART_OF_NICKNAME = "SELECT user_id,user_name,user_surname,user_nickname," +
                                                            "user_password,user_DOB,user_phone_number,user_email," +
                                                            "user_role FROM users WHERE user_nickname LIKE ?";
    private static final String SQL_FIND_ROLE_BY_NICKNAME = "SELECT user_role FROM users WHERE user_nickname = ?";
    private static final String SQL_FIND_PASSWORD_BY_NICKNAME = "SELECT user_password FROM users WHERE user_nickname = ?";
    private static final String SQL_ADD_USER = "INSERT INTO users (user_name,user_surname,user_nickname,user_password," +
                                                "user_DOB,user_phone_number,user_email,user_role) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id = ?";

    public static UserDao getInstance(){
        return instance;
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()){
                users.add(createUserFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding users", e);
        }
        return users;
    }

    @Override
    public User findUserByNickname(String nickname) throws DaoException {
        List<User> users = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_NICKNAME)) {
            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                users.add(createUserFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding users", e);
        }
        return users.get(0);
    }

    @Override
    public List<User> findUsersByPartOfNickname(String nickname) throws DaoException {
        List<User> users = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_PART_OF_NICKNAME)) {
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
    public Optional<String> findUserRole(String nickname) throws DaoException {
        Optional<String> role = Optional.empty();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ROLE_BY_NICKNAME)) {
            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                role = Optional.of(resultSet.getString(UsersColumn.USER_ROLE));
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
                           String dob, String phone, String email, String role) throws DaoException{
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, nickname);
            statement.setString(4, password);
            statement.setString(5, dob);
            statement.setString(6, phone);
            statement.setString(7, email);
            statement.setString(8, role);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while adding user", e);
        }
    }

    @Override
    public void deleteUser(int userId) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while deleting user", e);
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
