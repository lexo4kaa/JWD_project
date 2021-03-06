package com.example.shop.model.dao.impl;

import com.example.shop.entity.User;
import com.example.shop.model.pool.ConnectionPoolException;
import com.example.shop.model.pool.CustomConnectionPool;
import com.example.shop.model.dao.DaoException;
import com.example.shop.model.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.shop.model.dao.UserColumn.*;

public class UserDaoImpl implements UserDao {
    private static final UserDao instance = new UserDaoImpl();
    private static final char PERCENT = '%';
    private static final String SQL_FIND_ALL_USERS = "SELECT user_id,user_name,user_surname,user_nickname,user_password," +
                                                     "user_DOB,user_phone_number,user_email,user_role,is_banned FROM users";
    private static final String SQL_FIND_USER_BY_NICKNAME = "SELECT user_id,user_name,user_surname,user_nickname," +
                                                            "user_password,user_DOB,user_phone_number,user_email," +
                                                            "user_role,is_banned FROM users WHERE user_nickname = ?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT user_id,user_name,user_surname,user_nickname," +
                                                        "user_password,user_DOB,user_phone_number,user_email," +
                                                        "user_role,is_banned FROM users WHERE user_id = ?";
    private static final String SQL_FIND_USER_BY_PART_OF_NICKNAME = "SELECT user_id,user_name,user_surname,user_nickname," +
                                                            "user_password,user_DOB,user_phone_number,user_email," +
                                                            "user_role,is_banned FROM users WHERE user_nickname LIKE ?";
    private static final String SQL_FIND_ROLE_BY_NICKNAME = "SELECT user_role FROM users WHERE user_nickname = ?";
    private static final String SQL_FIND_PASSWORD_BY_NICKNAME = "SELECT user_password FROM users WHERE user_nickname = ?";
    private static final String SQL_ADD_USER = "INSERT INTO users (user_name,user_surname,user_nickname,user_password," +
                                                "user_DOB,user_phone_number,user_email,user_role) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_ADD_USER_TO_BLACKLIST = "INSERT INTO blacklist (ref_user_id,ban_reason) VALUES (?,?)";
    private static final String SQL_DELETE_USER_FROM_BLACKLIST = "DELETE FROM blacklist WHERE ref_user_id = ?";
    private static final String SQL_CHANGE_IS_BANNED_ON_TRUE = "UPDATE users SET is_banned = true WHERE user_id = ?";
    private static final String SQL_CHANGE_IS_BANNED_ON_FALSE = "UPDATE users SET is_banned = false WHERE user_id = ?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET user_name=?,user_surname=?,user_nickname=?," +
                                                    "user_DOB=?,user_phone_number=?,user_email=? WHERE user_id = ?";
    private static final String SQL_CHANGE_PASSWORD = "UPDATE users SET user_password = ? WHERE user_id = ?";
    private static final String SQL_FIND_IS_BANNED_BY_NICKNAME = "SELECT is_banned FROM users WHERE user_nickname = ?";
    private static final String SQL_CHANGE_ROLE = "UPDATE users SET user_role = ? WHERE user_id = ?";

    private UserDaoImpl() {}

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
    public Optional<User> findUserByNickname(String nickname) throws DaoException {
        Optional<User> user = Optional.empty();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_NICKNAME)) {
            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = Optional.of(createUserFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding user", e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserById(int userId) throws DaoException {
        Optional<User> user = Optional.empty();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = Optional.of(createUserFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding user", e);
        }
        return user;
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
                role = Optional.of(resultSet.getString(USER_ROLE));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding user", e);
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
                password = resultSet.getString(USER_PASSWORD);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding user", e);
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
    public void addUserToBlacklist(int userId, String banReason) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER_TO_BLACKLIST)) {
            statement.setInt(1, userId);
            statement.setString(2, banReason);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while adding user to blacklist", e);
        }
    }

    @Override
    public void deleteUserFromBlacklist(int userId) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_FROM_BLACKLIST)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while deleting user from blacklist", e);
        }
    }

    @Override
    public void changeIsBannedPropertyOnTrue(int userId) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_IS_BANNED_ON_TRUE)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while changing property 'isBanned' on true", e);
        }
    }

    @Override
    public void changeIsBannedPropertyOnFalse(int userId) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_IS_BANNED_ON_FALSE)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while changing property 'isBanned' on false", e);
        }
    }

    @Override
    public void updateUser(String name, String surname, String nickname, String dob,
                            String phone, String email, int userId) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, nickname);
            statement.setString(4, dob);
            statement.setString(5, phone);
            statement.setString(6, email);
            statement.setInt(7, userId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while updating user", e);
        }
    }

    @Override
    public void changePassword(int userId, String newPassword) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while changing password", e);
        }
    }

    @Override
    public void changeRole(int userId, String newRole) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_ROLE)) {
            statement.setString(1, newRole);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while changing role", e);
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        int userId = resultSet.getInt(USER_ID);
        String name = resultSet.getString(USER_NAME);
        String surname = resultSet.getString(USER_SURNAME);
        String nickname = resultSet.getString(USER_NICKNAME);
        String password = resultSet.getString(USER_PASSWORD);
        LocalDate dob = resultSet.getDate(USER_DOB).toLocalDate();
        String phone = resultSet.getString(USER_PHONE);
        String email = resultSet.getString(USER_EMAIL);
        String role = resultSet.getString(USER_ROLE);
        boolean isBanned = resultSet.getBoolean(IS_BANNED);
        user.setUserId(userId);
        user.setName(name);
        user.setSurname(surname);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setDob(dob);
        user.setPhone(phone);
        user.setEmail(email);
        user.setRole(role);
        user.setIsBanned(isBanned);
        return user;
    }
}
