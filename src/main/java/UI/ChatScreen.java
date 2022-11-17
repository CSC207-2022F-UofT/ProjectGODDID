package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatScreen extends JFrame implements ActionListener{
    /* TODO:
    - fix colours
    - make chat panel scrollable
    - code for actions after clicking each button
    - implement interactor
    - update event log after each event
    - add comments
     */
    boolean reported = false;

    // initializing all buttons to be accessed both by actionPerformed and constructor
    JButton newGame; JButton send; JButton report; JButton back;

    // constructor for when a chat screen is opened
    public ChatScreen(String mainUser, String matchedUser){
        // Commonly used variables
        Color cream = new Color(247, 239, 215);
        Color yellowish = new Color(232, 220, 184);
        Font buttonFont = new Font("Helvetica Neue",Font.BOLD,15);

        // Label to identify who you are currently chatting with
        JLabel label = new JLabel();
        label.setText("Speaking with " + matchedUser); //testing, change to variable of matched user real name after
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        // Text field where the used inputs their message
        JTextField sendMessage = new JTextField(26); //increase max?

        // Button to send the message typed in the sendMessage text field
        send = new JButton();
        send.addActionListener(this);
        send.setText("Send");
        send.setFont(buttonFont);
        send.setBounds(340, 390, 150, 40);

        // Button to start a new game with the matched used
        newGame = new JButton();
        newGame.addActionListener(this);
        newGame.setText("Games");
        newGame.setFocusable(false);
        newGame.setFont(buttonFont);

        // Button to activate the report system after the chat has ended
        report = new JButton();
        report.addActionListener(this);
        report.setText("Report");
        report.setFont(buttonFont);

        // Button to return to HomeScreen window
        back = new JButton();
        back.addActionListener(this);
        back.setText("Exit"); // maybe add return icon instead
        back.setFont(buttonFont);
        back.setBounds(0, 0, 20, 20);

        // Panels to separate the code into 3 sections:
        // Panel to contain the buttons and the label in the menu bar
        JPanel topPanel = new JPanel();
        topPanel.setBackground(cream);
        topPanel.setBounds(0,0,500,25);

        // Panel to contain all the text of the actual chat between the two users
        JPanel textPanel = new JPanel();
        textPanel.setBackground(cream);
        textPanel.setBounds(12, 30, 476, 350);

        // Panel to contain the typed message the user wants to send
        JPanel sendPanel = new JPanel();
        sendPanel.setBackground(cream);
        sendPanel.setBounds(12, 390, 325, 40);

        // Frame containing all the created objects
        JFrame frame = new JFrame();
        frame.setTitle(mainUser + "'s chat with " + matchedUser);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500,500);
        ImageIcon image = new ImageIcon("Messaging logo.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(yellowish);
        topPanel.add(label);
        topPanel.add(newGame);
        topPanel.add(report);
        topPanel.add(back);
        frame.add(topPanel);
        frame.add(textPanel);
        sendPanel.add(sendMessage);
        frame.add(sendPanel);
        frame.add(send);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // TODO: Code for actions of each button
        if (e.getSource()==newGame){
            // will send a new ticktacktoe game to start with the matched user

        } else if (e.getSource()==send) {
            // will send the typed message in the text field
            // add to text file and retrieve from text file
        } else if (e.getSource()==report) {
            // will use the report feature to send the chat data to be reported at the end of the day when chat ends
            reported = true; // TODO: output to user that report went through?

        } else if (e.getSource()==back) {
            //leaves the chat screen

        }
    }

    public boolean getReported(){
        return reported;
    }

    public void setReported(boolean update){
        this.reported = update;
    }
}

class runScreen{
    public static void main(String[] args){
        try
        {
            ChatScreen chat = new ChatScreen("User1", "User2");
            chat.setVisible(true);
        }
        catch(Exception e)
        {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}