package UI;
// import statements

import controllers.*;
import entities.User;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HomeScreen extends JFrame implements ActionListener
{

    CardLayout crd;

    // button variables to hold the references of buttons
    JButton btn1, btn2, btn3;
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
        btn2 = new JButton("Boy");
        btn3 = new JButton("Cat");

// adding listeners to it
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);

        cPane.add("a", btn1); // first card is the button btn1
        cPane.add("b", btn2); // first card is the button btn2
        cPane.add("c", btn3);  // first card is the button btn3

        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome");

        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public void actionPerformed(ActionEvent e)
    {
// Upon clicking the button, the next card of the container is shown
// after the last card, again, the first card of the container is shown upon clicking
        User bob = new User();
        bob.setUsername("bob");
        User joe = new User();
        joe.setUsername("joe");
        User frank = new User();
        frank.setUsername("frank");
        ArrayList<User> users = new ArrayList<>();
        users.add(bob);
        users.add(joe);
        users.add(frank);
        ArrayList<User> bobFriends = new ArrayList<>();
        bobFriends.add(frank);
        bob.setFriends(bobFriends);

        crd.next(cPane);
        AddUIController addUIController = new AddUIController();
        addUIController.addUser(users, bob, joe);
        for (User i: bob.getFriends()){
            System.out.println(i.getUsername());
        }

    }

    /*
    // main method
    public static void main(String argvs[])
    {
// creating an object of the class CardLayoutExample1
        HomeScreen crdl = new HomeScreen();

// size is 300 * 300
        crdl.setSize(300, 300);
        crdl.setVisible(true);
        crdl.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

     */
}