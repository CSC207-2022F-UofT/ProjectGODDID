package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public ArrayList<User> friends;
    private String username, password;
    public String acc_type;

    public int points;
    private ArrayList<User> blocked_friends;
    private int num_strikes;

    public User(String name, String acctype){
        this.username = name;
        this.acc_type = acctype;
        //points = 0;
        friends = new ArrayList<User>();
    }

    public void setUsername(String name){
        username = name;
    }


    public void setPassword(String pWord){
        password = pWord;
    }

    public void setAccountType(String acc_type){
        this.acc_type = acc_type;
    }

    public String getAccountType(){
        return this.acc_type;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void setPoints(int p) {

        this.points = p;
    }

    public void setBlocked_friends(){
        this.blocked_friends = new ArrayList<User>();
    }

    public void setNum_strikes(){
        this.num_strikes = 0;
    }

    public String getUsername() {
        return username;
    }


    public int getPoints() {
        return this.points;
    }

    /*public Boolean getPremium() {
        return premium;
    }*/

    public String getPassword(  ){
        return password;
    }

    public ArrayList<User> getBlocked_friends(){
        return blocked_friends;
    }

    public ArrayList<User> getFriends() {
        return this.friends;
    }

    public int getNum_strikes(){
        return num_strikes;
    }

    public void addStrike(){
        this.num_strikes += 1;
    }

    public void addblocked(User user){
        blocked_friends.add(user);
    }
}