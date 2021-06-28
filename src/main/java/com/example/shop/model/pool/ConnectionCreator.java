package com.example.shop.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionCreator {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String FILE_NAME = "properties/database.properties";
    private static final String PROPERTY_URL = "db.url";
    private static final String PROPERTY_PASSWORD = "db.password";
    private static final String PROPERTY_USER = "db.user";
    private static final String PROPERTY_DRIVER = "db.driver";
    private static final String DATABASE_URL;
    private static final String DATABASE_PASSWORD;
    private static final String DATABASE_USER;
    private static final String DATABASE_DRIVER;

    private ConnectionCreator(){}

    static {
        try {
            ClassLoader classLoader = ConnectionCreator.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
            properties.load(inputStream);
            DATABASE_URL = properties.getProperty(PROPERTY_URL);
            DATABASE_PASSWORD = properties.getProperty(PROPERTY_PASSWORD);
            DATABASE_USER = properties.getProperty(PROPERTY_USER);
            DATABASE_DRIVER = properties.getProperty(PROPERTY_DRIVER);
            Class.forName(DATABASE_DRIVER);
        } catch (FileNotFoundException e) {
            logger.fatal("FileNotFoundException in ConnectionCreator", e);
            throw new RuntimeException();
        } catch (IOException e) {
            logger.fatal("IOException in ConnectionCreator", e);
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            logger.fatal("ClassNotFoundException in ConnectionCreator", e);
            throw new RuntimeException();
        }
    }

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}