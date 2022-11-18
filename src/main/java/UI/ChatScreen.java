package UI;

import javax.swing.*;
import java.awt.event.*;

class ChatScreen extends JFrame implements ActionListener {

    JTextArea jt;
    JButton playgame, report, sendmessage;
    JLabel label;
    JPanel panel;

    ChatScreen(){
        jt = new JTextArea(40,40);
        label = new JLabel();
        label.setText("Friend1");
        playgame = new JButton("Play Game");
        report = new JButton("Report User");
        sendmessage = new JButton("Send");

        panel = new JPanel();
        panel.setBounds(10,10, 500, 500);
        panel.add(playgame);
        panel.add(sendmessage);
        panel.add(report);
        panel.add(label);
        panel.add(jt);


        jt.setVisible(true);
        report.setVisible(true);
        panel.setVisible(true);


        report.addActionListener(this);
        sendmessage.addActionListener(this);
        playgame.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        String message = jt.getText();
//        new AddToTextFile(message, "sure");



    }
}

class ChatScreenDemo{
    public static void main(String[] args) {
        try{
            ChatScreen chatScreen = new ChatScreen();
            chatScreen.setSize(700,700);
            chatScreen.setVisible(true);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
