package entities;

import java.util.ArrayList;

import static useCases.AccountManager.*;

public class User {
    private ArrayList<Vertex> friends;
    private String username, email, password;
    public String acc_type;
    public int points;

    public User(){
        points = 0;
        friends = new ArrayList<Vertex>();
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

    public void setAccountType(String acc_type){
        this.acc_type = acc_type;
    }

    public String getAccountType(){
        return this.acc_type;
    }

    public Vertex getEqVertex(User user)
    {
        ArrayList<Vertex> users = user_graph.getVertices();
        for(Vertex i: users)
        {
            if(i.curr_user.equals(user))
                return i;
        }
        return null;
    }

    public void setFriends(ArrayList<Vertex> friends) {
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

    /*public Boolean getPremium() {
        return premium;
    }*/

    public String getPassword(  ){
        return password;
    }

    public ArrayList<Vertex> getFriends() {
        return friends;
    }
}