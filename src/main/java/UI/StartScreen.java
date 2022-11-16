package UI;
//import required classes and packages

import entities.*;
import UI.RegisterScreen;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import java.util.ArrayList;

//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
class StartScreen extends JFrame implements ActionListener
{
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    JTextField  textField1, textField2;

    private final CardLayout cl = new CardLayout();
    private final JPanel cards = new JPanel(cl);
    private final Border border = BorderFactory.createEmptyBorder(60, 60, 60, 60);

    //calling constructor
    StartScreen()
    {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JPanel panel1 = new JPanel(new GridBagLayout());
        panel1.setBorder(border);
        panel1.setBackground(Color.RED);
        panel1.add(new JLabel("Card 1"));
        LoginScreen loginScreen = new LoginScreen();
        cards.add(loginScreen.getPanel(), "First Panel");

        JPanel panel2 = new JPanel(new GridBagLayout());
        panel2.setBorder(border);
        panel2.setBackground(Color.GREEN);
        panel2.add(new JLabel("Card 2"));
        RegisterScreen registerScreen = new RegisterScreen();
        cards.add(registerScreen.getPanel(), "Second Panel");

        JButton controlButton = new JButton("Register");
        controlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.next(cards);
            }
        });
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.LIGHT_GRAY);
        controlPanel.add(controlButton);

        contentPane.add(cards);
        contentPane.add(controlPanel);

        cl.show(cards, "First Panel");

        setTitle("LOGIN FORM");         //set title to the login form
    }
    //define abstract method actionPerformed() which will be called on button click
    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter
    {
        String userValue = textField1.getText();        //get user entered username from the textField1
        String passValue = textField2.getText();        //get user entered pasword from the textField2


    }
}
