package UI;



import controllers.GameController;
import entities.User;
import useCases.MoveTracker;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameUI extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    User user1;
    User user2;
    boolean player1_turn = true;
    ArrayList<String> board = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", "", "", ""));


    /**
     * @param user1
     * @param user2
     */
    public GameUI(User user1, User user2) {
        /**
         * A frame is created for the Tic-Tac-Toe board
         * The frame is closed when the user closes the window
         */
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        /**
         * A texfield is created on top of the grids and centered in the middle
         * The textfield shows the name of the game in the beginning
         */
        textfield.setBackground(Color.black);
        textfield.setForeground(Color.white);
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        /**
         * 9 buttons are created and placed on the frame in 3x3 size to make a Tic-Tac-Toe board
         */
        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(Color.GRAY);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

//        firstTurn();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            GameController winner = new GameController(textfield, buttons);
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(Color.red);
                        buttons[i].setText("X");
                        MoveTracker move_cont = new MoveTracker();
                        board = move_cont.moves(board, i, "X");
                        player1_turn = false;
                        textfield.setText("O(" + user2.getUsername() + ")turn");
                        winner.Wins(board, user1, user2);
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(Color.blue);
                        buttons[i].setText("O");
                        MoveTracker move_cont = new MoveTracker();
                        board = move_cont.moves(board, i, "O");
                        player1_turn = true;
                        textfield.setText("O(" + user1.getUsername() + ")turn");
                        winner.Wins(board, user1, user2);
                    }
                }
            }
        }
    }

//    public void firstTurn() {
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        if (random.nextInt(2) == 0) {
//            player1_turn = true;
//            textfield.setText("X turn");
//        } else {
//            player1_turn = false;
//            textfield.setText("O turn");
//        }
//    }



//    public ArrayList<String> moves(ArrayList<String> board, int i, String piece) {
//
//        board.set(i, piece);
//        return board;
//
//    }



}