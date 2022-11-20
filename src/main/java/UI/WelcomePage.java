package UI;


import entities.User;
import useCases.AccountManager;
import useCases.FriendAdder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import Databases.*;
import useCases.FriendRemover;

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

    JLabel messageLabel = new JLabel();

    JButton recommendButton, activeButton;
    User user1;

    ReadGraph rg = new ReadGraph();


    public WelcomePage(User user) throws IOException, ClassNotFoundException {
        user1 = user;

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
        Integer points = (Integer) user1.getPoints();
        welcomeLabel.setText("Hello "+ user.getUsername() + ", you have " + points.toString() + " points");

        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setVisible(true);

        match.setBounds(150, 100, 100, 50);
        match.addActionListener(this);
        match.setText("Start Chat");
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

        messageLabel.setBounds(250,360,250,45);
        messageLabel.setFont(new Font(null,Font.ITALIC,25));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == friends){
            FriendsPage friends = new FriendsPage(user1);
        }

        if(e.getSource() == addfriend){
            String friendToAdd = text.getText();
            System.out.println(friendToAdd);
            //FriendAdder fd = new FriendAdder();
            AccountManager am= new AccountManager();
            try {
                System.out.println(rg.readobject().getUsers());
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try {
                //System.out.println("hi");
                System.out.println("before:"+user1.getFriends());
                am.addFriend(user1, rg.readobject().getUser(friendToAdd));
                System.out.println("after:"+user1.getFriends());
                messageLabel.setForeground(Color.green);
                System.out.println("hi");
                messageLabel.setText(friendToAdd+" is successfuly added to your friend's list");
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == removefriend){
            String friendToAdd = text.getText();
            //FriendRemover fd = new FriendRemover();
            AccountManager am = new AccountManager();
            try {
                am.removeFriend(user1, rg.readobject().getUser(friendToAdd));
                messageLabel.setForeground(Color.green);
                messageLabel.setText(friendToAdd+" is successfuly removed from your friend's list");
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }




    }


}