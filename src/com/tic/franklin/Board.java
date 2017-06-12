package com.tic.franklin;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by franklin on 5/23/17.
 */
public class Board {

    public  static int PLAYER_X;
    public  static int PLAYER_O;


    private int[][] board = new int[3][3];

    public Board(int playerX, int playerY) {
        PLAYER_X = playerX;
        PLAYER_O = playerY;
    }


    public boolean hasPlayerWon(int player) {
        //these are diagonal coordinates, checking if diagonal elements are equal to player
        if ((board[0][0] == player && board[1][1] == board[0][0] && board[0][0] == board[2][2]) ||
                (board[0][2] == player && board[1][1] == board[0][2] && board[2][0] == board[0][2])) {
            return true;
        }

        for (int i = 0; i < 3; i++) {

            //check if elements in row are equal to player
            if (board[i][0] == player && board[i][1] == board[i][0] && board[i][2] == board[i][0]) {
                return true;
            }

            //check if elements in column are equal to player
            if (board[0][i] == player && board[1][i] == board[0][i] && board[2][i] == board[0][i]) {
                return true;
            }
        }

        return false;
    }


    public boolean isGameOver() {
        return hasPlayerWon(PLAYER_X) || hasPlayerWon(PLAYER_O) || getBlankSlots().isEmpty();
    }


    public String getEndResult() {
        if (hasPlayerWon(PLAYER_X)) {
            return "Player " + PLAYER_X + " WINS!";
        } else if (hasPlayerWon(PLAYER_O)) {
            return "Player " + PLAYER_O + " WINS";
        } else {
            return "IT'S A DRAW";
        }
    }

    public void displayBoard() {

        System.out.println();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == PLAYER_O) {
                    System.out.print(PLAYER_O);
                } else if (board[i][j] == PLAYER_X) {
                    System.out.print(PLAYER_X);
                } else {
                    System.out.print(" _ ");
                }
            }
            System.out.println("\n");
        }
    }


    public List<Point> getBlankSlots() {

        //basically, check where board[i][j] == 0 and add it to the list as a Point object
        List<Point> blankSlots = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) blankSlots.add(new Point(i, j));
            }
        }
        return blankSlots;
    }


    public boolean placeMove(Point point, int player, boolean backtracking) {

        /*
        already played that slot

        only check this if the algorithm is not running, only during gameplay!
        backtracking is true only when calling this method from miniMax()
        */
        if (board[point.getX()][point.getY()] != 0 && !backtracking) {
            return false;
        }

        if (point.getX() > 2 || point.getY() > 2)
            return false;

        board[point.getX()][point.getY()] = player;

        return true;
    }


    public Point miniMax(int whosTurn) {

        if (hasPlayerWon(PLAYER_X)) {
            return new Point(-1);
        } else if (hasPlayerWon(PLAYER_O)) {
            return new Point(1);
        } else if (getBlankSlots().isEmpty()) { //no one won and board is full
            return new Point(0);
        }

        //store all possible moves in this list
        List<Point> moves = new ArrayList<>();

        for (int i = 0; i < getBlankSlots().size(); i++) {
            // get a Point from available slots
            Point point = getBlankSlots().get(i);
            placeMove(point, whosTurn, true);
            if (whosTurn == PLAYER_X) {
                point.setScore(miniMax(PLAYER_O).getScore());
            } else {
                point.setScore(miniMax(PLAYER_X).getScore());
            }

            //save move
            moves.add(point);

            //reset board back to previous state
            placeMove(point, 0, true);
        }

        int bestMove = 0;
        if (whosTurn == PLAYER_O) {
            int bestScore = Integer.MIN_VALUE;

            //find max in array
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).getScore() > bestScore) {
                    bestMove = i; //point at this index has highest score

                    //keep current max score
                    bestScore = moves.get(i).getScore();
                }
            }
        } else {

            //find the lowest value in the array
            int bestScore = Integer.MAX_VALUE;
            //find max in array
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).getScore() < bestScore) {
                    bestMove = i;

                    //keep current max score
                    bestScore = moves.get(i).getScore();
                }
            }
        }
        return moves.get(bestMove);
    }

}
