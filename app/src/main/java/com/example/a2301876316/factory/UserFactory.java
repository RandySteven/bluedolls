package com.example.a2301876316.factory;

import com.example.a2301876316.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {
    User user;
    public User userCreate(List<User> users, String userName, String userEmail, String userPassword, String userGender, String userRole){
        String userId = "";
        if(users.size() < 10){
            userId = "US00" + users.size();
        }else if(users.size() >= 10 && users.size() < 100){
            userId = "US0" + users.size();
        }else{
            userId = "US" + users.size();
        }
        user = new User(userId, userName, userEmail, userPassword, userGender, userRole);
        return user;
    }
}
