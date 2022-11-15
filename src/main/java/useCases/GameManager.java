package useCases;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {


//    Random random;
//    JLabel textfield;
//    JButton[] buttons;
//    boolean player1_turn;
//
//    public GameManager(Random random, JLabel textfield, JButton[] buttons, boolean player1_turn) {
//        this.random = random;
//        this.textfield = textfield;
//        this.buttons = buttons;
//        this.player1_turn = player1_turn;
//    }



    public int getIndex(JButton[] arr, JButton value) {

        int k = 0;
        for (int i = 0; i < arr.length; i++){
            if(arr[i].equals(value)){
                k = i;
                break;
            }
        }
        return k;
    }

//    public void check() {
//        //check X win conditions
//
//        for (int i = 0; i < 3; i+= 3){
//            if (buttons[i].getText().equals("X") && buttons[i + 1].getText().equals("X") &&
//                    buttons[i + 2].getText().equals("X")){
//                xWins(getIndex(buttons, buttons[i]), getIndex(buttons, buttons[i + 1]),getIndex(buttons, buttons[i + 2]));
//            }
//        }
//
//        for (int i = 0; i < 3; i++){
//            if (buttons[i].getText().equals("X") && buttons[i + 3].getText().equals("X") &&
//                    buttons[i + 6].getText().equals("X")){
//                xWins(getIndex(buttons, buttons[i]), getIndex(buttons, buttons[i + 3]),getIndex(buttons, buttons[i + 6]));
//            }
//        }
//
//        if((buttons[0].getText().equals("X")) &&
//                (buttons[4].getText().equals("X")) &&
//                (buttons[8].getText().equals("X"))
//        ) {
//            xWins(0,4,8);
//        }
//        if((buttons[2].getText().equals("X")) &&
//                (buttons[4].getText().equals("X")) &&
//                (buttons[6].getText().equals("X"))
//        ) {
//            xWins(2,4,6);
//        }
//
//        for (int i = 0; i < 3; i+= 3){
//            if (buttons[i].getText().equals("O") && buttons[i + 1].getText().equals("O") &&
//                    buttons[i + 2].getText().equals("O")){
//                xWins(getIndex(buttons, buttons[i]), getIndex(buttons, buttons[i + 1]),getIndex(buttons, buttons[i + 2]));
//            }
//        }
//
//        for (int i = 0; i < 3; i++){
//            if (buttons[i].getText().equals("O") && buttons[i + 3].getText().equals("O") &&
//                    buttons[i + 6].getText().equals("O")){
//                xWins(getIndex(buttons, buttons[i]), getIndex(buttons, buttons[i + 3]),getIndex(buttons, buttons[i + 6]));
//            }
//        }
//
//        if((buttons[0].getText().equals("O")) &&
//                (buttons[4].getText().equals("O")) &&
//                (buttons[8].getText().equals("O"))
//        ) {
//            oWins(0,4,8);
//        }
//        if(
//                (buttons[2].getText().equals("O")) &&
//                        (buttons[4].getText().equals("O")) &&
//                        (buttons[6].getText().equals("O"))
//        ) {
//            oWins(2,4,6);
//        }
//    }

    public int[] check(ArrayList<String> board) {
        //check X win conditions

        for (int i = 0; i < 3; i += 3) {
            if (board.get(i).equals("X") && board.get(i + 1).equals("X") &&
                    board.get(i + 2).equals("X")) {
                return new int[]{i, i + 1, i + 2, 1};
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board.get(i).equals("X") && board.get(i + 3).equals("X") &&
                    board.get(i + 6).equals("X")) {
                return new int[]{i, i + 3, i + 6, 1};
            }
        }


        if ((board.get(0).equals("X")) && (board.get(4).equals("X")) && (board.get(8).equals("X"))) {
            return new int[]{0, 4, 8, 1};
        }
        if ((board.get(2).equals("X")) && (board.get(4).equals("X")) && (board.get(6).equals("X"))) {
            return new int[]{2, 4, 6, 1};
        }

        for (int i = 0; i < 3; i += 3) {
            if (board.get(i).equals("O") && board.get(i + 1).equals("O") &&
                    board.get(i + 2).equals("O")) {
                return new int[]{i, i + 1, i + 2, 0};
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board.get(i).equals("O") && board.get(i + 3).equals("O") &&
                    board.get(i + 6).equals("O")) {
                return new int[]{i, i + 3, i + 6, 0};
            }
        }

        if ((board.get(0).equals("O")) && (board.get(4).equals("O")) && (board.get(8).equals("O"))) {
            return new int[]{0, 4, 8, 0};
        }
        if ((board.get(2).equals("O")) && (board.get(4).equals("O")) && (board.get(6).equals("O"))) {
            return new int[]{2, 4, 6, 0};
        }
        return new int[]{0, 0, 0, 2};
    }


}

