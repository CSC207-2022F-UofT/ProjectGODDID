package useCases;

import java.util.ArrayList;

public class GameManager {

    /**
     * @param board
     * @return
     */
    public int[] check(ArrayList<String> board) {
        /**
         * Check the win conditions for X
         */

        /**
         *  Check whether X wins horizontally
         */
        for (int i = 0; i < 3; i += 3) {
            if (board.get(i).equals("X") && board.get(i + 1).equals("X") &&
                    board.get(i + 2).equals("X")) {
                return new int[]{i, i + 1, i + 2, 1};
            }
        }

        /**
         *  Check whether X wins vertically
         */
        for (int i = 0; i < 3; i++) {
            if (board.get(i).equals("X") && board.get(i + 3).equals("X") &&
                    board.get(i + 6).equals("X")) {
                return new int[]{i, i + 3, i + 6, 1};
            }
        }

        /**
         *  Check whether X wins from the left diagonal
         */
        if ((board.get(0).equals("X")) && (board.get(4).equals("X")) && (board.get(8).equals("X"))) {
            return new int[]{0, 4, 8, 1};
        }

        /**
         *  Check whether X wins from the right diagonal
         */
        if ((board.get(2).equals("X")) && (board.get(4).equals("X")) && (board.get(6).equals("X"))) {
            return new int[]{2, 4, 6, 1};
        }

        /**
         * Check the win conditions for O
         */

        /**
         *  Check whether O wins horizontally
         */
        for (int i = 0; i < 3; i += 3) {
            if (board.get(i).equals("O") && board.get(i + 1).equals("O") &&
                    board.get(i + 2).equals("O")) {
                return new int[]{i, i + 1, i + 2, 0};
            }
        }

        /**
         *  Check whether O wins vertically
         */
        for (int i = 0; i < 3; i++) {
            if (board.get(i).equals("O") && board.get(i + 3).equals("O") &&
                    board.get(i + 6).equals("O")) {
                return new int[]{i, i + 3, i + 6, 0};
            }
        }

        /**
         *  Check whether O wins from the left diagonal
         */
        if ((board.get(0).equals("O")) && (board.get(4).equals("O")) && (board.get(8).equals("O"))) {
            return new int[]{0, 4, 8, 0};
        }

        /**
         *  Check whether O wins from the right diagonal
         */
        if ((board.get(2).equals("O")) && (board.get(4).equals("O")) && (board.get(6).equals("O"))) {
            return new int[]{2, 4, 6, 0};
        }
        return new int[]{0, 0, 0, 2};
    }


}

