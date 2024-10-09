package com.example.apppizza;

public class User {
    private String userName;
    private int profileImage;
    private int likes;
    private boolean isLiked;  // Nueva variable para controlar el estado del like

    public User(String userName, int profileImage, int likes) {
        this.userName = userName;
        this.profileImage = profileImage;
        this.likes = likes;
        this.isLiked = false;  // Inicialmente, el usuario no ha dado like
    }

    // Getters y Setters

    public String getUserName() {
        return userName;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        this.isLiked = liked;
    }
}
