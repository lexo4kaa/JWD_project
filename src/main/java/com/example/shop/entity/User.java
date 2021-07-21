package com.example.shop.entity;

import java.util.Date;
import java.util.Objects;

public class User {
    private int userId;
    private String name;
    private String surname;
    private String nickname;
    private String password;
    private Date dob;
    private String phone;
    private String email;
    private String role;
    private boolean isBanned;

    public User() {
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (isBanned != user.isBanned) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (dob != null ? !dob.equals(user.dob) : user.dob != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userId;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (surname == null ? 0 : surname.hashCode());
        result = prime * result + (nickname == null ? 0 : nickname.hashCode());
        result = prime * result + (password == null ? 0 : password.hashCode());
        result = prime * result + (dob == null ? 0 : dob.hashCode());
        result = prime * result + (phone == null ? 0 : phone.hashCode());
        result = prime * result + (email == null ? 0 : email.hashCode());
        result = prime * result + (role == null ? 0 : role.hashCode());
        result = prime * result + (isBanned ? 1 : 0) ;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", dob=").append(dob);
        sb.append(", phone=").append(phone).append('\'');
        sb.append(", email=").append(email).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", isBanned='").append(isBanned).append('\'');
        sb.append('}');
        return sb.toString();
    }
}