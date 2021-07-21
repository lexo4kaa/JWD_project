package com.example.shop.entity;

import java.util.Date;

public class Order {
    private int orderId;
    private int userId;
    private double orderCost;
    private Date orderDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(double orderCost) {
        this.orderCost = orderCost;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (orderId != order.orderId) return false;
        if (userId != order.userId) return false;
        if (Double.compare(order.orderCost, orderCost) != 0) return false;
        return orderDate != null ? orderDate.equals(order.orderDate) : order.orderDate == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userId;
        result = prime * result + orderId;
        result = prime * result + (int)orderCost;
        result = prime * result + (orderDate != null ? orderDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", orderCost=").append(orderCost);
        sb.append(", orderDate=").append(orderDate);
        sb.append('}');
        return sb.toString();
    }
}
