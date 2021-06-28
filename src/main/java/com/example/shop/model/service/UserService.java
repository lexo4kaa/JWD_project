package com.example.shop.model.service;

import com.example.shop.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers() throws ServiceException;
    List<User> findUsersByNickname(String nickname) throws ServiceException;
    Optional<String> findUserRole(String login) throws ServiceException;
    boolean authorizeUser(String login, String password) throws ServiceException;
    boolean registerUser(String name, String surname, String nickname, String password,
                                String dob, String phone, String email) throws ServiceException;
    void deleteUser(int userId) throws ServiceException;
}
