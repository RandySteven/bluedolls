package com.example.testmad.models;

import java.io.Serializable;

public class User implements Serializable {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userGender;
    private String userRole;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    public String getUserPassword(){
        return userPassword;
    }

    public void setUserGender(String userGender){
        this.userGender = userGender;
    }

    public String getUserGender(){
        return userGender;
    }

    public void setUserRole(String userRole){
        this.userRole = userRole;
    }

    public String getUserRole(){
        return userRole;
    }

    public User(String userId, String userName, String userEmail, String userPassword, String userGender, String userRole){
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userRole = userRole;
    }

}
