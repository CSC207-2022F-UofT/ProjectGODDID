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
    User player1;
    User player2;
    boolean player1_turn = true;
    ArrayList<String> board = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", "", "", ""));



    /**
     * @param user1
     * @param user2
     */
    public GameUI(User user1, User user2) {

        player1 = user1;
        player2 = user2;

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
            buttons[i].setFont(new Font(null,Font.PLAIN,100));
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

    }


    /**
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            GameController winner = new GameController(textfield, buttons);
            if (e.getSource() == buttons[i]) {
                /**
                 * If player1_turn is true the first if body is executed
                 * In the body only a move can be made if the grid is empty
                 * The player one (x) is red and (o) is blue
                 * Move tracker converts the moves in the buttons to an arraylist to check the board to see if
                 * there is a winner
                 */
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(Color.red);
                        buttons[i].setText("X");
                        MoveTracker move_cont = new MoveTracker();
                        board = move_cont.moves(board, i, "X");
                        player1_turn = false;
                        textfield.setText("O(" + player2.getUsername() + ") turn");
                        /**
                         * Calls the controller to check the winner and the controller calls the usecase which
                         * follows the Clean Architecture and Dependency inversion principle
                         */
                        winner.Wins(board, player1, player2);
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(Color.blue);
                        buttons[i].setText("O");
                        MoveTracker move_cont = new MoveTracker();
                        board = move_cont.moves(board, i, "O");
                        player1_turn = true;
                        textfield.setText("X(" + player1.getUsername() + ") turn");
                        winner.Wins(board, player1, player2);
                    }
                }
            }
        }
    }

}