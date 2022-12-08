package UI;

import controllers.ChatManagerController;
import useCases.EventPackage.Event;
import PointSystem.PointSystemS;
import entities.User;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FriendsPage extends JFrame implements ActionListener {

    JButton[] buttons;
    User user;

    ChatManagerController chat_con;

    PointSystemS ps = new PointSystemS();

    JFrame frame = new JFrame();


    /**
     * @param user1
     */
    public FriendsPage(User user1) {

        user = user1;

        chat_con = new ChatManagerController(user);


        buttons = new JButton[user.getFriends().size()];


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(user1.getFriends().size(), 1));

        /**
         * Adding a button in the gridlayout for each of the friends
         */

        for (int i = 0; i < user1.getFriends().size(); i++){
            JButton button = new JButton(user.getFriends().get(i).getUsername());
            button.addActionListener(this);
            button.setFocusable(false);
            button.setText(user.getFriends().get(i).getUsername());
            String s1 = "src/" + user.getUsername() + user.getFriends().get(i).getUsername() + ".txt";
            String s2 = "src/" + user.getFriends().get(i).getUsername() + user.getUsername() + ".txt";

            /**
             * Adding the button only if there isn't an ongoing chat between the users
             * (file not existing between the two users means there isn't an ongoing chat)
             */

            if (!new File(s2).exists() && !new File(s1).exists()){
                frame.add(button);
                buttons[i] = button;
            }

        }

        frame.setVisible(true);

    }

    /**
     * @param e the event to be processed
     */

    /**
     *
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++){
            if (e.getSource() == buttons[i]){
                String friend_name = buttons[i].getText();
                for (User friend : user.getFriends()){
                    if (friend_name.equals(friend.getUsername()) && user.getPoints() >= 20){

                        /**
                         * If a friend is chosen point system is called to deduct points for choosing a match
                         */
                        ArrayList<User> users_involved = new ArrayList<>();
                        users_involved.add(user);
                        Event choose_friend = new Event("SpendChoose", users_involved);
                        try {
                            choose_friend.execute(ps);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        /**
                         * After a friend is chosen chat starts between the user and the chosen friend and chat screen
                         * is displayed
                         * the friends page is disposed
                         */
                        frame.dispose();
                        chat_con.makeMatch("Choose", friend);
                        try {
                            chat_con.createChat();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }

                }
            }
        }
    }

}






