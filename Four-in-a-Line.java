//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                          //
//  Source File Name:           Four-in-a-Line.java                                                         //
//  Name:                       Flucus Oscar HO                                                             //
//  Description Of The Program: This program is a game called "Connect Four". It is a two-player game.      //
//                              The game rule is when the first player get four of their pieces in a row    //
//                              horizontally, vertically or diagonally, he/she win the game.                //
//                                                                                                          //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] game = new int[6][7];
        boolean victory;
        boolean usable;
        int overflow = 0, cur;

        //check victory or draw
        do {

            //set player
            cur = overflow % 2 + 1;

            //display game
            drawGrid(game);

            //keyboard input
            int kb;

            //data usable
            do {

                //input data
                System.out.print("Player " + cur + " type a column <0-6> or 9 to quit current game: ");
                kb = sc.nextInt();

                //quit current game
                if (kb == 9) {
                    System.out.print("Bye Bye!");
                    return;
                }

                //check input
                usable = getError(kb, game);

            } while (!usable);

            //input to game
            for (int x = 5; x >= 0; x--) {
                if (game[x][kb] == 0) {
                    game[x][kb] = cur;
                    break;
                }
            }
            overflow++;

            //check victory
            victory = checkVictory(cur, game);

        } while (!victory && overflow != 42);

        //final
        drawGrid(game);

        //display victory or draw
        if (victory) {
            System.out.println("Player " + cur + " win this game!");
        } else {
            System.out.println("Draw!");
        }
    }

    //check value
    public static boolean getError(int kb, int[][] game) {

        //check game column range
        if (kb < 0 || kb > 6) {
            System.out.println("Range of column should be 0 to 6!");
            return false;
        }

        //check column full
        if (game[0][kb] != 0) {
            System.out.println("Column " + kb + " is full!");
            return false;
        }
        return true;
    }

    //check the victory
    public static boolean checkVictory(int curplayer, int[][] game) {

        //check diagonally 01
        for (int x = 3; x < 6; x++) {
            for (int y = 0; y < 7 - 3; y++) {
                if (game[x][y] == curplayer && game[x - 1][y + 1] == game[x][y] && game[x - 2][y + 2] == game[x][y] && game[x - 3][y + 3] == game[x][y]) {
                    return true;
                }
            }
        }

        //check diagonally 02
        for (int x = 0; x < 6 - 3; x++) {
            for (int y = 0; y < 7 - 3; y++) {
                if (game[x][y] == curplayer && game[x + 1][y + 1] == game[x][y] && game[x + 2][y + 2] == game[x][y] && game[x + 3][y + 3] == game[x][y]) {
                    return true;
                }
            }
        }

        //check horizontal
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7 - 3; y++) {
                if (game[x][y] == curplayer && game[x][y + 1] == game[x][y] && game[x][y + 2] == game[x][y] && game[x][y + 3] == game[x][y]) {
                    return true;
                }
            }
        }

        //check vertical
        for (int x = 0; x < 6 - 3; x++) {
            for (int y = 0; y < 7; y++) {
                if (game[x][y] == curplayer && game[x + 1][y] == game[x][y] && game[x + 2][y] == game[x][y] && game[x + 3][y] == game[x][y]) {
                    return true;
                }
            }
        }
        return false;
    }

    //display the game
    public static void drawGrid(int[][] game) {

        //display table
        int i = 5;
        for (int x = 0; x < 6; x++) {
            System.out.print(i + " | ");
            i--;
            for (int y = 0; y < 7; y++) {
                System.out.print(game[x][y]);
                System.out.print(" ");
            }
            System.out.println();
        }

        //display column number and table style
        System.out.print("   ");
        for (int ln = 0; ln <= 6; ln++)
            System.out.print("--");
        System.out.println();
        System.out.print("    ");
        for (int display = 0; display <= 6; display++)
            System.out.print(display + " ");
        System.out.println();

    }
}