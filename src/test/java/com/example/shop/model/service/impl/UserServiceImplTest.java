package com.example.shop.model.service.impl;

import com.example.shop.entity.User;
import com.example.shop.model.dao.DaoException;
import com.example.shop.model.dao.UserDao;
import com.example.shop.model.dao.impl.UserDaoImpl;
import com.example.shop.model.service.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserServiceImpl userService;

    private User user1;
    private User user2;
    private List<User> users;

    @BeforeMethod
    public void setUp() {
        userDao = mock(UserDaoImpl.class);
        userService = new UserServiceImpl(userDao);

        user1 = new User();
        user1.setUserId(1);
        user1.setName("Ivan");
        user1.setSurname("Polka");
        user1.setNickname("vanyaPolka");
        user1.setPassword("polkA123");
        user1.setDob(LocalDate.of(1998, 5, 29));
        user1.setPhone("375294851200");
        user1.setRole("client");
        user1.setIsBanned(false);

        user2 = new User();
        user2.setUserId(2);
        user2.setName("Petr");
        user2.setSurname("Laguna");
        user2.setNickname("petro");
        user2.setPassword("1kort1");
        user2.setDob(LocalDate.of(1988, 12, 2));
        user2.setPhone("375257811965");
        user2.setRole("client");
        user2.setIsBanned(false);

        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
    }

    @Test
    public void testFindAllUsers() throws ServiceException, DaoException {
        List<User> expected = users;
        when(userDao.findAllUsers()).thenReturn(expected);
        List<User> actual = userService.findAllUsers();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindUsersByPartOfNickname() throws ServiceException, DaoException {
        String nickname = "polk";
        List<User> expected = List.of(user1);
        when(userDao.findUsersByPartOfNickname(nickname)).thenReturn(expected);
        List<User> actual = userService.findUsersByPartOfNickname(nickname);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindUsersByPartOfNicknameWithEmptyResult() throws ServiceException, DaoException {
        String nickname = "minor";
        List<User> expected = new ArrayList<>();
        when(userDao.findUsersByPartOfNickname(nickname)).thenReturn(expected);
        List<User> actual = userService.findUsersByPartOfNickname(nickname);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindUserByNickname() throws ServiceException, DaoException {
        String nickname = user1.getNickname();
        Optional<User> expected = Optional.of(user1);
        when(userDao.findUserByNickname(nickname)).thenReturn(expected);
        Optional<User> actual = userService.findUserByNickname(nickname);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindUserByNicknameWithEmptyResult() throws ServiceException, DaoException {
        String nickname = "minor4ek";
        Optional<User> expected = Optional.empty();
        when(userDao.findUserByNickname(nickname)).thenReturn(expected);
        Optional<User> actual = userService.findUserByNickname(nickname);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindUserById() throws ServiceException, DaoException {
        int userId = user1.getUserId();
        Optional<User> expected = Optional.of(user1);
        when(userDao.findUserById(userId)).thenReturn(expected);
        Optional<User> actual = userService.findUserById(userId);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindUserByIdWithEmptyResult() throws ServiceException, DaoException {
        int userId = 3;
        Optional<User> expected = Optional.empty();
        when(userDao.findUserById(userId)).thenReturn(expected);
        Optional<User> actual = userService.findUserById(userId);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindUserRole() throws ServiceException, DaoException {
        String nickname = user1.getNickname();
        Optional<String> expected = Optional.of(user1.getRole());
        when(userDao.findUserRole(nickname)).thenReturn(expected);
        Optional<String> actual = userService.findUserRole(nickname);
        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void tearDown() {
        userDao = null;
        userService = null;
        user1 = null;
        user2 = null;
        users = null;
    }
}