package com.example.shop.model.service;

import com.example.shop.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface for working with users
 */
public interface UserService {
    /**
     * Find all users.
     *
     * @return list of users
     * @throws ServiceException if DaoException occur
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Find users by part of nickname.
     *
     * @param nickname user nickname
     * @return list of users
     * @throws ServiceException if DaoException occur
     */
    List<User> findUsersByPartOfNickname(String nickname) throws ServiceException;

    /**
     * Find user by nickname (optional).
     *
     * @param nickname user nickname
     * @return optional user
     * @throws ServiceException if DaoException occur
     */
    Optional<User> findUserByNickname(String nickname) throws ServiceException;

    /**
     * Find user by id (optional).
     *
     * @param userId user id
     * @return optional user
     * @throws ServiceException if DaoException occur
     */
    Optional<User> findUserById(int userId) throws ServiceException;

    /**
     * Find user role (optional).
     *
     * @param login user nickname
     * @return optional user role
     * @throws ServiceException if DaoException occur
     */
    Optional<String> findUserRole(String login) throws ServiceException;

    /**
     * Authorize user.
     *
     * @param login    login
     * @param password password
     * @return user is authorized or not
     * @throws ServiceException if DaoException occur
     */
    boolean authorizeUser(String login, String password) throws ServiceException;

    /**
     * Register user.
     *
     * @param name     user name
     * @param surname  user surname
     * @param nickname user nickname
     * @param password user password
     * @param dob      user dob
     * @param phone    user phone
     * @param email    user email
     * @param role     user role
     * @return user is registered or not
     * @throws ServiceException if DaoException occur
     */
    boolean registerUser(String name, String surname, String nickname, String password,
                        String dob, String phone, String email, String role) throws ServiceException;

    /**
     * Add user to blacklist.
     *
     * @param userId    user id
     * @param banReason ban reason
     * @throws ServiceException if DaoException occur
     */
    void addUserToBlacklist(int userId, String banReason) throws ServiceException;

    /**
     * Delete user from blacklist.
     *
     * @param userId user id
     * @throws ServiceException the service exception
     */
    void deleteUserFromBlacklist(int userId) throws ServiceException;

    /**
     * Update information about user.
     *
     * @param name     user name
     * @param surname  user surname
     * @param nickname user nickname
     * @param dob      user dob
     * @param phone    user phone
     * @param email    user email
     * @param userId   user id
     * @return information is updated or not
     * @throws ServiceException if DaoException occur
     */
    boolean updateUser(String name, String surname, String nickname, String dob,
                    String phone, String email, int userId) throws ServiceException;

    /**
     * Change password.
     *
     * @param userNickname user nickname
     * @param oldPassword  old password
     * @param newPassword  new password
     * @return password is changed or not
     * @throws ServiceException if DaoException occur
     */
    boolean changePassword(String userNickname, String oldPassword, String newPassword) throws ServiceException;

    /**
     * Change role.
     *
     * @param userId  user id
     * @param newRole new role
     * @throws ServiceException if DaoException occur
     */
    void changeRole(int userId, String newRole) throws ServiceException;

    /**
     * Exists user by nickname.
     *
     * @param nickname the nickname
     * @return user is exists or not
     * @throws ServiceException if DaoException occur
     */
    boolean existsNickname(String nickname) throws ServiceException;
}
