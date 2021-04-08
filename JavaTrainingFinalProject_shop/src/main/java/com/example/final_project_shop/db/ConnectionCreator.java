package com.example.final_project_shop.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    private static final DatabaseConfig databaseConfig = new DatabaseConfig();
    private static Logger logger = LogManager.getLogger();

    private ConnectionCreator(){}
    //fixme Class.forName in constructor or static-block
    public static Connection createConnection() throws SQLException {
        Connection connection;
        try {
            String driverName = databaseConfig.getDriverName();
            String url = databaseConfig.getUrl();
            String username = databaseConfig.getUsername();
            String password = databaseConfig.getPassword();
            Class.forName(driverName);
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            logger.fatal("Error with database: " + databaseConfig, e);
            throw new RuntimeException("Error with database: " + databaseConfig, e);
        }
        return connection;
    }
}