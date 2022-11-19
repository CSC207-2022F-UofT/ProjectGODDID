package UI;

import Databases.ReadFile;

import Databases.WriteFile;
import entities.Graph;
import entities.User;
import useCases.AccountManager;
import useCases.LoginMatcher;
import useCases.UserCreator;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;


public class LoginPage implements ActionListener{

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton signUp = new JButton("Sign Up");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("username:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();

    LoginMatcher chatOpen = new LoginMatcher();

    Graph logininfos;
    AccountManager adder = new AccountManager();


    public LoginPage(){

//        logininfo = loginInfoOriginal;

        userIDLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);

        messageLabel.setBounds(125,250,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,25));

        userIDField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);

        loginButton.setBounds(125,200,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        signUp.setBounds(225,200,100,25);
        signUp.setFocusable(false);
        signUp.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(signUp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== signUp) {

            try {
                adder.addUser(userIDField.getText(),String.valueOf(userPasswordField.getPassword()), "casual");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

//            UserCreator use = new UserCreator();
//            User user = use.CreateUser(userIDField.getText(), String.valueOf(userPasswordField.getPassword()));
//            try {
//                userwr.writefile(user);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            } catch (ClassNotFoundException ex) {
//                throw new RuntimeException(ex);
//            }

        }


        if(e.getSource()==loginButton) {
            try {
                logininfos = adder.getGraph();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            for (User i : logininfos.getUsers()) {
                String userID = userIDField.getText();
                String password = String.valueOf(userPasswordField.getPassword());

                if (i.getUsername().equals(userID)) {
                    if (i.getPassword().equals(password)) {
                        messageLabel.setForeground(Color.green);
                        messageLabel.setText("Login successful");
                        frame.dispose();
                        try {
                            WelcomePage welcomePage = new WelcomePage(i);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                    } else {
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("Wrong password");
                    }
                }
            }


    }
}