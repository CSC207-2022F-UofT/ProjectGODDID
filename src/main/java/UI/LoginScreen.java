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
class LoginScreen extends JFrame implements ActionListener
{
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;

    JTextField  textField1, textField2;

    //calling constructor
    LoginScreen()
    {
        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2

        //create text field to get password from the user
        textField2 = new JPasswordField(15);    //set length for the password

        //create submit button
        b1 = new JButton("SUBMIT"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel

        //set border to panel
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        b1.addActionListener(this);     //add action listener to button
        setTitle("LOGIN FORM");         //set title to the login form
    }

    public JPanel getPanel(){
        return newPanel;
    }

    //define abstract method actionPerformed() which will be called on button click
    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter
    {
        String userValue = textField1.getText();        //get user entered username from the textField1
        String passValue = textField2.getText();        //get user entered pasword from the textField2

        //check whether the credentials are authentic or not
        User bob = new User("bob", "Casual");
        bob.setUsername("bob");
        bob.setPassword("123");
        User joe = new User("joe", "Casual");
        joe.setUsername("joe");
        User frank = new User("frank", "Casual");
        frank.setUsername("frank");
        ArrayList<User> users = new ArrayList<>();
        users.add(bob);
        users.add(joe);
        users.add(frank);
        ArrayList<User> bobFriends = new ArrayList<>();
        bobFriends.add(frank);
        bob.setFriends(bobFriends);
        for (User i : users) {
            if (i.getUsername().equals(userValue) && i.getPassword().equals(passValue)) {
                newPanel.setVisible(false);
                ChatUI page = new ChatUI();
                //NewPage page = new NewPage();
                //HomeScreen home = new NewPage();

                //make page visible to the user
                page.setVisible(true);
                //home.setVisible(true);

                //create a welcome label and set it to the new page
                JLabel wel_label = new JLabel("Welcome: "+userValue);
                page.getContentPane().add(wel_label);
                //page.userLabel = new JLabel("Welcome: "+userValue);
            } else {
                //show error message
                System.out.println("Please enter valid username and password");
            }
        }
    }
}

//create the main class
class LoginFormDemo
{
    //main() method start
    public static void main(String arg[])
    {
        try
        {
            //create instance of the CreateLoginForm
            //LoginScreen form = new LoginScreen();
            StartScreen form = new StartScreen();
            form.setSize(500,500);  //set size of the frame
            form.setVisible(true);  //make form visible to the user
        }
        catch(Exception e)
        {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}