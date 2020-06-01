package com.sc.homework;


public class User { //用户对象
    private String username;
    private String password;
    private String card;

    public User() {
    }

    public User(String username, String password, String card) {
        this.username = username;
        this.password = password;
        this.card = card;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
