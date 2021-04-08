package com.example.final_project_shop.service;

import com.example.final_project_shop.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUsers() throws ServiceException;
    public List<User> findUsersByNickname(String nickname) throws ServiceException;
    public boolean authorizeUser(String login, String password) throws ServiceException;
    public boolean registerUser(String name, String surname, String nickname, String password,
                                String dob, String phone, String email) throws ServiceException;
}
