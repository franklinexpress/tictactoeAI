package com.tic.franklin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        IBoard board = new Board(1, 2);
        Game game = new Game(sc, board);

        game.start();
    }

}