package org.example;

public class Game {
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;

    private int xWins = 0;
    private int oWins = 0;
    private int draws = 0;

    public Game() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    public boolean makeMove(int row, int col) {
        if (!gameOver && board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                    board[i][1] == currentPlayer &&
                    board[i][2] == currentPlayer) return true;

            if (board[0][i] == currentPlayer &&
                    board[1][i] == currentPlayer &&
                    board[2][i] == currentPlayer) return true;
        }

        return (board[0][0] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer &&
                        board[1][1] == currentPlayer &&
                        board[2][0] == currentPlayer);
    }

    public boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') return false;
        return !checkWin();
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void setGameOver(boolean over) {
        gameOver = over;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void updateStats(String winner) {
        if ("X".equals(winner)) {
            xWins++;
        } else if ("O".equals(winner)) {
            oWins++;
        } else if ("Draw".equals(winner)) {
            draws++;
        }
    }

    public int getXWins() {
        return xWins;
    }

    public int getOWins() {
        return oWins;
    }

    public int getDraws() {
        return draws;
    }

    public void resetBoard() {
        initializeBoard();
        currentPlayer = 'X';
        gameOver = false;
    }

    public void resetStats() {
        xWins = 0;
        oWins = 0;
        draws = 0;
    }
}
