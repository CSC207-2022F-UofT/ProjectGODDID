package controllers;

import entities.User;
import useCases.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class GameController {

    JLabel textfield;
    JButton[] buttons;

    /**
     * @param textfield
     * @param buttons
     */
    public GameController(JLabel textfield, JButton[] buttons) {
        this.textfield = textfield;
        this.buttons = buttons;
    }

    GameManager checker = new GameManager();


    /**
     * @param board
     * @param user1
     * @param user2
     */
    public void Wins(ArrayList<String> board, User user1, User user2) {
        int[] wins;

        /**
         * Winner is checked first and stores the winning position of the pieces in wins
         */
        wins = checker.check(board);
        if (wins[3] != 2){
            /**
             * If a player wins the winning position on the gridlayout board's background is changed to green
             */
            buttons[wins[0]].setBackground(Color.GREEN);
            buttons[wins[1]].setBackground(Color.GREEN);
            buttons[wins[2]].setBackground(Color.GREEN);


            /**
             * Every button is disabled after a player makes the winning move
             */
            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }

            /**
             * If X (user1) wins their name is printed as the winner
             * If O (user2) wins their name is printed as the winner
             */
            if (wins[3] == 1) {
                textfield.setText(user1.getUsername() + "wins");
            } else if (wins[3] == 0) {
                textfield.setText(user2.getUsername() + "wins");
            }
        }
    }


}
