package useCases;

import java.util.ArrayList;

public class MoveTracker {

    public ArrayList<ArrayList<String>> moves(ArrayList<ArrayList<String>> board, int i, int j, String piece) {

        ArrayList<String> row = board.get(i);
        row.set(j, piece);
        board.set(i, row);
        return board;

    }
}
