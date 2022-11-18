package UI;

import EventPackage.Event;
import entities.User;
import useCases.Report;
import PointsSystem.PointsSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatScreen extends JFrame implements ActionListener, WindowListener{
    boolean reported = false;
    public User mainUser;
    public User matchedUser;

    // initializing all buttons and text fields to be accessed both by actionPerformed and constructor
    JButton newGame; JButton send; JButton report; JButton back;
    JTextField sendMessage; JTextArea displayed;

    // constructor for when a chat screen is opened
    public ChatScreen(User user1, User user2){
        this.mainUser = user1;
        this.matchedUser = user2;

        // Commonly used variables
        Color cream = new Color(247, 239, 215);
        Color yellowish = new Color(232, 220, 184);
        Font buttonFont = new Font("Helvetica Neue",Font.BOLD,15);

        // Label to identify who you are currently chatting with
        JLabel label = new JLabel();
        label.setText("Speaking with " + matchedUser.getUsername());
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        displayed = new JTextArea();
        displayed.setEditable(false);
        displayed.setBackground(cream);
        displayed.setFont(buttonFont);
        displayed.setForeground(Color.BLACK);
        displayed.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(displayed, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Text field where the used inputs their message
        sendMessage = new JTextField(20); //increase max?
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
//        back = new JButton();
//        back.addActionListener(this);
//        back.setText("Exit"); // maybe add return icon instead
//        back.setFont(buttonFont);
//        back.setBounds(0, 0, 20, 20);

        // Panels to separate the code into 3 sections:
        // Panel to contain the buttons and the label in the menu bar
        JPanel topPanel = new JPanel();
        topPanel.setBackground(cream);
        topPanel.setBounds(0,0,500,25);

        // Panel to contain all the text of the actual chat between the two users
        JPanel textPanel = new JPanel();
        textPanel.setBackground(cream);
        textPanel.setBounds(12, 30, 476, 350);
        textPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        textPanel.setLayout(new BorderLayout(0, 0));

        // Panel to contain the typed message the user wants to send
        JPanel sendPanel = new JPanel();
        sendPanel.setBackground(cream);
        sendPanel.setBounds(12, 390, 325, 40);

        // Frame containing all the created objects
        JFrame frame = new JFrame();
        frame.setTitle(mainUser.getUsername() + "'s chat with " + matchedUser.getUsername());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500,500);
        ImageIcon image = new ImageIcon("Messaging logo.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(yellowish);
        frame.addWindowListener(this);
        topPanel.add(label);
        topPanel.add(newGame);
        topPanel.add(report);
//        topPanel.add(back);
        frame.add(topPanel);
        textPanel.add(scrollPane, BorderLayout.CENTER);
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
            addToTextFile(sendMessage.getText(), mainUser.getUsername(), matchedUser.getUsername());
            readFromTextFile(mainUser.getUsername(), matchedUser.getUsername());
            sendMessage.setText("");
        }

        else if (e.getSource()==report) {
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
            // MIGHT delete this part, comment out for now
        }
    }

    public boolean getReported(){
        return reported;
    }

    public void setReported(boolean update){
        this.reported = update;
    }

    public void addToTextFile(String messages, String username1, String username2){
        String s = "src/" + username1 + username2 + ".txt";
        String s1 = "src/" + username2 + username1 + ".txt";
        File f = new File(s);
        if (f.exists()) {
            try {
                FileWriter writer = new FileWriter(s, true);
                writer.write(username1 + ": " + messages + "\n");
                writer.close();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
        else{
            try {
                FileWriter writer = new FileWriter(s1);
                writer.write(username1 + ": " + messages + "\n");
                writer.close();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    public ArrayList<String> readFromTextFile(String username1, String username2) {
        displayed.setText("");
        ArrayList<String> list_of_messages = new ArrayList<String>();
        String file1 = "src/" + username1 + username2 + ".txt";
        String file2 = "src/" + username2 + username1 + ".txt";
        File f = new File(file1);
        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    list_of_messages.add(data);
                    displayed.append(data + "\n");
                }
                sc.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                File file = new File(file2);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    list_of_messages.add(data);
                    displayed.append(data + "\n");
                }
                sc.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return list_of_messages;
    }

    public void endChat(){
        ArrayList <User> users = new ArrayList<>();
        users.add(mainUser); users.add(matchedUser);
        String s1 = "src/" + mainUser.getUsername() + matchedUser.getUsername() + ".txt";
        String s2 = "src/" + matchedUser.getUsername() + mainUser.getUsername() + ".txt";
        Report report = new Report(mainUser, matchedUser);

        if (reported){
            // TODO: Manit check this
            report.checkReport();
        }

        try {
            Files.deleteIfExists(Paths.get(s1));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            Files.deleteIfExists(Paths.get(s2));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Event e = new Event("ChatEnd", users, false);
        PointsSystem ps = new PointsSystem();
        e.execute(ps);

        matchedUser = null;
        reported = false;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        endChat();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        endChat();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

class runScreen{
    ChatScreen chat;
    User mainUser;
    User matchedUser;

    public static void main(String[] args) throws IOException {
        ChatScreen testChat;
        User user1 = new User("Manit", "Casual");
        User user2 = new User("Arian", "Casual");

        try
        {
            String s = "src/" + user1.getUsername() + user2.getUsername() + ".txt";
            File file = new File(s);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            else {
                System.out.println("File already exists.");
            }

            testChat = new ChatScreen(user1, user2);
            testChat.setVisible(true);
        }
        catch(Exception e)
        {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void startChat(boolean usedPoints, User mainUser) throws IOException {
        this.mainUser = mainUser;

        // if-else to execute the correct code depending on if the main user used their points
        if (usedPoints){
            //matchedUser = ; // TODO: get selected user from points manager class
        } else {
            int index = (int)(Math.random() * mainUser.getFriends().size());
            this.matchedUser = mainUser.getFriends().get(index);
        }

        createTextFile(mainUser.getUsername(), matchedUser.getUsername());

        //opens a new chatscreen with the selected user
        chat = new ChatScreen(mainUser, matchedUser);
    }

    public void createTextFile(String username1, String username2) throws IOException {
        String s = "src/" + username1 + username2 + ".txt";
        File file = new File(s);
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        }
        else {
            System.out.println("File already exists.");
        }
    }
}