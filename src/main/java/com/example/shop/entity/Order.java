package com.example.shop.entity;

import java.util.Date;

public class Order {
    private int orderId;
    private int userId;
    private double orderCost;
    private Date orderDate;
    private String methodOfReceiving;
    private String methodOfPayment;

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

    public String getMethodOfReceiving() {
        return methodOfReceiving;
    }

    public void setMethodOfReceiving(String methodOfReceiving) {
        this.methodOfReceiving = methodOfReceiving;
    }

    public String getMethodOfPayment() {
        return methodOfPayment;
    }

    public void setMethodOfPayment(String methodOfPayment) {
        this.methodOfPayment = methodOfPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (orderId != order.orderId) return false;
        if (userId != order.userId) return false;
        if (Double.compare(order.orderCost, orderCost) != 0) return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) return false;
        if (methodOfReceiving != null ? !methodOfReceiving.equals(order.methodOfReceiving) : order.methodOfReceiving != null)
            return false;
        return methodOfPayment != null ? methodOfPayment.equals(order.methodOfPayment) : order.methodOfPayment == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userId;
        result = prime * result + orderId;
        result = prime * result + (int)orderCost;
        result = prime * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = prime * result + (methodOfReceiving != null ? methodOfReceiving.hashCode() : 0);
        result = prime * result + (methodOfPayment != null ? methodOfPayment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", orderCost=").append(orderCost);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", methodOfReceiving=").append(methodOfReceiving);
        sb.append(", methodOfPayment=").append(methodOfPayment);
        sb.append('}');
        return sb.toString();
    }
}
