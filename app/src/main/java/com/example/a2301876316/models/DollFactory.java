package com.example.a2301876316.models;

import java.util.ArrayList;

public class DollFactory {

    static ArrayList<Doll> dolls = new ArrayList<>();

    public Doll createDoll(String dollName, String dollDescription, int dollImage, User user){
        String dollId = "";
        for(int i = 0 ; i < dolls.size() ; i++){
            if(dolls.isEmpty() && dolls.size() < 10){
                dollId = "DD00" + (i + 1);
            }else if(dolls.size() < 100){
                dollId = "DD0" + (i + 1);
            }else{
                dollId = "DD" + i+1;
            }
        }
        return new Doll(dollId, dollName, dollDescription, dollImage, user);
    }

    public static void insertDolls(Doll doll){
        dolls.add(doll);
    }

    public static ArrayList<Doll> getDolls(){
        return dolls;
    }
}
