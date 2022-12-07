package game_tests;

import controllers.GameController;
import entities.User;
import org.junit.*;
import useCases.GameManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GameControllerTests {

    @Test
    public void testXWinsTextfield() throws IOException {
        ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();

        ArrayList<String> row = new ArrayList<String>();

        JLabel textfield = new JLabel();

        JButton[][] buttons = new JButton[6][7];

        User user1 = new User("Mert");
        User user2 = new User("Manit");

        for (int i = 0; i < 4; i++){
            row = new ArrayList<>(Arrays.asList("", "", "", "", "", "", ""));
            board.add(row);
        }

        row = new ArrayList<>(Arrays.asList("O", "X", "X", "X", "X", "", ""));
        board.add(row);

        row = new ArrayList<>(Arrays.asList("O", "X", "O", "O", "X", "O", ""));
        board.add(row);

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

        User user1 = new User("Mert");
        User user2 = new User("Manit");

        for (int i = 0; i < 4; i++){
            row = new ArrayList<>(Arrays.asList("", "", "", "", "", "", ""));
            board.add(row);
        }

        row = new ArrayList<>(Arrays.asList("O", "O", "O", "O", "X", "", ""));
        board.add(row);

        row = new ArrayList<>(Arrays.asList("X", "X", "X", "O", "X", "O", "X"));
        board.add(row);

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

        String expected = "Manit wins";
        String actual = textfield.getText();

        assertEquals(expected, actual);
    }
}
