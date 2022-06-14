package com.example.pre_phase.Model;

public class Users {
    private String id;
    private String username;
    private  String imageUrl;
    public Users(){

    }
    public Users(String id, String username, String imageUrl){
        this.id = id;
        this.username = username;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
