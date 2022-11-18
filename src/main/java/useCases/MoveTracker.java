package useCases;

import java.util.ArrayList;

public class MoveTracker {

    public ArrayList<String> moves(ArrayList<String> board, int i, String piece) {

        board.set(i, piece);
        return board;

    }
}
