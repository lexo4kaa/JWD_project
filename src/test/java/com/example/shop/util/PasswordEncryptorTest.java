package com.example.shop.util;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordEncryptorTest {
    PasswordEncryptor encryptor;
    @BeforeMethod
    public void setUp() {
        encryptor = new PasswordEncryptor();
    }

    @DataProvider(name = "trueDataEncryptPassword")
    public Object[][] createTrueDataEncryptPassword(){
        return new Object[][]{
                {"belo4kAA", "77dd79d93b791c0f8e2a5c995b5038bd4464b329"},
                {"QWERTY", "65e21ea0de8852abc2b0d821c1f9ac6f2cd5bd98"},
                {"ya_umni4ka", "1ac4ca37c85f830ee90e786d9f061007ae25d2c4"},
                {"aleshakrupenko", "e5df1a383b8d611b8546819f63605085a3fbd62f"}
        };
    }
    @Test(dataProvider = "trueDataEncryptPassword")
    public void trueTestEncryptPassword(String password, String expected) {
        assertEquals(encryptor.encryptPassword(password), expected);
    }

    @DataProvider(name = "falseDataEncryptPassword")
    public Object[][] createFalseDataEncryptPassword(){
        return new Object[][]{
                {"belo4kAA", "790445061ea3f35190d3416912ef2cc8b6e60ae1"},
                {"QWERTY", "b1b3773a05c0ed0176787a4f1574ff0075f7521e"},
                {"ya_umni4ka", "4330ec750b282e0b8cb39fbb45df21a1d2be07ca"},
                {"aleshakrupenko", "b5797196b1661f404742cee566dfec8000771b93"}
        };
    }
    @Test(dataProvider = "falseDataEncryptPassword")
    public void falseTestIsLoginCorrect(String password, String expected) {
        assertNotEquals(encryptor.encryptPassword(password), expected);
    }

    @AfterMethod
    public void tearDown() {
        encryptor = null;
    }
}