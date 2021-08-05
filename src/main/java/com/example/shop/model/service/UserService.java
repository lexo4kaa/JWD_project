package com.example.shop.model.service;

import com.example.shop.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers() throws ServiceException;
    List<User> findUsersByPartOfNickname(String nickname) throws ServiceException;
    Optional<User> findUserByNickname(String nickname) throws ServiceException;
    Optional<User> findUserById(int userId) throws ServiceException;
    Optional<String> findUserRole(String login) throws ServiceException;
    boolean authorizeUser(String login, String password) throws ServiceException;
    boolean registerUser(String name, String surname, String nickname, String password,
                        String dob, String phone, String email, String role) throws ServiceException;
    void addUserToBlacklist(int userId, String banReason) throws ServiceException;
    void deleteUserFromBlacklist(int userId) throws ServiceException;
    boolean updateUser(String name, String surname, String nickname, String dob,
                    String phone, String email, int userId) throws ServiceException;
    boolean changePassword(String userNickname, String oldPassword, String newPassword) throws ServiceException;
    boolean isBanned(String userNickname) throws ServiceException;
    void changeRole(int userId, String newRole) throws ServiceException;
}
