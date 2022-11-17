package useCases;

import EventRepository.Event;
import entities.*;
import java.io.File;
import java.util.ArrayList;

import UI.*;

public class ChatManager{
    public User mainUser;
    public User matchedUser;
    String fileName = "";
    ChatScreen chat;

    // TODO: add reference on previous page (of UI) so that it can access this one
    public ChatManager(User mainUser){
        this.mainUser = mainUser;
    }

    public void startChat(boolean usedPoints){
        // if-else to execute the correct code depending on if the main user used their points
        if (usedPoints){
            //matchedUser = ; // TODO: get selected user from points manager class
        } else {
            matchedUser = this.selectUser();
        }

        // creates and names a new file storing the messages in the chat
        String textFileName = mainUser.getUsername() + " chat with " + matchedUser.getUsername() + ".txt";
        this.fileName = textFileName;
        File file = new File(textFileName);

        //opens a new chatscreen with the selected user
        chat = new ChatScreen(mainUser.getUsername(), matchedUser.getUsername());
    }

    // method to randomly select the main user's matched user if they did not use their points to select a user
    public User selectUser(){
        // TODO: consider different account types?
        int index = (int)(Math.random() * mainUser.getFriends().size());
        return mainUser.getFriends().get(index);
    }

    public void sendMessage(String message){

    }

    public void retrieveMessages(){

    }

    public void endChat(){
        ArrayList <User> users = new ArrayList<User>();
        users.add(mainUser); users.add(matchedUser);
        Report report = new Report();

        if (chat.getReported()){
            report.checkReport(fileName, mainUser, matchedUser);
        }

        Event e = new Event("ChatEnd", users, false);
        e.execute();

        // delete text file and reset instance attributes after (speak w/ mert)
        matchedUser = null;
        fileName = "";
        chat.setReported(false);
        chat = null;
    }
}
