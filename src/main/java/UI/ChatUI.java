package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChatUI extends JFrame implements ActionListener {
    String person = "James";
    String person2 = "Lana";
    String person3 = "Melissa";

    ArrayList<String> myList;

    JTextField tf;
    JPanel f;
    JFrame frame;


    ChatUI(){
        myList = new ArrayList<>();
        myList.add(person);
        myList.add(person2);
        myList.add(person3);

        //Creating the Frame
        frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        tf = new JTextField(10);
        JButton send = new JButton("Send");
        send.addActionListener(this);

        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        ;

        // Text Area at the Center
        //JFrame f = new JFrame("MyPersonList");
        f = new JPanel();
        f.add(new JList(myList.toArray()));
        //f.pack();
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setLocationRelativeTo(null);
        f.setSize(300, 300);
        f.setVisible(true);
        //JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, f);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myList.add(tf.getText());
        f.remove(0);
        f.add(new JList(myList.toArray()));
        f.revalidate();
        frame.repaint();
        frame.revalidate();
        System.out.println(myList);
    }
}