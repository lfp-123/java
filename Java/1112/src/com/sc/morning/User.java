package com.sc.morning;

public class User {
    private String userName;
    private String passWord;
    private String card;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public User(String userName, String passWord, String card) {
        this.userName = userName;
        this.passWord = passWord;

        this.card = card;
    }
}
