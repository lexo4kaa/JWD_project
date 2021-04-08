package com.example.final_project_shop.pool;

import com.example.final_project_shop.db.ConnectionCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private final static int DEFAULT_POOL_SIZE = 8;
    private static final CustomConnectionPool instance = new CustomConnectionPool();

    private BlockingQueue<Connection> freeConnections;
    private Queue<Connection> givenAwayConnections;

    public static CustomConnectionPool getInstance() {
        return instance;
    }

    private CustomConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                Connection connection = ConnectionCreator.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            }
        } catch (SQLException e) {
            logger.error("creating connection is failed", e);
        }

        if(freeConnections.size() == 0) {
            throw new RuntimeException("database access error");
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            // todo watch video 02.04 (30.00)
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error("error getting connection", e);
            throw new ConnectionPoolException("error getting connection", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (givenAwayConnections.remove(connection)) {
            freeConnections.offer(connection);
        } else {
            logger.error(connection + " does not belong to the pool");
        }
    }

    public void destroyPool() throws ConnectionPoolException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                ((ProxyConnection)freeConnections.take()).reallyClose();
            } catch (InterruptedException e) {
                logger.error("error destroy pool", e);
                throw new ConnectionPoolException("destroyPool error", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() throws ConnectionPoolException {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("deregisterDrivers error", e);
                throw new ConnectionPoolException("deregisterDrivers error", e);
            }
        }
    }
}
