package com.example.a2301876316.factory;

import com.example.a2301876316.DataHelper;
import com.example.a2301876316.models.Doll;
import com.example.a2301876316.models.User;

import java.util.ArrayList;

public class DollFactory {

    public Doll createDoll(ArrayList<Doll> dolls, String dollName, String dollDescription, byte[] dollImage, User user){
        String dollId = "";
        if(dolls.size() < 10){
            dollId = "DD00" + dolls.size();
        }else if(dolls.size() >= 10 && dolls.size() < 100){
            dollId = "DD0" + dolls.size();
        }else{
            dollId = "DD" + dolls.size();
        }
        return new Doll(dollId, dollName, dollDescription, dollImage, user);
    }
}
