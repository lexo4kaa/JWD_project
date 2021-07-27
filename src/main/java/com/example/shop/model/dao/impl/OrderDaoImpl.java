package com.example.shop.model.dao.impl;

import com.example.shop.entity.Order;
import com.example.shop.model.dao.DaoException;
import com.example.shop.model.dao.OrderDao;
import com.example.shop.model.dao.ProductDao;
import com.example.shop.model.dao.UserDao;
import com.example.shop.model.pool.ConnectionPoolException;
import com.example.shop.model.pool.CustomConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.shop.model.dao.OrderColumn.*;

public class OrderDaoImpl implements OrderDao {
    private static final OrderDao instance = new OrderDaoImpl();
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final ProductDao productDao = ProductDaoImpl.getInstance();

    private static final String SQL_FIND_ALL_ORDERS = "SELECT order_id,ref_user_id,order_cost,order_date," +
                                                      "method_of_receiving,method_of_payment FROM orders";
    private static final String SQL_ADD_ORDER = "INSERT INTO orders (ref_user_id,order_cost," +
                                                "method_of_receiving,method_of_payment) VALUES (?,?,?,?)";
    private static final String SQL_ADD_ORDER_HAS_PRODUCT = "INSERT INTO order_has_product (ref_order_id," +
                                                            "ref_product_id,quantity) VALUES (?,?,?)";
    private static final String SQL_FIND_ORDERS_BY_NICKNAME = "SELECT order_id,ref_user_id,order_cost,order_date," +
                                                              "method_of_receiving,method_of_payment FROM orders AS o" +
                                                              " JOIN users AS u ON u.user_id = o.ref_user_id" +
                                                              " WHERE user_nickname = ?";


    public static OrderDao getInstance(){
        return instance;
    }

    @Override
    public List<Order> findAllOrders() throws DaoException {
        List<Order> orders = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDERS);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()){
                orders.add(createOrdersFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding orders", e);
        }
        return orders;
    }

    @Override
    public void addOrder(Map<Integer, Integer> cart, String nickname,
                         String methodOfReceiving, String methodOfPayment) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_ORDER)) {
            int userId = userDao.findUserByNickname(nickname).getUserId();
            statement.setInt(1, userId);
            statement.setDouble(2, totalCost(cart));
            statement.setString(3, methodOfReceiving);
            statement.setString(4, methodOfPayment);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while adding order", e);
        }
    }

    @Override
    public void addOrderHasProduct(Map<Integer, Integer> cart, int productId) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_ORDER_HAS_PRODUCT)) {
            int orderId = findAllOrders().size();
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            statement.setInt(3, cart.get(productId));
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while adding in order_has_product", e);
        }
    }

    @Override
    public List<Order> findOrdersByNickname(String nickname) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_NICKNAME)) {
            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                orders.add(createOrdersFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding orders", e);
        }
        return orders;
    }

    private double totalCost(Map<Integer, Integer> cart) throws DaoException {
        int cost = 0;
        int[] keys = cart.keySet().stream()
                .mapToInt(Integer::intValue)
                .toArray();
        for(int i = 0; i < cart.size(); i++) {
            int productId = keys[i];
            cost += productDao.findPriceById(productId) * cart.get(productId);
        }
        return cost;
    }

    private Order createOrdersFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        int orderId = resultSet.getInt(ORDER_ID);
        int user_id = resultSet.getInt(USER_ID);
        double cost = resultSet.getDouble(COST);
        LocalDateTime date = resultSet.getObject(ORDER_DATE, LocalDateTime.class);
        String methodOfReceiving = resultSet.getString(METHOD_OF_RECEIVING);
        String methodOfPayment = resultSet.getString(METHOD_OF_PAYMENT);
        order.setOrderId(orderId);
        order.setUserId(user_id);
        order.setOrderCost(cost);
        order.setOrderDate(date);
        order.setMethodOfReceiving(methodOfReceiving);
        order.setMethodOfPayment(methodOfPayment);
        return order;
    }
}
