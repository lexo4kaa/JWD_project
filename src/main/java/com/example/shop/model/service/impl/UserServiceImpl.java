package com.example.shop.model.service.impl;

import com.example.shop.model.dao.DaoException;
import com.example.shop.entity.User;
import com.example.shop.model.dao.UserDao;
import com.example.shop.model.dao.impl.UserDaoImpl;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.UserService;
import com.example.shop.util.PasswordEncryptor;
import com.example.shop.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();
    private final UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public List<User> findAllUsers() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAllUsers();
        } catch (DaoException e) {
            logger.error("userDao.findAllUsers() is failed in UserServiceImpl", e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public User findUserByNickname(String nickname) throws ServiceException {
        User user = new User();
        if (UserValidator.isLoginCorrect(nickname)) {
            try {
                user = userDao.findUserByNickname(nickname);
            } catch (DaoException e) {
                logger.error("userDao.findUserByNickname(" + nickname + ") is failed in UserServiceImpl", e);
                throw new ServiceException(e);
            }
        }
        return user;
    }

    @Override
    public List<User> findUsersByPartOfNickname(String nickname) throws ServiceException {
        List<User> users = new ArrayList<>();
        if (UserValidator.isLoginCorrect(nickname)) {
            try {
                users = userDao.findUsersByPartOfNickname(nickname);
            } catch (DaoException e) {
                logger.error("userDao.findUsersByNickname(" + nickname + ") is failed in UserServiceImpl", e);
                throw new ServiceException(e);
            }
        }
        return users;
    }

    @Override
    public Optional<String> findUserRole(String login) throws ServiceException {
        Optional<String> role;
        try {
            role = userDao.findUserRole(login);
        } catch (DaoException e) {
            logger.error("userDao.findUserRole(" + login + ") is failed in UserServiceImpl", e);
            throw new ServiceException(e);
        }
        return role;
    }

    @Override
    public boolean authorizeUser(String login, String password) throws ServiceException {
        PasswordEncryptor encryptor = new PasswordEncryptor();
        String findPassword = "";
        String encPassword = encryptor.encryptPassword(password);
        if (UserValidator.isLoginCorrect(login) && UserValidator.isPasswordCorrect(password)) {
            try {
                findPassword = userDao.findPasswordByNickname(login);
            } catch (DaoException e) {
                logger.error("userDao.findPasswordByNickname(" + login + ") is failed in UserServiceImpl", e);
                throw new ServiceException(e);
            }
        }
        return findPassword.equals(encPassword);
    }

    @Override
    public boolean registerUser(String name, String surname, String nickname, String password,
                                  String dob, String phone, String email, String role) throws ServiceException {
        boolean flag = false;
        if (UserValidator.isLoginCorrect(nickname) && UserValidator.isPasswordCorrect(password) &&
                UserValidator.isEmailCorrect(email) && UserValidator.isPhoneCorrect(phone)) {
            try {
                userDao.addNewUser(name, surname, nickname, password, dob, phone, email, role);
                flag = true;
            } catch (DaoException e) {
                logger.error("userDao.registerUser is failed in UserServiceImpl", e);
                throw new ServiceException(e);
            }
        }
        return flag;
    }

    @Override
    public void deleteUser(int userId) throws ServiceException {
        try {
            userDao.deleteUser(userId);
        } catch (DaoException e) {
            logger.error("userDao.deleteUser(" + userId + ") is failed in UserServiceImpl", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void addUserToBlacklist(int userId, String banReason) throws ServiceException {
        try {
            userDao.addUserToBlacklist(userId, banReason);
            userDao.changeIsBannedPropertyOnTrue(userId);
        } catch (DaoException e) {
            logger.error("adding user to blacklist is failed in UserServiceImpl", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUserFromBlacklist(int userId) throws ServiceException {
        try {
            userDao.deleteUserFromBlacklist(userId);
            userDao.changeIsBannedPropertyOnFalse(userId);
        } catch (DaoException e) {
            logger.error("deleting user to blacklist is failed in UserServiceImpl", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUser(String name, String surname, String nickname, String dob,
                                String phone, String email, int userId) throws ServiceException {
        boolean flag = false;
        if (UserValidator.isLoginCorrect(nickname) && UserValidator.isEmailCorrect(email)
                && UserValidator.isPhoneCorrect(phone)) {
            try {
                userDao.updateUser(name, surname, nickname, dob, phone, email, userId);
                flag = true;
            } catch (DaoException e) {
                logger.error("userDao.updateUser is failed in UserServiceImpl", e);
                throw new ServiceException(e);
            }
        }
        return flag;
    }

    @Override
    public boolean changePassword(String userNickname, String oldPassword, String newPassword) throws ServiceException {
        boolean flag = false;
        if (UserValidator.isPasswordCorrect(newPassword) && authorizeUser(userNickname, oldPassword)) {
            try {
                int userId = findUserByNickname(userNickname).getUserId();
                userDao.changePassword(userId, newPassword);
                flag = true;
            } catch (DaoException e) {
                logger.error("userDao.changePassword is failed in UserServiceImpl", e);
                throw new ServiceException(e);
            }
        }
        return flag;
    }

    @Override
    public boolean isBanned(String userNickname) throws ServiceException {
        boolean flag = false;
        if (UserValidator.isLoginCorrect(userNickname)) {
            try {
                flag = userDao.isBanned(userNickname);
            } catch (DaoException e) {
                logger.error("userDao.isBanned is failed in UserServiceImpl", e);
                throw new ServiceException(e);
            }
        }
        return flag;
    }
}
