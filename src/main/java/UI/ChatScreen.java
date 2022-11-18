package UI;

import EventRepository.Event;
import entities.User;
import useCases.Report;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ChatScreen extends JFrame implements ActionListener{
    /* TODO:
    - fix colours
    - make chat panel scrollable
    - code for actions after clicking each button
    - implement interactor
    - update event log after each event
     */
    boolean reported = false;
    String message = "";

    public User mainUser;
    public User matchedUser;
    ArrayList<String> messages;
    String fileName = "";


    // initializing all buttons and text fields to be accessed both by actionPerformed and constructor
    JButton newGame; JButton send; JButton report; JButton back;
    JTextField sendMessage;

    // constructor for when a chat screen is opened
    public ChatScreen(String user1, String user2){
        // Commonly used variables
        Color cream = new Color(247, 239, 215);
        Color yellowish = new Color(232, 220, 184);
        Font buttonFont = new Font("Helvetica Neue",Font.BOLD,15);

        // Label to identify who you are currently chatting with
        JLabel label = new JLabel();
        label.setText("Speaking with " + matchedUser); //testing, change to variable of matched user real name after
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        // TODO: Display Messages
        JTextArea displayed = new JTextArea();

        // Text field where the used inputs their message
        sendMessage = new JTextField(); //increase max?
        sendMessage.setPreferredSize(new Dimension(320, 30));

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
            GameUI game = new GameUI(mainUser, matchedUser);

        } else if (e.getSource()==send) {
            // will send the typed message in the text field
            // add to text file and retrieve from text file
            message = sendMessage.getText();
            new AddToTextFile(message, mainUser.getUsername(), fileName);

        } else if (e.getSource()==report) {
            // will use the report feature to send the chat data to be reported at the end of the day when chat ends
            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to report?",
                    "Report", JOptionPane.YES_NO_OPTION);

            if (answer == 0){
                reported = true;
            }

        } else if (e.getSource()==back) {
            //leaves the chat screen
            HomeScreen home = new HomeScreen();
            //code to exit chat screen (goes back to the home screen)
        }
    }

    public boolean getReported(){
        return reported;
    }

    public void setReported(boolean update){
        this.reported = update;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void retrieveMessages(){
        messages = new ReadFile(fileName).ReadFiles();
    }
}

class runScreen{
    ChatScreen chat;
    User mainUser;
    User matchedUser;
    String fileName;

    public static void main(String[] args){
        try
        {
            ChatScreen testChat = new ChatScreen("User1", "User2");
            testChat.setVisible(true);
        }
        catch(Exception e)
        {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void startChat(boolean usedPoints, User mainUser){
        this.mainUser = mainUser;

        // if-else to execute the correct code depending on if the main user used their points
        if (usedPoints){
            //matchedUser = ; // TODO: get selected user from points manager class
        } else {
            int index = (int)(Math.random() * mainUser.getFriends().size());
            this.matchedUser = mainUser.getFriends().get(index);
        }

        // creates and names a new file storing the messages in the chat
        String textFileName = mainUser.getUsername() + " chat with " + matchedUser.getUsername() + ".txt";
        this.fileName = textFileName;
        File file = new File(textFileName);

        //opens a new chatscreen with the selected user
        chat = new ChatScreen(mainUser.getUsername(), matchedUser.getUsername());
        chat.setFileName(textFileName);
    }

    public void endChat(){
        ArrayList <User> users = new ArrayList<User>();
        users.add(mainUser); users.add(matchedUser);
        Report report = new Report();

        if (chat.getReported()){
            report.checkReport(fileName, mainUser, matchedUser);
        }

        EventRepository.Event e = new Event("ChatEnd", users, false);
        e.execute();

        // delete text file and reset instance attributes after (speak w/ mert)
        matchedUser = null;
        fileName = "";
        chat.setReported(false);
        chat = null;
    }
}