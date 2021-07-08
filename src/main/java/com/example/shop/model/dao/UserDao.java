package com.example.shop.model.dao;

import com.example.shop.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAllUsers() throws DaoException;
    User findUserByNickname(String nickname) throws DaoException;
    List<User> findUsersByPartOfNickname(String nickname) throws DaoException;
    Optional<String> findUserRole(String nickname) throws DaoException;
    String findPasswordByNickname(String nickname) throws DaoException;
    void addNewUser(String name, String surname, String nickname, String password,
                    String dob, String phone, String email, String role) throws DaoException;
    void deleteUser(int userId) throws DaoException;
    void addUserToBlacklist(int userId, String banReason) throws DaoException;
    void deleteUserFromBlacklist(int userId) throws DaoException;
    void changeIsBannedPropertyOnTrue(int userId) throws DaoException;
    void changeIsBannedPropertyOnFalse(int userId) throws DaoException;
    void updateUser(String name, String surname, String nickname, String dob,
                    String phone, String email, int userId) throws DaoException;
    void changePassword(int userId, String newPassword) throws DaoException;
}
