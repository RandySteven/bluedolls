package com.example.a2301876316.models;

public class Doll {
    private String dollId;
    private String dollName;
    private String dollDescription;
    private byte []dollImage;
    private User user;

    public String getDollId() {
        return dollId;
    }

    public void setDollId(String dollId) {
        this.dollId = dollId;
    }

    public void setDollImage(byte []dollImage){
        this.dollImage = dollImage;
    }

    public byte[] getDollImage(){
        return dollImage;
    }

    public String getDollName() {
        return dollName;
    }

    public void setDollName(String dollName) {
        this.dollName = dollName;
    }

    public String getDollDescription() {
        return dollDescription;
    }

    public void setDollDescription(String dollDescription) {
        this.dollDescription = dollDescription;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public Doll(String dollId, String dollName, String dollDescription, byte[] dollImage, User user){
        this.dollId = dollId;
        this.dollName = dollName;
        this.dollDescription = dollDescription;
        this.dollImage = dollImage;
        this.user = user;
    }
}
