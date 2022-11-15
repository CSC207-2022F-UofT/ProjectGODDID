package controllers;

import useCases.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import useCases.GameManager;


public class GameController {

    JLabel textfield;
    JButton[] buttons;

    public GameController(JLabel textfield, JButton[] buttons) {
        this.textfield = textfield;
        this.buttons = buttons;
    }

    GameManager checker = new GameManager();


//    GameManager game_man = new GameManager(textfield, buttons);
//
//    public GameController(JLabel textfield, JButton[] buttons) {
//        this.textfield = textfield;
//        this.buttons = buttons;
//    }

    public void Wins(ArrayList<String> board) {
        int[] wins;
        wins = checker.check(board);
        if (wins[3] != 2){
            buttons[wins[0]].setBackground(Color.GREEN);
            buttons[wins[1]].setBackground(Color.GREEN);
            buttons[wins[2]].setBackground(Color.GREEN);

            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }

            if (wins[3] == 1) {
                textfield.setText("X wins");
            } else if (wins[3] == 0) {
                textfield.setText("O wins");
            }
        }
    }


}
