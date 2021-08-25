package com.example.shop.model.dao;

import com.example.shop.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface for working with users
 */
public interface UserDao {
    /**
     * Find all users.
     *
     * @return list of users
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    List<User> findAllUsers() throws DaoException;

    /**
     * Find user by nickname (optional).
     *
     * @param nickname user nickname
     * @return optional user
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    Optional<User> findUserByNickname(String nickname) throws DaoException;

    /**
     * Find user by id (optional).
     *
     * @param userId user id
     * @return optional user
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    Optional<User> findUserById(int userId) throws DaoException;

    /**
     * Find users by part of nickname.
     *
     * @param nickname user nickname
     * @return list of users
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    List<User> findUsersByPartOfNickname(String nickname) throws DaoException;

    /**
     * Find user role (optional).
     *
     * @param nickname user nickname
     * @return optional user role
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    Optional<String> findUserRole(String nickname) throws DaoException;

    /**
     * Find password by user nickname.
     *
     * @param nickname user nickname
     * @return user password
     * @throws DaoException the dao exception
     */
    String findPasswordByNickname(String nickname) throws DaoException;

    /**
     * Add new user.
     *
     * @param name     user name
     * @param surname  user surname
     * @param nickname user nickname
     * @param password user password
     * @param dob      user dob
     * @param phone    user phone
     * @param email    user email
     * @param role     user role
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void addNewUser(String name, String surname, String nickname, String password,
                    String dob, String phone, String email, String role) throws DaoException;

    /**
     * Add user to blacklist.
     *
     * @param userId    user id
     * @param banReason ban reason
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void addUserToBlacklist(int userId, String banReason) throws DaoException;

    /**
     * Delete user from blacklist.
     *
     * @param userId user id
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void deleteUserFromBlacklist(int userId) throws DaoException;

    /**
     * Change isBanned property on true (ban user)
     *
     * @param userId user id
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void changeIsBannedPropertyOnTrue(int userId) throws DaoException;

    /**
     * Change isBanned property on false (unban user)
     *
     * @param userId user id
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void changeIsBannedPropertyOnFalse(int userId) throws DaoException;

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
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void updateUser(String name, String surname, String nickname, String dob,
                    String phone, String email, int userId) throws DaoException;

    /**
     * Change password.
     *
     * @param userId      user id
     * @param newPassword new password
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void changePassword(int userId, String newPassword) throws DaoException;

    /**
     * Change role.
     *
     * @param userId  user id
     * @param newRole new role
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void changeRole(int userId, String newRole) throws DaoException;
}
