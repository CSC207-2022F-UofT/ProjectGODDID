package UI;

import entities.User;
import useCases.ChatManager;

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

    ChatManager chat;


    public FriendsPage(User user1) {

        user = user1;

        chat = new ChatManager(user);

        buttons = new JButton[user.getFriends().size()];

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(user1.getFriends().size(), 1));


        for (int i = 0; i < user1.getFriends().size(); i++){
            JButton button = new JButton(user.getFriends().get(i).getUsername());
            button.addActionListener(this);
            button.setFocusable(false);
            button.setText(user.getFriends().get(i).getUsername());
            String s1 = "src/" + user.getUsername() + user.getFriends().get(i).getUsername() + ".txt";
            String s2 = "src/" + user.getFriends().get(i).getUsername() + user.getUsername() + ".txt";
            if (!new File(s2).exists() && !new File(s1).exists()){
                frame.add(button);
                buttons[i] = button;
            }

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
                        chat = new ChatManager(user);
                        chat.choseMatch(friend);
                        try {
                            chat.openChat();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                }
            }
        }
    }

}






