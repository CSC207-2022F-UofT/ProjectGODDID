package UI;


import controllers.AddFriendController;
import entities.User;
import useCases.AccountManager;
import useCases.ChatManager;
import useCases.FriendAdder;
import useCases.FriendRemover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WelcomePage extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel("Hello!");
    JButton startchat = new JButton();
    JButton friends = new JButton();
    JButton addfriend = new JButton();
    JButton removefriend = new JButton();
    JButton skipchat = new JButton();
    JButton match = new JButton();
    TextField text = new TextField();

    AddFriendController adder = new AddFriendController();

    ChatManager chat;

    JButton recommendButton, activeButton;
    User user1, friend;

    AccountManager accountManager;


    public WelcomePage(User user)  {
        user1 = user;

        chat = new ChatManager(user1);

        recommendButton = new JButton();
        recommendButton.setBounds(200, 35, 200, 50);
        recommendButton.addActionListener(this);
        recommendButton.setText("Recommend Friends");
        recommendButton.setFocusable(false);

        activeButton = new JButton();
        activeButton.setBounds(250, 175, 100, 50);
        activeButton.addActionListener(this);
        activeButton.setText("Active Chat");
        activeButton.setFocusable(false);

        welcomeLabel.setBounds(0,0,400,35);
        welcomeLabel.setFont(new Font(null,Font.PLAIN,20));
        Integer points = (Integer) user.getPoints();
        welcomeLabel.setText("Hello "+ user.getUsername() + ", you have " + points.toString() + " points");

        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setVisible(true);

        match.setBounds(150, 100, 100, 50);
        match.addActionListener(this);
        match.setText("Match");
        match.setFocusable(false);

        startchat.setBounds(250, 100, 100, 50);
        startchat.addActionListener(this);
        startchat.setText("Start Chat");
        startchat.setFocusable(false);

        skipchat.setBounds(350, 100, 100, 50);
        skipchat.addActionListener(this);
        skipchat.setText("Skip");
        skipchat.setFocusable(false);

        friends.setBounds(250, 250, 100, 50);
        friends.addActionListener(this);
        friends.setText("Friends");
        friends.setFocusable(false);

        addfriend.setBounds(210, 400,100,25);
        addfriend.setFocusable(false);
        addfriend.addActionListener(this);
        addfriend.setText("Add");

        removefriend.setBounds(310,400,100,25);
        removefriend.setFocusable(false);
        removefriend.addActionListener(this);
        removefriend.setText("Remove");

        text.setBounds(200,360,225,25);

        frame.add(startchat);
        frame.add(recommendButton);
        frame.add(activeButton);
        frame.add(friends);
        frame.add(text);
        frame.add(addfriend);
        frame.add(removefriend);
        frame.add(skipchat);
        frame.add(match);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == friends){
            FriendsPage friends = new FriendsPage(user1);
        }

        if (e.getSource() == addfriend){
            String friend_to_add = text.getText();

            try {
                adder.AddFriendCon(user1, friend_to_add);
        if(e.getSource() == friends) //add friend
        {
            FriendsPage friends = new FriendsPage(user1);
        }
        if(e.getSource() == addfriend) //add friend
        {
            friend = new User(text.getText(), "");
            FriendAdder friendAdder = new FriendAdder();

            //User rand = friendRecommender.getRecommendRandom(user1);
            try {
                friendAdder.addFriend(user1,friend);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == removefriend) //add friend
        {
            for (User i:user1.getFriends()){
                if (i.getUsername().equals(text.getText())){
                    friend = i;
                }
            }
            FriendRemover friendRemover = new FriendRemover();
            try {
                friendRemover.remove(user1,friend,accountManager);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        //if (e.getSource() == friends){
        //try {

        //}catch (IOException ex) {
        //throw new RuntimeException(ex);
        // } catch (ClassNotFoundException ex) {
        // throw new RuntimeException(ex);
        // }
        //}

        User matched_user = null;
        if (e.getSource() == match){
            matched_user = chat.randomMatch();
            System.out.println(matched_user.getUsername());
        }

        if (e.getSource() == skipchat){
            matched_user = chat.skipMatch(matched_user);
            System.out.println(matched_user.getUsername());
        }

        if (e.getSource() == startchat){
            try {
                chat.openChat(user1, matched_user);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }


    }


}