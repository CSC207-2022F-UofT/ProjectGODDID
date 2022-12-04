package controllers;


import EventPackage.Event;
import PointSystem.PointSystemR;
import entities.User;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import useCases.GameManager;



public class GameController {

    JLabel textfield;
    JButton[][] buttons;

    PointSystemR ps = new PointSystemR();

    /**
     * @param textfield
     * @param buttons
     */
    public GameController(JLabel textfield, JButton[][] buttons) {
        this.textfield = textfield;
        this.buttons = buttons;
    }

//    PointSystemR gameEarn = new PointSystemR();

    GameManager checker = new GameManager();


    /**
     * @param board
     * @param user1
     * @param user2
     */
    public void Wins(ArrayList<ArrayList<String>> board, User user1, User user2) throws IOException {
        int[] wins;

        /**
         * Winner is checked first and stores the winning position of the pieces in wins
         */
        wins = checker.check(board);
        if (wins[8] != 2){
            /**
             * If a player wins the winning position on the gridlayout board's background is changed to green
             */
            buttons[wins[0]][wins[1]].setBackground(Color.GREEN);
            buttons[wins[2]][wins[3]].setBackground(Color.GREEN);
            buttons[wins[4]][wins[5]].setBackground(Color.GREEN);
            buttons[wins[6]][wins[7]].setBackground(Color.GREEN);

            /**
             * Every button is disabled after a player makes the winning move
             */
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }

            /**
             * If X (user1) wins their name is printed as the winner
             * If O (user2) wins their name is printed as the winner
             */
            if (wins[8] == 1) {
                textfield.setText(user1.getUsername() + " wins");
                ArrayList<User> users_involved = new ArrayList<>();
                users_involved.add(user1);
                Event game_win = new Event("GameChatEnd", users_involved);
                game_win.execute(ps);

            } else if (wins[8] == 0) {
                textfield.setText(user2.getUsername() + " wins");
                ArrayList<User> users_involved = new ArrayList<>();
                users_involved.add(user2);
                Event game_win = new Event("GameChatEnd", users_involved);
                game_win.execute(ps);
            }
        }
    }

}