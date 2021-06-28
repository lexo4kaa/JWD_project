package com.example.shop.model.dao;

import com.example.shop.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAllUsers() throws DaoException;
    List<User> findUsersByNickname(String nickname) throws DaoException;
    Optional<String> findUserRole(String nickname) throws DaoException;
    String findPasswordByNickname(String nickname) throws DaoException;
    void addNewUser(String name, String surname, String nickname, String password,
                    String dob, String phone, String email) throws DaoException;
    void deleteUser(int userId) throws DaoException;
}
