package entities;

import java.util.ArrayList;

public class User {
    private ArrayList<User> friends;
    private String username, email, password;
    private Boolean premium;
    private int points;

    public User(){
        points = 0;
        friends = new ArrayList<User>();
    }

    public void setUsername(String name){
        username = name;
    }

    public void setEmail(String mail){
        email = mail;
    }

    public void setPassword(String pWord){
        password = pWord;
    }

    public void setPremium(Boolean prem){
        premium = prem;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getPoints() {
        return points;
    }

    public Boolean getPremium() {
        return premium;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }
}
