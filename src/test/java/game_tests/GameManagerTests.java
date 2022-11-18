package game_tests;

import org.junit.*;
import useCases.GameManager;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;

import static org.junit.Assert.*;

public class GameManagerTests {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 500)
    public void testHorizontalWinX() {
        ArrayList<String> board = new ArrayList<String>(Arrays.asList("X", "X", "X", "O", "X", "O", "", "O", ""));

        GameManager winner = new GameManager();

        int[] expected = new int[]{0, 1, 2, 1};
        int[] actual = winner.check(board);

        Assert.assertArrayEquals(expected, actual);

    }

    @Test(timeout = 500)
    public void testVerticalWinO() {
        ArrayList<String> board = new ArrayList<String>(Arrays.asList("X", "X", "O", "O", "X", "O", "", "", "O"));

        GameManager winner = new GameManager();

        int[] expected = new int[]{2, 5, 8, 0};
        int[] actual = winner.check(board);

        Assert.assertArrayEquals(expected, actual);

    }

    @Test(timeout = 500)
    public void testLeftDiagonalWinX() {
        ArrayList<String> board = new ArrayList<String>(Arrays.asList("X", "X", "O", "O", "X", "O", "", "", "X"));

        GameManager winner = new GameManager();

        int[] expected = new int[]{0, 4, 8, 1};
        int[] actual = winner.check(board);

        Assert.assertArrayEquals(expected, actual);

    }

    @Test(timeout = 500)
    public void testRightDiagonalWinO() {
        ArrayList<String> board = new ArrayList<String>(Arrays.asList("X", "X", "O", "O", "O", "", "O", "", "X"));

        GameManager winner = new GameManager();

        int[] expected = new int[]{2, 4, 6, 0};
        int[] actual = winner.check(board);

        Assert.assertArrayEquals(expected, actual);

    }
}
