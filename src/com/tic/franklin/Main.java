package com.tic.franklin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //game loop
        while (true) {
            System.out.println("\nPlay against the Computer (y/n) ? ");

            if (sc.nextLine().equalsIgnoreCase("y"))
                newGame(true);
            else
                newGame(false);

            // at the end of the game, giving the user the option to try again
            System.out.print("\n Play again (y/n) ? ");

            //any input other than "Y" will terminate the program.
            if (!sc.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }
    }


    public static void newGame(boolean isSinglePlayer) {

        Board board = new Board();
        board.displayBoard();

        int player = Board.PLAYER_X; // plays first
        while (!board.isGameOver()) {

            Point point = null;

            //try - because of weird or non numerical inputs
            try {

                if (isSinglePlayer && player == Board.PLAYER_O)
                    point = board.miniMax(player);
                else
                    point = getMove(player);

                if (!board.placeMove(point, player, false)) {

                    System.out.println("Invalid Move");
                    continue;
                }
                player = (player == Board.PLAYER_X ? Board.PLAYER_O : Board.PLAYER_X);
                board.displayBoard();

            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }
        System.out.println(board.getWinner());
    }


    public static Point getMove(int player) {

        Scanner sc = new Scanner(System.in);

        int x;
        int y;
        String symbol = (player == Board.PLAYER_X ? "X" : "O");
        System.out.print("\n Player " + symbol + " | Enter X Y values, space separated [0 - 2] -> ");

        x = sc.nextInt();
        y = sc.nextInt();
        return new Point(x, y);
    }




}