package UI;

import Databases.ReadGraph;
import EventPackage.Event;
import Interfaces.ChatScreenInt;
import controllers.ReportController;
import entities.Graph;
import entities.User;
import PointSystem.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains code for the GUI for ChatScreen, as well as code for editing the text file containing the chat
 * between the two users, reading from the text file containing the chat between the two users, and ending the chat
 * after the message limit has been reached.
 *
 * @author Arian Khademi
 * @version 2.0
 * @since December 8th, 2022
 */
public class ChatScreen extends JFrame implements ActionListener, KeyListener, ChatScreenInt {
    // Declaring instance attributes
    public User mainUser;
    public User matchedUser;
    public int numExtended = 0;

    // Declaring all buttons and text fields and the timer to be accessed both by actionPerformed and constructor
    JFrame frame;
    JButton newGame; JButton send; JButton report;
    JTextField sendMessage; JTextArea displayed;
    Timer timer = new Timer(500, this);

    /**
     * This is the constructor for when a new ChatScreen is opened.
     * Below, the constructor contains a lot of code for creating and styling components of the GUI, such as: labels,
     * buttons, and panels. The code also sets the instance attributes mainUser and matchedUser to user1 and user2
     * respectively. There is also code to start the timer to constantly update the display, to edit the frame's
     * properties, and for a KeyListener which detects if the enter key has been pressed.
     *
     * @param user1 the main user who is accessing the GUI.
     * @param user2 the user the main user is matched with and will be messaging.
     */
    public ChatScreen(User user1, User user2) throws IOException {
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

        // TextArea to contain and display all the text within the chat
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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        frame.add(topPanel);
        textPanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(textPanel);
        sendPanel.add(sendMessage);
        frame.add(sendPanel);
        frame.add(send);
        frame.setLayout(null);
        frame.setVisible(true);
        readFromTextFile();
    }

    /**
     * Method to set whether the GUI is visible or not visible
     *
     * @param setting the desired visibility setting of the GUI window.
     */
    @Override
    public void setVisible(boolean setting){
        frame.setVisible(setting);
    }

    /**
     * Method to execute code after event.
     * This method contains code to be executed depending on whether a specific button was clicked, or whether it is
     * the timer which constantly updates the display accessing it.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        // Will start a new ticktacktoe game with the matched user if the game button is clicked
        if (e.getSource()==newGame){
            new GameUI(mainUser, matchedUser);
        }

        // Will send the typed message in the text field if the user clicks send or presses enter
        else if (e.getSource()==send) {
            addToTextFile(sendMessage.getText());
            try {
                readFromTextFile();
            } catch (IOException ex) {
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
                ReportController report = new ReportController();
                try {
                    ReadGraph rg = new ReadGraph();
                    Graph user_graph = rg.readobject();
                    report.reportController(mainUser, user_graph.accounts.get(matchedUser.getUsername()));
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                while (true){
                    try {
                        if (!(readFromTextFile() < 20)) break;
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    addToTextFile("REPORTED_DELETE");
                }
            }

        }

        // Used by the timer to keep reading from the text file
        else {
            try {
                readFromTextFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Writes the message that mainUser has typed to the text file storing the conversation.
     *
     * @param message the message that mainUser has typed.
     */
    public void addToTextFile(String message){
        // Two strings to check for either type of file name
        String fileName1 = "src/" + mainUser.getUsername() + matchedUser.getUsername() + ".txt";
        String fileName2 = "src/" + matchedUser.getUsername() + mainUser.getUsername() + ".txt";
        if (new File(fileName1).exists()) {
            addTextHelper(message, fileName1);
        }
        else{
            addTextHelper(message, fileName2);
        }
    }

    /**
     * Helper method called with the relevant file name to add the message to the file containing the conversation.
     *
     * @param message the message that mainUser has typed.
     * @param fileName the name of the file.
     */
    private void addTextHelper(String message, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            if (message.equals("REPORTED_DELETE")){
                writer.write("\n");
            } else{
                writer.write(mainUser.getUsername() + ": " + message + "\n");
            }
            writer.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * Reads conversation between users.
     * Reads the data in from the text file that contains the conversation between the two users, and simultaneously
     * edits and then updates the display with the messages read in from the file.
     *
     * @return the length of the chat to be accessed by ActionPerformed after clicking the report button
     */
    public int readFromTextFile() throws IOException {
        displayed.setText("");
        ArrayList<String> list_of_messages = new ArrayList<>();
        String s = "src/" + mainUser.getUsername() + matchedUser.getUsername() + ".txt";
        String s1 = "src/" + matchedUser.getUsername() + mainUser.getUsername() + ".txt";
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

        if ((list_of_messages.size() - (numExtended * 20)) >= 20){
            endChat();
        }
        return list_of_messages.size();
    }

    /**
     * Executes code to end and reset the chat and save necessary information.
     * Stops the timer to keep refreshing the display, saves the chat point information to a new Event, prompts the
     * user that the chat has now ended, deletes the chat file if the path can be found, and closes the GUI window.
     */
    public void endChat() throws IOException {
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
        JOptionPane.showMessageDialog(null, "Chat limit reached. Chat will now end.");

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

    /**
     * Executes desired code if a specific key has been typed
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Updates the text file and then updates the display if the enter key has been detected to have been pressed.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Checks for the enter key being pressed so that the user doesn't have to click the send button every time
        if (e.getKeyCode() == 10){
            // Adds the text to the text file, displays the updated text file, and sets the text box to empty
            addToTextFile(sendMessage.getText());
            try {
                readFromTextFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            sendMessage.setText("");
        }
    }

    /**
     * Executes desired code if a specific key has been typed
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}

/**
 * Class to test creating a conversation between two users with user "Manit" as the main user.
 */
class testUser1{
    public static void main(String[] args) throws IOException {
        User user1 = new User("Manit");
        User user2 = new User("Arian");

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

/**
 * Class to test creating a conversation between two users with user "Arian" as the main user.
 */
class testUser2 {
    public static void main(String[] args) throws IOException {
        User user1 = new User("Arian");
        User user2 = new User("Manit");

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