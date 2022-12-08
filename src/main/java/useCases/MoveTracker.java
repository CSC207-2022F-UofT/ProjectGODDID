package useCases;

import java.util.ArrayList;

public class MoveTracker {

    /**
     * @param board
     * @param i
     * @param j
     * @param piece
     * @return
     *
     * Updates the Connect 4 board after every move based on row (i) and column (j), with the indicated piece (X or O)
     */
    public ArrayList<ArrayList<String>> moves(ArrayList<ArrayList<String>> board, int i, int j, String piece) {

        ArrayList<String> row = board.get(i);
        row.set(j, piece);
        board.set(i, row);
        return board;

    }
}
