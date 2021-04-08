package com.example.final_project_shop.dao;

import com.example.final_project_shop.entity.Entity;
import com.example.final_project_shop.entity.User;

import java.util.List;

public interface BaseDao <K, T extends Entity> { //todo use Entity  (26.03 51:00)
    List<User> findAllUsers() throws DaoException;
    List<User> findUsersByNickname(String nickname) throws DaoException;
    public String findPasswordByNickname(String nickname) throws DaoException;
    public void addNewUser(String name, String surname, String nickname, String password,
                           String dob, String phone, String email) throws DaoException;
}