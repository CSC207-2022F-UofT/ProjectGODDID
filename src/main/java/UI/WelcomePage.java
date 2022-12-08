package UI;



import useCases.EventPackage.Event;
import PointSystem.PointSystemS;
import controllers.ChatManagerController;
import controllers.FriendRecommenderController;
import entities.User;
import useCases.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import Databases.*;

public class WelcomePage extends JFrame implements ActionListener {
    ChatManagerController chat_con;
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

    JLabel friendsLabel = new JLabel();

    JLabel matchLabel = new JLabel();

    PointSystemS ps = new PointSystemS();

    FriendFacade friend_facade = new FriendFacade();

    JButton recommendButton, activeButton, recommendRandButton;
    User user1;

    ReadGraph rg = new ReadGraph();


    /**
     * @param user
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public WelcomePage(User user) throws IOException, ClassNotFoundException {
        user1 = user;
        chat_con = new ChatManagerController(user1);

        /**
         * Adding a button for friend recommendation based on highest mutual friends
         */

        recommendButton = new JButton();
        recommendButton.setBounds(150, 35, 200, 50);
        recommendButton.addActionListener(this);
        recommendButton.setText("Recommend Friend");
        recommendButton.setFocusable(false);

        /**
         * Adding a button for random friend recommendation
         */

        recommendRandButton = new JButton();
        recommendRandButton.setBounds(350, 35, 200, 50);
        recommendRandButton.addActionListener(this);
        recommendRandButton.setText("Recommend Random");
        recommendRandButton.setFocusable(false);

        /**
         * Adding a button to see active chats between the user and his/ her friends
         */
        activeButton = new JButton();
        activeButton.setBounds(250, 175, 100, 50);
        activeButton.addActionListener(this);
        activeButton.setText("Active Chat");
        activeButton.setFocusable(false);

        /**
         * Welcome label for greeting the user and displaying the user's points
         */
        welcomeLabel.setBounds(0,0,400,35);
        welcomeLabel.setFont(new Font(null,Font.PLAIN,20));
        Integer points = (Integer) user1.getPoints();
        welcomeLabel.setText("Hello "+ user.getUsername() + ", you have " + points.toString() + " points");

        /**
         * Match label for displaying who the user is matched with
         */
        matchLabel.setBounds(200,450,400,35);
        matchLabel.setFont(new Font(null,Font.PLAIN,20));

        /**
         * Friends label for displaying the recommended friend
         */
        friendsLabel.setBounds(100,500,400,35);
        friendsLabel.setFont(new Font(null,Font.PLAIN,20));


        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setVisible(true);

        /**
         * Match button to get matched with a friend for chat
         */
        match.setBounds(150, 100, 100, 50);
        match.addActionListener(this);
        match.setText("Match");
        match.setFocusable(false);

        /**
         * Start chat button for starting the chat between the user and matched friend
         */
        startchat.setBounds(250, 100, 100, 50);
        startchat.addActionListener(this);
        startchat.setText("Start Chat");
        startchat.setFocusable(false);

        /**
         * Skip chat button to get matched with an another friend
         */
        skipchat.setBounds(350, 100, 100, 50);
        skipchat.addActionListener(this);
        skipchat.setText("Skip");
        skipchat.setFocusable(false);

        /**
         * Friends button to see the list of friends (if there isn't ongoing chat between them)
         */
        friends.setBounds(250, 250, 100, 50);
        friends.addActionListener(this);
        friends.setText("Friends");
        friends.setFocusable(false);

        /**
         * Add friend button to add a friend based on username
         */
        addfriend.setBounds(210, 400,100,25);
        addfriend.setFocusable(false);
        addfriend.addActionListener(this);
        addfriend.setText("Add");

        /**
         * Remove friend button to remove a friend based on username
         */
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
        frame.add(matchLabel);
        frame.add(friendsLabel);
        frame.add(recommendRandButton);
        frame.add(messageLabel);

        messageLabel.setBounds(250,360,250,45);
        messageLabel.setFont(new Font(null,Font.PLAIN,25));

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == friends){
            FriendsPage friends = new FriendsPage(user1);
        }

        if(e.getSource() == addfriend){
            String friendToAdd = text.getText();
            System.out.println(friendToAdd);
            //FriendAdder fd = new FriendAdder();
            System.out.println(rg.readobject().getUsers());
            try {
                //System.out.println("hi");
                System.out.println("before:"+user1.getFriends());
                friend_facade.addFriend(user1, rg.readobject().getUser(friendToAdd));
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
            try {
                friend_facade.removeFriend(user1, rg.readobject().getUser(friendToAdd));
                messageLabel.setForeground(Color.green);
                messageLabel.setText(friendToAdd+" is successfuly removed from your friend's list");
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (e.getSource() == match){
            chat_con.randomize();
            matchLabel.setText("Matched with: " + chat_con.getMatch().getUsername());
            match.setEnabled(false);
        }

        /**
         * Skip chat button calls the points system for deducting points
         */
        if (e.getSource() == skipchat && user1.getPoints() >= 10){
            ArrayList<User> users_involved = new ArrayList<>();
            users_involved.add(user1);
            Event skip_friend = new Event("SpendSkip", users_involved);
            try {
                skip_friend.execute(ps);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Integer points = (Integer) user1.getPoints();
            welcomeLabel.setText("Hello "+ user1.getUsername() + ", you have " + points.toString() + " points");
            chat_con.makeMatch("Skip", chat_con.getMatch());
            matchLabel.setText("Matched with: " + chat_con.getMatch().getUsername());
        }

        if (e.getSource() == startchat){
            try {
                chat_con.createChat();
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (e.getSource() == activeButton) {
            new ActiveChatsPage(user1);
        }

        /**
         * Calls the friend recommender controller for friend recommendation
         */
        if (e.getSource() == recommendButton) {
            FriendRecommenderController rec_friend = new FriendRecommenderController();
            String recommended_friend;
            try {
                recommended_friend = rec_friend.getRec(user1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            friendsLabel.setText("Recommended Friend: " + recommended_friend);

        }

        /**
         * Calls the Random recommend Uı and Displays a new page
         */
        if (e.getSource() == recommendRandButton) {
            new RandomRecommendUI(user1);
        }



    }


}