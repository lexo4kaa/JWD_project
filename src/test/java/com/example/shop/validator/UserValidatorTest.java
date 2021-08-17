package com.example.shop.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidatorTest {
    @DataProvider(name = "trueDataIsLoginCorrect")
    public Object[][] createTrueDataIsLoginCorrect(){
        return new Object[][]{
                {"belo4kAA"},
                {"QWERTY"},
                {"ya_umni4ka"},
                {"alex58"}
        };
    }
    @Test(dataProvider = "trueDataIsLoginCorrect")
    public void trueTestIsLoginCorrect(String line) {
        assertTrue(UserValidator.isLoginCorrect(line));
    }

    @DataProvider(name = "falseDataIsLoginCorrect")
    public Object[][] createFalseDataIsLoginCorrect(){
        return new Object[][]{
                {"IvanovIvanIvanovich"}, // length is 19 (>18)
                {"QWE RTY"}, // 2 words
                {"_abcdef"}, // first symbol is a underscore
                {"abcdef_"}, // last symbol is a underscore
                {"ya.umni4ka"}, // contains '.'
                {"a"} // length is 1 (<3)
        };
    }
    @Test(dataProvider = "falseDataIsLoginCorrect")
    public void falseTestIsLoginCorrect(String line) {
        assertFalse(UserValidator.isLoginCorrect(line));
    }

    @DataProvider(name = "trueDataIsEmailCorrect")
    public Object[][] createTrueDataIsEmailCorrect(){
        return new Object[][]{
                {"belo4kAA@gmail.com"},
                {"QWERTY@gmail.com"},
                {"ya_umni4ka@gmail.com"},
                {"alesha.krupenko@gmail.com"}
        };
    }
    @Test(dataProvider = "trueDataIsEmailCorrect")
    public void trueTestIsEmailCorrect(String line) {
        assertTrue(UserValidator.isEmailCorrect(line));
    }

    @DataProvider(name = "falseDataIsEmailCorrect")
    public Object[][] createFalseDataIsEmailCorrect(){
        return new Object[][]{
                {"IvanovIvanIvanovichIvanovIvanIvanovich@gmail.com"}, // length is 38 (>30)
                {"QWE RTY@gmail.com"}, // 2 words
                {".abcdef@gmail.com"}, // first symbol  is a dot
                {"abcdef.@gmail.com"}, // last symbol of name is a dot
                {"umni4ka@yandex.ru"}, // not contains '@gmail.com'
                {"abc@gmail.com"} // length is 3 (<6)
        };
    }
    @Test(dataProvider = "falseDataIsEmailCorrect")
    public void falseTestIsEmailCorrect(String line) {
        assertFalse(UserValidator.isEmailCorrect(line));
    }

    @DataProvider(name = "trueDataIsPasswordCorrect")
    public Object[][] createTrueDataIsPasswordCorrect(){
        return new Object[][]{
                {"belo4kAA"},
                {"QWERTY"},
                {"ya_umni4ka"},
                {"aleshakrupenko"}
        };
    }
    @Test(dataProvider = "trueDataIsPasswordCorrect")
    public void trueTestIsPasswordCorrect(String line) {
        assertTrue(UserValidator.isPasswordCorrect(line));
    }

    @DataProvider(name = "falseDataIsPasswordCorrect")
    public Object[][] createFalseDataIsPasswordCorrect(){
        return new Object[][]{
                {"IvanovIvanIvanovich"}, // length is 19 (>18)
                {"QWE RTY"}, // 2 words
                {"ya.umni4ka"}, // contains '.'
                {"alex"} // length is 4 (<6)
        };
    }
    @Test(dataProvider = "falseDataIsPasswordCorrect")
    public void falseTestIsPasswordCorrect(String line) {
        assertFalse(UserValidator.isPasswordCorrect(line));
    }

    @DataProvider(name = "trueDataIsPhoneCorrect")
    public Object[][] createTrueDataIsPhoneCorrect(){
        return new Object[][]{
                {"375292129055"},
                {"375178080123"},
                {"375441357900"},
                {"375339777555"}
        };
    }
    @Test(dataProvider = "trueDataIsPhoneCorrect")
    public void trueTestIsPhoneCorrect(String line) {
        assertTrue(UserValidator.isPhoneCorrect(line));
    }

    @DataProvider(name = "falseDataIsPhoneCorrect")
    public Object[][] createFalseDataIsPhoneCorrect(){
        return new Object[][]{
                {"3752921290555"}, // length is 13 (!=12)
                {"375558080123"}, // code 55 is not supported
                {"375440357900"}, // after code '0'
                {"+375339777555"} // starts with '+'
        };
    }
    @Test(dataProvider = "falseDataIsPhoneCorrect")
    public void falseTestIsPhoneCorrect(String line) {
        assertFalse(UserValidator.isPhoneCorrect(line));
    }
}
