package UI;

import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class FriendsPage extends JFrame implements ActionListener {

    JButton[] buttons;
    User user;


    public FriendsPage(User user1) {

        user = user1;


        JButton[] buttons = new JButton[user.getFriends().size()];

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(user1.getFriends().size(), 1));


        for (int i = 0; i < user1.getFriends().size(); i++){
            JButton button = new JButton(user.getFriends().get(i).getUsername());
            button.addActionListener(this);
            button.setFocusable(false);
            button.setText(user.getFriends().get(i).getUsername());
            frame.add(button);

            buttons[i] = button;
        }

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++){
            if (e.getSource() == buttons[i]){
                String friend_name = buttons[i].getText();
                for (User friend : user.getFriends()){
                    if (friend_name.equals(friend.getUsername())){
                        break; // the method for starting a chat will come here
                    }

                }
            }
        }
    }

}






