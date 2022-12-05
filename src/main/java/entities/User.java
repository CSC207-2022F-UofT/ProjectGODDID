package entities;

import java.io.Serializable;
import java.util.ArrayList;

import static useCases.AccountManager.*;

/**
 * public class that represents the user entity and all the users attributes and methods
 */
public class User implements Serializable {
    public ArrayList<User> friends;
    private String username, password;
    public String acc_type;

    public int points;
    public ArrayList<User> blocked_friends;
    public int num_strikes;

    public User(String name, String acctype){
        this.username = name;
        this.acc_type = acctype;
        //points = 0;
        this.friends = new ArrayList<User>();
        this.blocked_friends = new ArrayList<User>();
    }

    /**
     * setter method that sets the username of the user
     * @param name username of the user
     */
    public void setUsername(String name){
        username = name;
    }


    /**
     * setter method that sets the password of the user
     * @param pWord user's password
     */
    public void setPassword(String pWord){
        password = pWord;
    }

    /**
     * setter method that sets the account type of the user
     * @param acc_type user's account type
     */
    public void setAccountType(String acc_type){
        this.acc_type = acc_type;
    }

    /**
     * setter method that sets the friends of the user
     * @param friends an arraylist of friends
     */
    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    /**
     * setter method that sets the points of the user
     * @param p the points to be assigned to the user, whos object calls this method.
     */
    public void setPoints(int p) {

        this.points = p;
    }

    /**
     * setter method that sets the blocked friends list of the user to an empty arraylist
     * initialize an empty arraylist of blocked friend belonging to the user
     */
    public void setBlocked_friends(){
        this.blocked_friends = new ArrayList<User>();
    }

    /**
     * setter method that sets the number of strikes of the user to 0
     * initialize the number of strikes of the user to 0
     */
    public void setNum_strikes(){
        this.num_strikes = 0;
    }

    /**
     * getter method to get the username of the user
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }


    /**
     * getter method to get the number of points of the user
     * @return the number of points of the user
     */
    public int getPoints() {
        return this.points;
    }


    /**
     * getter method to get the user's password
     * @return the password of the user
     */
    public String getPassword(){
        return password;
    }

    /**
     * getter method to get the list of friends of the user
     * @return an arraylist containing the user's friends
     */
    public ArrayList<User> getFriends() {
        return friends;
    }

    /**
     * getter method to get the list of blocked friends of the user
     * @return an arraylist containing the user's blocked friends
     */
    public ArrayList<User> getBlocked_friends() {
        return blocked_friends;
    }

    /**
     * getter method to get the number of strikes a user has
     * @return number of strikes the users had
     */
    public int getNum_strikes(){
        return num_strikes;
    }

    /**
     * add a strike to the user if hes blocked
     */
    public void addStrike(){
        this.num_strikes += 1;
    }

    /**
     * adds the user the set of blocked friends of the user that calls this method.
     * @param user the user that is to be added to the list of blocked friends of the current user
     */
    public void addblocked(User user){
        this.blocked_friends.add(user);
    }
}