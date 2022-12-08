package UI;
import Databases.ReadGraph;
import useCases.AccountManager;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;


public class LoginPage implements ActionListener{

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton signUp = new JButton("Sign Up");
    JTextField usernameField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel usernameLabel = new JLabel("username:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();


    ReadGraph rg = new ReadGraph();

    AccountManager adder = new AccountManager();


    /**
     * initializes the display of the login page
     */
    public LoginPage(){


        usernameLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);

        messageLabel.setBounds(125,250,250,35);
        messageLabel.setFont(new Font(null,Font.PLAIN,25));

        usernameField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);

        loginButton.setBounds(125,200,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        signUp.setBounds(225,200,100,25);
        signUp.setFocusable(false);
        signUp.addActionListener(this);

        frame.add(usernameLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(usernameField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(signUp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    /**
     * responsible for the various actions in the login page, responds appropriate messages according to
     * the situation, displays a 'sign up successful' message if so or a 'username exists' message if the
     * username already exists. Displays 'login successful' if the user exists and the correct username
     * and password is entered. 'wrong password' is displayed if user tries to login with an incorrect
     * password. Displays if the account does not exist.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == signUp) {

            try {
                System.out.println(rg.readobject().accounts.keySet());

                if (!rg.readobject().accounts.containsKey(usernameField.getText())) {
                    adder.addUser(usernameField.getText(), String.valueOf(userPasswordField.getPassword()));
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Sign Up Successful");
                    System.out.println(rg.readobject().accounts.keySet());
                    System.out.println(" ");
                } else{
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Username exists");
                }

            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        }


        if(e.getSource()==loginButton) {
            String username = usernameField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            try {
                if (rg.readobject().accounts.get(username).getUsername().equals(username)) {
                    if (rg.readobject().accounts.get(username).getPassword().equals(password)) {
                        messageLabel.setForeground(Color.green);
                        messageLabel.setText("Login successful");
                        frame.dispose();
                        try {
                            WelcomePage welcomePage = new WelcomePage(rg.readobject().accounts.get(username));
                        } catch (IOException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }

                    } else {
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("Wrong password");
                    }
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText(username + " does not exist");
                }
            } catch (NullPointerException exception) {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Account does not exist");
            }

        }

    }


}