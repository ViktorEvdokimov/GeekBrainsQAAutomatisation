package HW4;

class AI {
    int bestRow;
    int bestCol;
    int streak;
    boolean hasNotWinTern = true;

    public AI() {
        this.bestRow = 0;
        this.bestCol = 0;
        streak = -1;
    }

    void newMove() {
        this.bestRow = 0;
        this.bestCol = 0;
        this.streak = -1;
        this.hasNotWinTern = true;
    }

    void setMove(int row, int col, int streak) {
        this.bestRow = row;
        this.bestCol = col;
        this.streak = streak;
    }

    void setWinTern(int row, int col) {
        this.bestRow = row;
        this.bestCol = col;
        this.hasNotWinTern = false;
    }

    void setBestMoveIfItBetter(int streak, int row, int col) {
        if (streak > this.streak && TicTacToe.map[row][col] == TicTacToe.DOT_EMPTY && hasNotWinTern) {
            setMove(row, col, streak);
        }
    }

    void aiTurn() {
        newMove();
        int diagonalsCount = (TicTacToe.SIZE - TicTacToe.WIN_STREAK) * 2 + 1;
        int[][] mainDiagonalStreak = new int[diagonalsCount][2];
        int[][] sideDiagonalStreak = new int[diagonalsCount][2];
        for (int i = 0; i < TicTacToe.SIZE; i++) {
            int[] rowStreak = new int[2];
            int[] colStreak = new int[2];
            for (int j = 0; j < TicTacToe.SIZE; j++) {
                rowStreak = checkStreak(i, j, rowStreak);
                colStreak = checkStreak(j, i, colStreak);
            }
            for (int k = 0; k < diagonalsCount; k++) {
                int diagonalOffset = (TicTacToe.SIZE - TicTacToe.WIN_STREAK) - k;
                if ((i + diagonalOffset) < TicTacToe.SIZE && (i + diagonalOffset) >= 0) {
                    mainDiagonalStreak[k] = checkStreak(i, i + diagonalOffset, mainDiagonalStreak[k]);
                    sideDiagonalStreak[k] = checkStreak(TicTacToe.SIZE - i - 1, i + diagonalOffset, sideDiagonalStreak[k]);
                }
            }
        }
        for (int i = TicTacToe.SIZE - 1; i >= 0; i--) {
            int[] rowStreak = new int[2];
            int[] colStreak = new int[2];
            for (int j = TicTacToe.SIZE - 1; j >= 0; j--) {
                rowStreak = checkStreak(i, j, rowStreak);
                colStreak = checkStreak(j, i, colStreak);
            }
            for (int k = 0; k < diagonalsCount; k++) {
                int diagonalOffset = (TicTacToe.SIZE - TicTacToe.WIN_STREAK) - k;
                if ((i + diagonalOffset) < TicTacToe.SIZE && (i + diagonalOffset) >= 0) {
                    mainDiagonalStreak[k] = checkStreak(i, i + diagonalOffset, mainDiagonalStreak[k]);
                    sideDiagonalStreak[k] = checkStreak(TicTacToe.SIZE - i - 1, i + diagonalOffset, sideDiagonalStreak[k]);
                }
            }
        }
        TicTacToe.map[bestRow][bestCol] = TicTacToe.DOT_O;
    }

    private int[] checkStreak(int row, int col, int[] streak) {
        if (TicTacToe.map[row][col] == TicTacToe.DOT_X) streak[0]++;
        else {
            setBestMoveIfItBetter(streak[0], row, col);
            streak[0] = 0;
        }
        if (TicTacToe.map[row][col] == TicTacToe.DOT_O) streak[1]++;
        else {
            if (streak[1] == (TicTacToe.SIZE - 1) && TicTacToe.map[row][col] == TicTacToe.DOT_EMPTY) setWinTern(row, col);
            streak[1] = 0;
        }
        return streak;
    }
}
