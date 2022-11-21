package UI;
// import statements

import controllers.*;
import entities.User;
import UI.*;
import useCases.FriendAdder;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HomeScreen extends JFrame implements ActionListener
{
/*
    CardLayout crd;

    // button variables to hold the references of buttons
    JButton btn1, btn2, btn3, btn4;
    Container cPane;

    // constructor of the class
    HomeScreen()
    {

        cPane = getContentPane();

//default constructor used
// therefore, components will
// cover the whole area
        crd = new CardLayout();

        cPane.setLayout(crd);

// creating the buttons
        btn1 = new JButton("Add Friend");
        btn2 = new JButton("Remove Friend");
        btn3 = new JButton("Create a new account");
        btn4 = new JButton("Delete an existing account");

// adding listeners to it
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);

        cPane.add("a", btn1); // first card is the button btn1
        cPane.add("b", btn2); // first card is the button btn2
        cPane.add("c", btn3);  // first card is the button btn3
        cPane.add("c", btn4);

        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome");

        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);*/


    //initialize button, panel, label, and text field
    JButton btn1, btn2, btn3, btn4, btn5;
    JPanel newPanel;
    //JLabel userLabel, emailLabel, passLabel, acc_typeLabel;
    //final JTextField  nameField, mailField, passField, acc_typeField;

    //calling constructor
    HomeScreen()
    {

        /*//create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        nameField = new JTextField();    //set length of the text

        emailLabel = new JLabel();
        emailLabel.setText("Email");

        mailField = new JTextField();

        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2

        //create text field to get password from the user
        passField = new JPasswordField();    //set length for the password

        acc_typeLabel = new JLabel();
        acc_typeLabel.setText("Type Of Account");      //set label value for textField2

        //create text field to get password from the user
        acc_typeField = new JTextField();*/

        //create submit button
        btn1 = new JButton("Add a friend"); //set label to button
        btn2 = new JButton("Remove a friend");
        btn3 = new JButton("Create a new account");
        btn4 = new JButton("Delete an existing account");
        btn5 = new JButton("Start chat");

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(5, 1));
        /*newPanel.add(userLabel);    //set username label to panel
        newPanel.add(nameField);   //set text field to panel
        newPanel.add(emailLabel);    //set password label to panel
        newPanel.add(mailField);
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(passField);   //set text field to panel
        newPanel.add(acc_typeLabel);    //set password label to panel
        newPanel.add(acc_typeField);*/
        newPanel.add(btn1);           //set button to panel
        newPanel.add(btn2);
        newPanel.add(btn3);
        newPanel.add(btn4);
        newPanel.add(btn5);

        //set border to panel
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        btn1.addActionListener(this);     //add action listener to button
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        setTitle("Welcome");         //set title to the login form
    }



    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == btn1) //add friend
        {
            System.out.println("Add friend");
        }
        else if(event.getSource() == btn2)//remove friend
        {
            System.out.println("remove friend");
        }
        else if(event.getSource() == btn3)//create new account
        {
            RegisterScreen reg = new RegisterScreen();
            reg.setVisible(true);
        }
        else if(event.getSource() == btn4)//delete existing account
        {
            System.out.println("delete account");
        }
        else // start chat
        {

        }




    }


    // main method
    public static void main(String argvs[])
    {
// creating an object of the class CardLayoutExample1
        //HomeScreen crdl = new HomeScreen();
        User bob = new User("Bob","");
        ArrayList friends = new ArrayList();
        User user1 = new User("Alpha", "");
        User user2 = new User("Beta", "");
        friends.add(user2);
        friends.add(user1);
        bob.setFriends(friends);

        WelcomePage crdl = new WelcomePage(bob);

// size is 300 * 300
        crdl.setSize(300, 300);
        crdl.setVisible(true);
        crdl.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


}