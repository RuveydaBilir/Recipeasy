package com.example.recipeasy.BackEnd;

import com.example.recipeasy.CoverPageActivity;

public class User {
    private String email;
    private String password;

    private String userID;

    public User() {
    }

    public User(String email, String password, String userID) {
        this.email = email;
        this.password = password;
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
