package entities;

import java.io.Serializable;
import java.util.ArrayList;

import static useCases.AccountManager.*;

/**
 * User class which is the object (entity) where the users of the app is stored as
 */
public class User implements Serializable {
    public ArrayList<User> friends;
    private String username, password;
    public String acc_type;

    public int points;
    public ArrayList<User> blocked_friends;
    public int num_strikes;

    /**
     * @param name username
     */
    public User(String name){
        this.username = name;
        this.points = 0;
        this.friends = new ArrayList<User>();
    }

    /**
     * setter method to set the username of the user as the attribute
     * @param name username
     */
    public void setUsername(String name){
        username = name;
    }


    /**
     * setter method to set the password of the user as the attribute
     * @param pWord password of the user
     */
    public void setPassword(String pWord){
        password = pWord;
    }

    /**
     * setter method to set the account type of the user as the attribute
     * @param acc_type account type of the user
     */
    public void setAccountType(String acc_type){
        this.acc_type = acc_type;
    }

    /**
     * setter method to set the friends of the user as the attribute
     * @param friends list of friends of the user
     */
    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }


    /**
     * setter method to set the number of strikes of the user as the attribute
     * @param num_strike number of strikes associated with the user
     */
    public void setNum_strikes(int num_strike){
        this.num_strikes = num_strike;
    }

    /**
     * getter method to return the username of the user
     * @return username of the user
     */
    public String getUsername() {
        return username;
    }


    /**
     * getter method to return the points of the user
     * @return number of points the user has
     */
    public int getPoints() {
        return this.points;
    }


    /**
     * getter method to return the password of the user
     * @return password of the user
     */
    public String getPassword(  ){
        return password;
    }

    /**
     * getter method to return the friends of the user
     * @return list of friends of the user
     */
    public ArrayList<User> getFriends() {
        return friends;
    }

    /**
     * getter method to return the number of strikes of the user
     * @return number of strikes
     */
    public int getNum_strikes(){
        return num_strikes;
    }

    public void addStrike(){
        this.num_strikes += 1;
    }

}