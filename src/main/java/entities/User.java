package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private ArrayList<User> friends;
    private String username, password;
    private Boolean premium;
    private int points;

    public User(String username, String password, ArrayList<User> friends){
        this.username = username;
        this.password = password;
        points = 0;
        this.friends = friends;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        points = 0;
        friends = new ArrayList<User>();
    }

    public void setUsername(String name){
        username = name;
    }


    public void setPassword(String pWord){
        password = pWord;
    }

    public String getPassword() {
        return password;
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