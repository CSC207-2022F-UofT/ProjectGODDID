package UI;

import controllers.ChatManagerController;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ActiveChatsPage extends JFrame implements ActionListener {
    ArrayList<JButton> buttons;
    User user;

    JFrame frame = new JFrame();

    ChatManagerController chat;


    /**
     * @param user1
     */
    public ActiveChatsPage(User user1) {

        user = user1;

        buttons = new ArrayList<>();

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
             * Adding the button only if there is an ongoing chat (existing file between the two users means the chat is
             * ongoing
             */

            if (new File(s2).exists() || new File(s1).exists()){
                frame.add(button);
                buttons.add(button);
            }

        }

        frame.setVisible(true);

    }

    /**
     * @param e the event to be processed
     */

    /**
     * If one of the buttons are pressed the chat screen between the user and the chosen friends
     * is displayed and the active chats page
     * is disposed
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.size(); i++){
            if (e.getSource() == buttons.get(i)){
                String friend_name = buttons.get(i).getText();
                for (User friend : user.getFriends()){
                    if (friend_name.equals(friend.getUsername())){
                        frame.dispose();
                        chat = new ChatManagerController(user);
                        chat.makeMatch("Choose", friend);
                        try {
                            chat.createChat();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                }
            }
        }
    }

}
