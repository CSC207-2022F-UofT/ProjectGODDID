package UI;

import EventPackage.Event;
import PointSystem.PointSystemS;
import controllers.FriendRecommenderController;
import entities.User;
import useCases.ChatManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class RandomRecommendUI extends JFrame implements ActionListener {

    JButton[] buttons;

    JPanel friend_panel = new JPanel();

    JPanel button_panel = new JPanel();

    JLabel textfield = new JLabel();
    User user;

    JFrame frame = new JFrame();


    public RandomRecommendUI(User user1) {

        user = user1;

        buttons = new JButton[user.getFriends().size()];


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(user1.getFriends().size(), 1));

        textfield.setBackground(Color.black);
        textfield.setForeground(Color.white);
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Choose a friend");
        textfield.setOpaque(true);

        friend_panel.setLayout(new BorderLayout());
        friend_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(user1.getFriends().size(), 1));
        button_panel.setBackground(Color.GRAY);

        friend_panel.add(textfield);


        for (int i = 0; i < user1.getFriends().size(); i++){
            JButton button = new JButton(user.getFriends().get(i).getUsername());
            button.addActionListener(this);
            button.setFocusable(false);
            button.setText(user.getFriends().get(i).getUsername());

            button_panel.add(button);
            buttons[i] = button;

        }

        frame.add(friend_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++){
            if (e.getSource() == buttons[i]){
                String friend_name = buttons[i].getText();
                for (User friend : user.getFriends()){
                    if (friend_name.equals(friend.getUsername())){
                        FriendRecommenderController rec_con = new FriendRecommenderController();
                        try {
                            String rand_friend = rec_con.getRandomRec(friend, user);
                            textfield.setText("Recommended friend: " + rand_friend);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                }
            }
        }
    }

}
