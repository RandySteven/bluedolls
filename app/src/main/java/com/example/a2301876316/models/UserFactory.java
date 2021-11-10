package com.example.a2301876316.models;

import java.util.ArrayList;

public class UserFactory {
    User user;
    static ArrayList<User> users = new ArrayList<>();
    public User userCreate(String userId, String userName, String userEmail, String userPassword, String userGender, String userRole){
        user = new User(userId, userName, userEmail, userPassword, userGender, userRole);
        return user;
    }

    public static void insertUser(User user){
        users.add(user);
    }

    public static ArrayList<User> getUsers(){
        return users;
    }

    public static User getUserByIndex(int index){
        return users.get(index);
    }

}
