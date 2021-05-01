package com.example.final_project_shop.model.dao;

import com.example.final_project_shop.entity.Product;
import com.example.final_project_shop.entity.User;
import org.apache.logging.log4j.core.appender.db.DbAppenderLoggingException;

import java.util.List;

public interface BaseDao {
    List<User> findAllUsers() throws DaoException;
    List<Product> findAllProducts() throws DaoException;
    List<Product> findProductsByTeam(String team) throws DaoException;
    List<User> findUsersByNickname(String nickname) throws DaoException;
    String findUserRole(String nickname) throws DaoException;
    String findPasswordByNickname(String nickname) throws DaoException;
    void addNewUser(String name, String surname, String nickname, String password,
                    String dob, String phone, String email) throws DaoException;
}