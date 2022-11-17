package game_tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import useCases.GameManager;
import useCases.MoveTracker;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;

import static org.junit.Assert.*;

public class MoveTrackerTests {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 500)
    public void testOMove() {
        ArrayList<String> board = new ArrayList<String>(Arrays.asList("X", "X", "X", "O", "X", "O", "", "O", ""));

        MoveTracker mover = new MoveTracker();

        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("X", "X", "X", "O", "X", "O", "O", "O", ""));
        ArrayList<String> actual = mover.moves(board, 6, "O");

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    }

    @Test(timeout = 500)
    public void testXMove() {
        ArrayList<String> board = new ArrayList<String>(Arrays.asList("X", "X", "X", "O", "X", "O", "", "O", "X"));

        MoveTracker mover = new MoveTracker();

        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("X", "X", "X", "O", "X", "O", "", "O", "X"));
        ArrayList<String> actual = mover.moves(board, 8, "X");

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    }
}
