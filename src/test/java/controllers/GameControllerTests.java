package controllers;


import entities.User;


import org.junit.jupiter.api.Test;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GameControllerTests {


    /**
     * @throws IOException
     * Checks whether the text field is changed to the name of the winner X
     */
    @Test
    public void testXWinsTextfield() throws IOException {
        ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();

        ArrayList<String> row = new ArrayList<String>();

        JLabel textfield = new JLabel();

        JButton[][] buttons = new JButton[6][7];

        /**
         * Creates two users that play against each other
         */
        User user1 = new User("Mert");
        User user2 = new User("Manit");

        /**
         * Creating a board where X (Mert) is the winner based on moves so far
         */
        for (int i = 0; i < 4; i++){
            row = new ArrayList<>(Arrays.asList("", "", "", "", "", "", ""));
            board.add(row);
        }

        row = new ArrayList<>(Arrays.asList("O", "X", "X", "X", "X", "", ""));
        board.add(row);

        row = new ArrayList<>(Arrays.asList("O", "X", "O", "O", "X", "O", ""));
        board.add(row);

        /**
         * Creates the buttons which are the grids
         */

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                buttons[i][j] = new JButton();
                if (i != 5){
                    buttons[i][j].setEnabled(false);
                }
                buttons[i][j].setFocusable(false);
                buttons[i][j].setFont(new Font(null, Font.PLAIN, 100));
            }
        }

        GameController game_con = new GameController(textfield, buttons);

        game_con.Wins(board, user1, user2);

        /**
         * Check if "Mert wins" is displayed in the textfield
         */
        String expected = "Mert wins";
        String actual = textfield.getText();

        assertEquals(expected, actual);
    }

    @Test
    public void testOWinsTextfield() throws IOException {
        ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();

        ArrayList<String> row = new ArrayList<String>();

        JLabel textfield = new JLabel();

        JButton[][] buttons = new JButton[6][7];
        /**
         * Creates two users that play against each other
         */
        User user1 = new User("Mert");
        User user2 = new User("Manit");
        /**
         * Creating a board where O (Manit) is the winner based on moves so far
         */
        for (int i = 0; i < 4; i++){
            row = new ArrayList<>(Arrays.asList("", "", "", "", "", "", ""));
            board.add(row);
        }

        row = new ArrayList<>(Arrays.asList("O", "O", "O", "O", "X", "", ""));
        board.add(row);

        row = new ArrayList<>(Arrays.asList("X", "X", "X", "O", "X", "O", "X"));
        board.add(row);

        /**
         * Creates the buttons which are the grids
         */

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                buttons[i][j] = new JButton();
                if (i != 5){
                    buttons[i][j].setEnabled(false);
                }
                buttons[i][j].setFocusable(false);
                buttons[i][j].setFont(new Font(null, Font.PLAIN, 100));
            }
        }

        GameController game_con = new GameController(textfield, buttons);

        game_con.Wins(board, user1, user2);

        /**
         * Check if "Manit wins" is displayed in the textfield
         */
        String expected = "Manit wins";
        String actual = textfield.getText();

        assertEquals(expected, actual);
    }
}
