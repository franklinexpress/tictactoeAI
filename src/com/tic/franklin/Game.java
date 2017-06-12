package com.tic.franklin;

import java.util.Scanner;

/**
 * Created by franklin on 6/12/17.
 */
public class Game {

    Scanner sc;
    IBoard board;

    public Game(Scanner scanner, IBoard board) {
        sc = scanner;
        this.board = board;
    }

    public void start() {
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

    public  void newGame(boolean isSinglePlayer) {

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
        System.out.println(board.getEndResult());
    }


    public  Point getMove(int player) {

        Scanner sc = new Scanner(System.in);

        int x;
        int y;
       // String symbol = (player == Board.PLAYER_X ? "X" : "O");
        System.out.print("\n Player " + player + " | Enter X Y values, space separated [0 - 2] -> ");

        x = sc.nextInt();
        y = sc.nextInt();
        return new Point(x, y);
    }


}
