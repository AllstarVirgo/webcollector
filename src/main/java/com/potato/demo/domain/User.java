package com.potato.demo.domain;

public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private String password;

    private String token;


    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
