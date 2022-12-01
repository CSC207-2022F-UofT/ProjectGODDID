package UI;

import EventPackage.Event;
import entities.User;
import useCases.Report;
import PointSystem.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatScreen extends JFrame implements ActionListener, KeyListener {
    // Declaring instance attributes
    public User mainUser;
    public User matchedUser;
    public int numExtended = 0;

    // Declaring all buttons and text fields and the timer to be accessed both by actionPerformed and constructor
    JFrame frame;
    JButton newGame; JButton send; JButton report; JButton back;
    JTextField sendMessage; JTextArea displayed;
    Timer timer = new Timer(500, this);

    // Constructor for when a chat screen is opened
    public ChatScreen(User user1, User user2) throws InterruptedException, IOException {
        // Setting instance attributes to users passed into the ChatScreen constructor
        this.mainUser = user1;
        this.matchedUser = user2;

        // Start timer to constantly keep refreshing chat to check for new messages
        timer.start();

        // Creating variables for commonly used colours/fonts
        Color cream = new Color(247, 239, 215);
        Color yellowish = new Color(232, 220, 184);
        Font buttonFont = new Font("Helvetica Neue",Font.BOLD,15);

        // Label to identify who you are currently chatting with
        JLabel label = new JLabel();
        label.setText("Speaking with " + matchedUser.getUsername());
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFocusable(true);
        label.addKeyListener(this);

        // TextArea to contain and display all of the text within the chat
        displayed = new JTextArea();
        displayed.setEditable(false);
        displayed.setBackground(cream);
        displayed.setFont(buttonFont);
        displayed.setForeground(Color.BLACK);
        displayed.setLineWrap(true);
        displayed.setFocusable(true);
        displayed.addKeyListener(this);

        // Scroll pane to make chat displayed vertically scrollable if there are too many messages to show at once
        JScrollPane scrollPane = new JScrollPane(displayed, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setFocusable(true);
        scrollPane.addKeyListener(this);

        // Text field where the used inputs their message
        sendMessage = new JTextField();
        sendMessage.setPreferredSize(new Dimension(320, 30));
        sendMessage.setFocusable(true);
        sendMessage.addKeyListener(this);

        // Button to send the message typed in the sendMessage text field
        send = new JButton();
        send.addActionListener(this);
        send.setText("Send");
        send.setFont(buttonFont);
        send.setBounds(340, 390, 150, 40);
        send.addKeyListener(this);

        // Button to start a new game with the matched user
        newGame = new JButton();
        newGame.addActionListener(this);
        newGame.setText("Games");
        newGame.setFocusable(false);
        newGame.setFont(buttonFont);

        // Button to activate the report system after the chat has ended
        report = new JButton();
        report.addActionListener(this);
        report.setText("Report");
        report.setFocusable(false);
        report.setFont(buttonFont);

        // TODO: Maybe add back button later? (Check back w/ mert when he finishes home screen)
        // Button to return to HomeScreen window
//        back = new JButton();
//        back.addActionListener(this);
//        back.setText("Exit"); // maybe add return icon instead
//        back.setFont(buttonFont);
//        back.setBounds(0, 0, 20, 20);

        // Panels below to separate the code into 3 sections:
        // 1: Panel to contain the buttons and the label in the menu bar
        JPanel topPanel = new JPanel();
        topPanel.setBackground(cream);
        topPanel.setBounds(0,0,500,25);
        topPanel.setFocusable(true);
        topPanel.addKeyListener(this);

        // 2: Panel to contain all the text of the actual chat between the two users
        JPanel textPanel = new JPanel();
        textPanel.setBackground(cream);
        textPanel.setBounds(12, 30, 476, 350);
        textPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        textPanel.setLayout(new BorderLayout(0, 0));
        textPanel.setFocusable(true);
        textPanel.addKeyListener(this);

        // 3: Panel to contain the typed message the user wants to send
        JPanel sendPanel = new JPanel();
        sendPanel.setBackground(cream);
        sendPanel.setBounds(12, 390, 325, 40);
        sendPanel.setFocusable(true);
        sendPanel.addKeyListener(this);

        // Frame containing all the added components and added panels which have their own components
        frame = new JFrame();
        frame.setTitle(mainUser.getUsername() + "'s chat with " + matchedUser.getUsername());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500,500);
        ImageIcon image = new ImageIcon("Messaging logo.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(yellowish);
        frame.setFocusable(true);
        frame.addKeyListener(this);
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
        readFromTextFile(mainUser.getUsername(), matchedUser.getUsername());
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // Will start a new tictactoe game with the matched user if the game button is clicked
        if (e.getSource()==newGame){
            new GameUI(mainUser, matchedUser);

        }

        // Will send the typed message in the text field if the user clicks send or presses enter
        else if (e.getSource()==send) {
            addToTextFile(sendMessage.getText(), mainUser.getUsername(), matchedUser.getUsername());
            try {
                readFromTextFile(mainUser.getUsername(), matchedUser.getUsername());
            } catch (InterruptedException | IOException ex) {
                throw new RuntimeException(ex);
            }
            sendMessage.setText("");
        }

        // Will report the matched user if the report button is clicked
        else if (e.getSource()==report) {
            // Popup to make sure the report was not accidental
            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to report?",
                    "Report", JOptionPane.YES_NO_OPTION);

            // If the user chooses to follow through with the report the code below is executed and chat ends
            if (answer == 0) {
                Report report = new Report(mainUser, matchedUser);
                try {
                    report.checkReport();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                while (true){
                    try {
                        if (!(readFromTextFile(mainUser.getUsername(), matchedUser.getUsername()) < 20)) break;
                    } catch (InterruptedException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    addToTextFile("REPORTED_DELETE", mainUser.getUsername(), matchedUser.getUsername());
                }
            }

        }

        // Will return to the home secreen
        else if (e.getSource()==back) {
            try {
                new WelcomePage(mainUser);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

        // Used by the timer to keep reading from the text file
        else {
            try {
                readFromTextFile(mainUser.getUsername(), matchedUser.getUsername());
            } catch (InterruptedException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // Writes the types message by the main user to the text file
    public void addToTextFile(String messages, String username1, String username2){
        // Two strings to check for either type of file name
        String s = "src/" + username1 + username2 + ".txt";
        String s1 = "src/" + username2 + username1 + ".txt";
        if (new File(s).exists()) {
            try {
                FileWriter writer = new FileWriter(s, true);
                if (messages.equals("REPORTED_DELETE")){
                    writer.write("\n");
                } else{
                    writer.write(username1 + ": " + messages + "\n");
                }
                writer.close();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
        else{
            try {
                FileWriter writer = new FileWriter(s1, true);
                if (messages.equals("REPORTED_DELETE")){
                    writer.write("\n");
                } else{
                    writer.write(username1 + ": " + messages + "\n");
                }
                writer.close();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    // Reads from the chat text file to display it to the users
    public int readFromTextFile(String username1, String username2) throws InterruptedException, IOException {
        displayed.setText("");
        ArrayList<String> list_of_messages = new ArrayList<>();
        String s = "src/" + username1 + username2 + ".txt";
        String s1 = "src/" + username2 + username1 + ".txt";
        File f = new File(s);
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
                File file = new File(s1);
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

        // Supposed to prompt the users to choose if they want to extend the chat or not
        // TODO: Add later?
        if ((list_of_messages.size() - (numExtended * 20)) >= 20){
//            ArrayList<User> main = new ArrayList<>();
//            int answer = JOptionPane.showConfirmDialog(null, "Do you want to extend this chat" +
//                            " (use 20 points)? You have " + mainUser.getPoints() + " points.", "",
//                    JOptionPane.YES_NO_OPTION);
//            if (answer == 0){
//                Event e = new Event("SpendExtend", main);
//                PointSystemS ps = new PointSystemS();
//                e.execute(ps);
//                numExtended += 1;
//            } else {
//                endChat();
//            }
            endChat();
        }
        return list_of_messages.size();
    }

    // Ends the chat after the limit has been reached
    public void endChat() throws InterruptedException, IOException {
        // Stops timer which keeps reading from the file
        timer.stop();

        // Makes arraylist to pass users to the event / point system
        ArrayList <User> users = new ArrayList<>();
        users.add(mainUser); users.add(matchedUser);

        // Creates a new event with eventType ChatEnd.
        Event e = new Event("ChatEnd", users);
        PointSystemR ps = new PointSystemR();
        e.execute(ps);

        // Checks for either order of file name and wipes the one that is found
        String s1 = "src/" + mainUser.getUsername() + matchedUser.getUsername() + ".txt";
        String s2 = "src/" + matchedUser.getUsername() + mainUser.getUsername() + ".txt";

        // Displays a message to the user that the chat has now ended
        JOptionPane.showMessageDialog(null, "Text limit reached. Chat will now end.");

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

        // Force closes the Chat GUI
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Checks for the enter key being pressed so that the user doesn't have to click the send button every time
        if (e.getKeyCode() == 10){
            // Adds the text to the text file, displays the updated text file, and sets the text box to empty
            addToTextFile(sendMessage.getText(), mainUser.getUsername(), matchedUser.getUsername());
            try {
                readFromTextFile(mainUser.getUsername(), matchedUser.getUsername());
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            sendMessage.setText("");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

// Two user tests that can be run concurrently to test the GUI from each users perspective
class testUser1{
    public static void main(String[] args) throws IOException, InterruptedException {
        User user1 = new User("Manit", "Casual");
        User user2 = new User("Arian", "Casual");

        String s1 = "src/" + user1.getUsername() + user2.getUsername() + ".txt";
        String s2 = "src/" + user2.getUsername() + user1.getUsername() + ".txt";

        if (!new File(s2).exists() && !new File(s1).exists()){
            File f = new File(s1);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }
        }

        ChatScreen testChat = new ChatScreen(user1, user2);
        testChat.setVisible(true);
    }
}

class testUser2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        User user1 = new User("Arian", "Casual");
        User user2 = new User("Manit", "Casual");

        String s1 = "src/" + user1.getUsername() + user2.getUsername() + ".txt";
        String s2 = "src/" + user2.getUsername() + user1.getUsername() + ".txt";

        if (!new File(s2).exists() && !new File(s1).exists()){
            File f = new File(s1);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }
        }

        ChatScreen testChat = new ChatScreen(user1, user2);
        testChat.setVisible(true);
    }
}
