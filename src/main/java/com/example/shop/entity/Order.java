package com.example.shop.entity;

import java.util.Objects;

public class Order {
    private int orderId;
    private int clientId;
    private double cost;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && clientId == order.clientId && cost == order.cost;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + clientId;
        result = prime * result + orderId;
        result = prime * result + (int)cost;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", clientId=").append(clientId);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
