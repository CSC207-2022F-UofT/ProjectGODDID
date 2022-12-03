package game_tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import useCases.MoveTracker;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;

public class MoveTrackerTests {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 500)
    public void testOMove() {
        ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
        ArrayList<String> row = new ArrayList<String>();

        for (int i = 0; i < 5; i++){
            row = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", ""));
            board.add(row);
        }

        row = new ArrayList<String>(Arrays.asList("X", "O", "X", "O", "X", "", ""));
        board.add(row);

        MoveTracker mover = new MoveTracker();

        ArrayList<ArrayList<String>> expected = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            row = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", ""));
            expected.add(row);
        }

        row = new ArrayList<String>(Arrays.asList("X", "O", "X", "O", "X", "", "O"));
        expected.add(row);

        ArrayList<ArrayList<String>> actual = mover.moves(board, 5, 6, "O");

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    }

    @Test(timeout = 500)
    public void testXMove() {
        ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
        ArrayList<String> row = new ArrayList<String>();

        for (int i = 0; i < 4; i++){
            row = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", ""));
            board.add(row);
        }

        row = new ArrayList<String>(Arrays.asList("X", "O", "X", "O", "", "X", ""));
        board.add(row);

        row = new ArrayList<String>(Arrays.asList("X", "O", "X", "O", "O", "X", "O"));
        board.add(row);

        MoveTracker mover = new MoveTracker();

        ArrayList<ArrayList<String>> expected = new ArrayList<>();

        for (int i = 0; i < 4; i++){
            row = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", ""));
            expected.add(row);
        }

        row = new ArrayList<String>(Arrays.asList("X", "O", "X", "O", "X", "X", ""));
        expected.add(row);

        row = new ArrayList<String>(Arrays.asList("X", "O", "X", "O", "O", "X", "O"));
        expected.add(row);

        ArrayList<ArrayList<String>> actual = mover.moves(board, 4, 4, "X");

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    }
}
