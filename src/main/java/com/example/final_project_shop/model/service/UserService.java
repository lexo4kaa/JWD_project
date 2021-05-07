package com.example.final_project_shop.model.service;

import com.example.final_project_shop.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers() throws ServiceException;
    List<User> findUsersByNickname(String nickname) throws ServiceException;
    String findUserRole(String login) throws ServiceException;
    boolean authorizeUser(String login, String password) throws ServiceException;
    boolean registerUser(String name, String surname, String nickname, String password,
                                String dob, String phone, String email) throws ServiceException;
    void deleteUser(int userId) throws ServiceException;
}
