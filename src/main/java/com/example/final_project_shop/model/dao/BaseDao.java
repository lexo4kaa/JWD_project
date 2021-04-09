package com.example.final_project_shop.model.dao;

import com.example.final_project_shop.entity.User;

import java.util.List;

public interface BaseDao {
    List<User> findAllUsers() throws DaoException;
    List<User> findUsersByNickname(String nickname) throws DaoException;
    String findPasswordByNickname(String nickname) throws DaoException;
    void addNewUser(String name, String surname, String nickname, String password,
                    String dob, String phone, String email) throws DaoException;
}