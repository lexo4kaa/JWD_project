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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();
    private final UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public List<User> findAllUsers() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAllUsers();
        } catch (DaoException e) {
            logger.info("userDao.findAllUsers() is failed in UserServiceImpl", e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public List<User> findUsersByNickname(String nickname) throws ServiceException {
        List<User> users = new ArrayList<>();
        if (UserValidator.isLoginCorrect(nickname)) {
            try {
                users = userDao.findUsersByNickname(nickname);
            } catch (DaoException e) {
                logger.info("userDao.findUsersByNickname(" + nickname + ") is failed in UserServiceImpl", e);
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
            logger.info("userDao.findUserRole(" + login + ") is failed in UserServiceImpl", e);
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
                logger.info("userDao.findPasswordByNickname(" + login + ") is failed in UserServiceImpl", e);
                throw new ServiceException(e);
            }
        }
        return findPassword.equals(encPassword);
    }

    @Override
    public boolean registerUser(String name, String surname, String nickname, String password,
                                  String dob, String phone, String email) throws ServiceException {
        boolean flag = false;
        if (UserValidator.isLoginCorrect(nickname) && UserValidator.isPasswordCorrect(password) &&
                UserValidator.isEmailCorrect(email) && UserValidator.isPhoneCorrect(phone)) {
            try {
                userDao.addNewUser(name, surname, nickname, password, dob, phone, email);
                flag = true;
            } catch (DaoException e) {
                logger.info("userDao.registerUser is failed in UserServiceImpl", e);
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
            logger.info("userDao.deleteUser(" + userId + ") is failed in UserServiceImpl", e);
            throw new ServiceException(e);
        }
    }
}
