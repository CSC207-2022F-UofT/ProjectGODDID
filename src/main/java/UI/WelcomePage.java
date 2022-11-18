package UI;

import controllers.AddFriendController;
import entities.User;
import useCases.AccountManager;

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
    TextField text = new TextField();

    JButton recommendButton, activeButton;
    User user1;


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
        Integer points = (Integer) user.getPoints();
        welcomeLabel.setText("Hello "+ user.getUsername() + ", you have " + points.toString() + " points");

        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setVisible(true);

        startchat.setBounds(250, 100, 100, 50);
        startchat.addActionListener(this);
        startchat.setText("Start Chat");
        startchat.setFocusable(false);

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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == friends){
            FriendsPage friends = new FriendsPage(user1);
        }

        if (e.getSource() == addfriend){
            String friend_to_add = text.getText();
            AddFriendController friend_con = new AddFriendController();
            try {
                friend_con.AddFriendCon(user1, friend_to_add);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        }

    }
}