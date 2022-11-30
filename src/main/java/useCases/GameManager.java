package useCases;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {

    /**
     * @param board
     * @return
     */
    public int[] check(ArrayList<ArrayList<String>> board) {

        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.get(0).size() - 3; col++) {
                if (board.get(row).get(col).equals("X") &&
                        board.get(row).get(col + 1).equals("X") &&
                        board.get(row).get(col + 2).equals("X") &&
                        board.get(row).get(col + 3).equals("X")) {
                    return new int[]{row, col, row, col + 1, row, col + 2, row, col + 3, 1};
                }

                if (board.get(row).get(col).equals("O") &&
                        board.get(row).get(col + 1).equals("O") &&
                        board.get(row).get(col + 2).equals("O") &&
                        board.get(row).get(col + 3).equals("O")) {
                    return new int[]{row, col, row, col + 1, row, col + 2, row, col + 3, 0};
                }
            }
        }

        for (int row = 0; row < board.size() - 3; row++) {
            for (int col = 0; col < board.get(0).size(); col++) {
                if (board.get(row).get(col).equals("X") &&
                        board.get(row + 1).get(col).equals("X") &&
                        board.get(row + 2).get(col).equals("X") &&
                        board.get(row + 3).get(col).equals("X")) {
                    return new int[]{row, col, row + 1, col, row + 2, col, row + 3, col, 1};
                }

                if (board.get(row).get(col).equals("O") &&
                        board.get(row + 1).get(col).equals("O") &&
                        board.get(row + 2).get(col).equals("O") &&
                        board.get(row + 3).get(col).equals("O")) {
                    return new int[]{row, col, row + 1, col, row + 2, col, row + 3, col, 0};
                }
            }
        }

        for (int row = 0; row < board.size() - 3; row++) {
            for (int col = 0; col < board.get(0).size() - 3; col++) {
                if (board.get(row).get(col).equals("X") &&
                        board.get(row + 1).get(col + 1).equals("X") &&
                        board.get(row + 2).get(col + 2).equals("X") &&
                        board.get(row + 3).get(col + 3).equals("X")) {
                    return new int[]{row, col, row + 1, col + 1, row + 2, col + 2, row + 3, col + 3, 1};
                }

                if (board.get(row).get(col).equals("O") &&
                        board.get(row + 1).get(col + 1).equals("O") &&
                        board.get(row + 2).get(col + 2).equals("O") &&
                        board.get(row + 3).get(col + 3).equals("O")) {
                    return new int[]{row, col, row + 1, col + 1, row + 2, col + 2, row + 3, col + 3, 0};
                }
            }
        }

        for (int row = 0; row < board.size() - 3; row++) {
            for (int col = 0; col < board.get(0).size() - 3; col++) {
                if (board.get(row).get(col + 3).equals("X") &&
                        board.get(row + 1).get(col + 2).equals("X") &&
                        board.get(row + 2).get(col + 1).equals("X") &&
                        board.get(row + 3).get(col).equals("X")) {
                    return new int[]{row, col + 3, row + 1, col + 2, row + 2, col + 1, row + 3, col, 1};
                }

                if (board.get(row).get(col + 3).equals("O") &&
                        board.get(row + 1).get(col + 2).equals("O") &&
                        board.get(row + 2).get(col + 1).equals("O") &&
                        board.get(row + 3).get(col).equals("O")) {
                    return new int[]{row, col + 3, row + 1, col + 2, row + 2, col + 1, row + 3, col, 0};
                }
            }
        }

        return new int[]{0, 0, 0, 0, 0, 0, 0, 0, 2};
    }
}