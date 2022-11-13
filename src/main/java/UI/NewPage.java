package UI;

//import required classes and packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//create NewPage class to create a new page on which user will navigate
class NewPage extends JFrame implements ActionListener
{
    JPanel newPanel, cPane;

    CardLayout crd;

    // button variables to hold the references of buttons
    JButton btn1, btn2, btn3;

    Container mainPane;

    JLabel userLabel;

    //constructor
    NewPage()
    {
        userLabel = new JLabel();
        userLabel.setText("Welcome");
        //cPane = getContentPane();

//default constructor used
// therefore, components will
// cover the whole area
        crd = new CardLayout();
        cPane = new JPanel(crd);

        //cPane.setLayout(crd);

// creating the buttons
        btn1 = new JButton("Apple");
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
        setSize(500, 500);

        mainPane = getContentPane();
        mainPane.setLayout(new BorderLayout());
        mainPane.add(userLabel, BorderLayout.PAGE_START);
        //mainPane.add(cPane, BorderLayout.CENTER);
        //newPanel = new JPanel(new GridLayout(2, 1));
        //newPanel.add(cPane);    //set username label to panel
        //newPanel.add(userLabel);   //set text field to panel
    }

    public void actionPerformed(ActionEvent e)
    {
// Upon clicking the button, the next card of the container is shown
// after the last card, again, the first card of the container is shown upon clicking
        crd.next(cPane);
    }
}