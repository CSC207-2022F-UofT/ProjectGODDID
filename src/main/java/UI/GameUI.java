package UI;



import controllers.GameController;
import useCases.GameManager;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameUI extends JFrame implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    ArrayList<String> board = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", "", "", ""));




    public GameUI() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(Color.black);
        textfield.setForeground(Color.white);
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

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

        firstTurn();
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
                        board = moves(board, i, "X");
                        player1_turn = false;
                        textfield.setText("O turn");
                        winner.Wins(board);
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(Color.blue);
                        buttons[i].setText("O");
                        board = moves(board, i, "O");
                        player1_turn = true;
                        textfield.setText("X turn");
                        winner.Wins(board);
                    }
                }
            }
        }
    }

    //
//
//
    public void firstTurn() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }
    }


    public int getIndex(JButton[] arr, JButton value) {

        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                k = i;
                break;
            }
        }
        return k;
    }

    public ArrayList<String> moves(ArrayList<String> board, int i, String piece) {

        board.set(i, piece);
        return board;

    }

//    public int[] check(ArrayList<String> board) {
//        //check X win conditions
//
//        for (int i = 0; i < 3; i += 3) {
//            if (board.get(i).equals("X") && board.get(i + 1).equals("X") &&
//                    board.get(i + 2).equals("X")) {
//                return new int[]{i, i + 1, i + 2, 1};
//            }
//        }
//
//        for (int i = 0; i < 3; i++) {
//            if (board.get(i).equals("X") && board.get(i + 3).equals("X") &&
//                    board.get(i + 6).equals("X")) {
//                return new int[]{i, i + 3, i + 6, 1};
//            }
//        }
//
//
//        if ((board.get(0).equals("X")) && (board.get(4).equals("X")) && (board.get(8).equals("X"))) {
//            return new int[]{0, 4, 8, 1};
//        }
//        if ((board.get(2).equals("X")) && (board.get(4).equals("X")) && (board.get(6).equals("X"))) {
//            return new int[]{2, 4, 6, 1};
//        }
//
//        for (int i = 0; i < 3; i += 3) {
//            if (board.get(i).equals("O") && board.get(i + 1).equals("O") &&
//                    board.get(i + 2).equals("O")) {
//                return new int[]{i, i + 1, i + 2, 0};
//            }
//        }
//
//        for (int i = 0; i < 3; i++) {
//            if (board.get(i).equals("O") && board.get(i + 3).equals("O") &&
//                    board.get(i + 6).equals("O")) {
//                return new int[]{i, i + 3, i + 6, 0};
//            }
//        }
//
//        if ((board.get(0).equals("O")) && (board.get(4).equals("O")) && (board.get(8).equals("O"))) {
//            return new int[]{0, 4, 8, 0};
//        }
//        if ((board.get(2).equals("O")) && (board.get(4).equals("O")) && (board.get(6).equals("O"))) {
//            return new int[]{2, 4, 6, 0};
//        }
//        return new int[]{0, 0, 0, 2};
//    }

//    public void Wins(int[] wins) {
//        if (wins[3] != 2){
//            buttons[wins[0]].setBackground(Color.GREEN);
//            buttons[wins[1]].setBackground(Color.GREEN);
//            buttons[wins[2]].setBackground(Color.GREEN);
//
//            for (int i = 0; i < 9; i++) {
//                buttons[i].setEnabled(false);
//            }
//
//            if (wins[3] == 1) {
//                textfield.setText("X wins");
//            } else if (wins[3] == 0) {
//                textfield.setText("O wins");
//            }
//        }
//    }
}