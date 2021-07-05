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
}
