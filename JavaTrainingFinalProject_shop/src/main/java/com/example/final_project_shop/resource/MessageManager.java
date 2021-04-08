package com.example.final_project_shop.resource;

import java.util.ResourceBundle;
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    private MessageManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}