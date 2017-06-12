package com.tic.franklin;

import java.util.List;

/**
 * Created by franklin on 6/12/17.
 */
public interface IBoard {
    public boolean hasPlayerWon(int player);
    public boolean isGameOver();
    public String getEndResult();
    public void displayBoard();
    public List<Point> getBlankSlots();
    public boolean placeMove(Point point, int player, boolean backtracking);
    public Point miniMax(int whosTurn);
    }
