package com.example.final_project_shop.entity;

import java.util.Objects;

public class Product {
    private int productId;
    private String type;
    private String team;
    private int year;
    private String specification;
    private int quantity;
    private double price;
    private String path;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && quantity == product.quantity &&
                            Double.compare(product.price, price) == 0 && Objects.equals(type, product.type) &&
                            Objects.equals(team, product.team) && year == product.year &&
                            Objects.equals(specification, product.specification) && Objects.equals(path, product.path);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + productId;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        result = prime * result + year;
        result = prime * result + ((specification == null) ? 0 : specification.hashCode());
        result = prime * result + quantity;
        result = prime * result + (int)price;
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("productId=").append(productId);
        sb.append(", type='").append(type).append('\'');
        sb.append(", team='").append(team).append('\'');
        sb.append(", year='").append(year).append('\'');
        sb.append(", specification='").append(specification).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", path='").append(path).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
