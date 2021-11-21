package HW8.TicTacToe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static final String AI_WIN_MSG = "Победил компьютер!";
    static final String HUMAN_WIN_MSG = "Победил человек!";
    static final String DRAW_MSG = "Ничья!";

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '•';

    static final int SIZE = 5;
    static final int WIN_STREAK = 4;

    static char[][] map;
    protected static UI ui;

    protected static AI aI = new AI();


    public static void main(String[] args) {
        initMap();
        ui = new UI(map);
    }

    protected static boolean checkEndGame(char symbol) {
        if (isMapFull()) {
            ui.setWinnMessage(DRAW_MSG);
            return true;
        }

        if (isWin(symbol)) {
            ui.setWinnMessage(getWinMessageBy(symbol));
            return true;
        }

        return false;
    }

    protected static String getWinMessageBy(char symbol) {
        return symbol == DOT_X ? HUMAN_WIN_MSG : AI_WIN_MSG;
    }

    protected static boolean isWin(char symbol) {
        int diagonalsCount = (SIZE - WIN_STREAK) * 2 + 1;
        int[] mainDiagonalStreak = new int[diagonalsCount];
        int[] sideDiagonalStreak = new int[diagonalsCount];
        for (int i = 0; i < SIZE; i++) {
            int rowStreak = 0;
            int colStreak = 0;
            for (int j = 0; j < SIZE; j++) {
                rowStreak = checkStreak(i, j, rowStreak, symbol);
                colStreak = checkStreak(j, i, colStreak, symbol);
                if (isWinSizeAchieved(rowStreak, colStreak)) return true;
            }
            if (rowStreak >= WIN_STREAK || colStreak >= WIN_STREAK) return true;
            for (int k = 0; k < diagonalsCount; k++) {
                int diagonalOffset = (SIZE - WIN_STREAK) - k;
                if ((i + diagonalOffset) < SIZE && (i + diagonalOffset) >= 0) {
                    mainDiagonalStreak[k] = checkStreak(i, i + diagonalOffset, mainDiagonalStreak[k], symbol);
                    sideDiagonalStreak[k] = checkStreak(SIZE - i - 1, i + diagonalOffset, sideDiagonalStreak[k], symbol);
                }
                if (isWinSizeAchieved(mainDiagonalStreak[k], sideDiagonalStreak[k])) return true;
            }
        }
        return false;
    }

    protected static boolean isWinSizeAchieved (int ... streaks){
        for (int streak : streaks) {
            if(streak >= WIN_STREAK) return true;
        }
        return false;
    }

    protected static int checkStreak(int row, int col, int streak, char symbol) {
        if (map[row][col] == symbol) streak++;
        else streak = 0;
        return streak;
    }

    protected static boolean isMapFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (isEmptyCell(row, col)) {
                    return false;
                }
            }
        }

        return true;
    }

    protected static void humanTurn(int row, int col) {
        if(map[row][col] == DOT_EMPTY) {
            map[row][col] = DOT_X;
            ui.refreshMap(map);
            if (checkEndGame(DOT_X)) {
                initMap();
            }

            aI.aiTurn();
            if (checkEndGame(DOT_O)) {
                initMap();
            }
            ui.refreshMap(map);
        }
    }

    protected static boolean isEmptyCell(int row, int col) {
        return map[row][col] == DOT_EMPTY;
    }

    protected static void initMap() {
        map = new char[SIZE][SIZE];

        for (int row = 0; row < map.length; row++) {
            Arrays.fill(map[row], DOT_EMPTY);
        }
    }
}


