package game_tests;

import org.junit.*;
import useCases.GameManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;

public class GameManagerTests {


    /**
     * Test when X wins horizontally
     */
    @Test
    public void testHorizontalWinX() {
        ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
        ArrayList<String> row = new ArrayList<String>();

        for (int i = 0; i < 4; i++){
            row = new ArrayList<>(Arrays.asList("", "", "", "", "", "", ""));
            board.add(row);
        }

        row = new ArrayList<>(Arrays.asList("O", "X", "X", "X", "X", "", ""));
        board.add(row);

        row = new ArrayList<>(Arrays.asList("O", "X", "O", "O", "X", "O", ""));
        board.add(row);


        GameManager winner = new GameManager();

        int[] expected = new int[]{4, 1, 4, 2, 4, 3, 4, 4, 1};
        int[] actual = winner.check(board);

        Assert.assertArrayEquals(expected, actual);

    }

    /**
     * Test when O wins vertically
     */
    @Test
    public void testVerticalWinO() {
        ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
        ArrayList<String> row = new ArrayList<String>();

        for (int i = 0; i < 4; i++) {
            row = new ArrayList<String>(Arrays.asList("", "", "O", "", "", "", ""));
            board.add(row);
        }

        row = new ArrayList<String>(Arrays.asList("", "", "X", "", "", "", ""));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("", "X", "X", "O", "", "X", "X"));
        board.add(row);


        GameManager winner = new GameManager();

        int[] expected = new int[]{0, 2, 1, 2, 2, 2, 3, 2, 0};
        int[] actual = winner.check(board);

        Assert.assertArrayEquals(expected, actual);

    }

    /**
     * Test when X wins from the left diagonal
     */
    @Test
    public void testLeftDiagonalWinX() {
        ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
        ArrayList<String> row = new ArrayList<String>();

        row = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", ""));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("", "X", "", "", "", "", ""));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("", "O", "X", "", "", "", ""));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("", "O", "O", "X", "", "", ""));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("", "O", "X", "X", "X", "", ""));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("", "X", "O", "X", "O", "O", ""));
        board.add(row);

        GameManager winner = new GameManager();

        int[] expected = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 1};
        int[] actual = winner.check(board);

        Assert.assertArrayEquals(expected, actual);

    }

    /**
     * Test when O wins from the right diagonal
     */
    @Test
    public void testRightDiagonalWinO() {
        ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
        ArrayList<String> row = new ArrayList<String>();

        row = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", ""));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", "O"));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("", "", "", "", "", "O", "X"));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("", "", "", "", "O", "O", "X"));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("", "", "", "O", "X", "X", "O"));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("", "X", "X", "X", "O", "O", "X"));
        board.add(row);

        GameManager winner = new GameManager();

        int[] expected = new int[]{1, 6, 2, 5, 3, 4, 4, 3, 0};
        int[] actual = winner.check(board);

        Assert.assertArrayEquals(expected, actual);

    }
}
