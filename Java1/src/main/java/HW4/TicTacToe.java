package HW4;

import HW4.AI;

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

    private static Scanner scanner;
    static char[][] map;
    private static Random random;

    private static AI aI = new AI();


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        random = new Random();

        initMap();
        printMap();
        startGameLoop();
    }

    private static void startGameLoop() {
        while (true) {
            humanTurn();
            printMap();
            if (checkEndGame(DOT_X)) {
                break;
            }

            aI.aiTurn();
            printMap();
            if (checkEndGame(DOT_O)) {
                break;
            }
        }
    }

    private static boolean checkEndGame(char symbol) {
        if (isMapFull()) {
            System.out.println(DRAW_MSG);
            return true;
        }

        if (isWin(symbol)) {
            System.out.println(getWinMessageBy(symbol));
            return true;
        }

        return false;
    }

    private static String getWinMessageBy(char symbol) {
        return symbol == DOT_X ? HUMAN_WIN_MSG : AI_WIN_MSG;
    }

    private static boolean isWin(char symbol) {
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

    private static boolean isWinSizeAchieved (int ... streaks){
        for (int streak : streaks) {
            if(streak >= WIN_STREAK) return true;
        }
        return false;
    }

    private static int checkStreak(int row, int col, int streak, char symbol) {
        if (map[row][col] == symbol) streak++;
        else streak = 0;
        return streak;
    }

    private static boolean isMapFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (isEmptyCell(row, col)) {
                    return false;
                }
            }
        }

        return true;
    }


    private static void humanTurn() {
        System.out.println("Введите координаты row col: ");
        int row = 0;
        int col = 0;
        do {
            row = readIndex();
            col = readIndex();


            if (!checkRange(row) || !checkRange(col)) {
                System.out.println("координаты должны быть в диапазоне от 1 до " + SIZE);
                continue;
            }

            if (isEmptyCell(row - 1, col - 1)) {
                break;
            } else {
                System.out.println("Клетка уже занята!");
            }
        } while (true);

        map[row - 1][col - 1] = DOT_X;
    }

    private static boolean isEmptyCell(int row, int col) {
        return map[row][col] == DOT_EMPTY;
    }

    private static int readIndex() {
        while (!scanner.hasNextInt()) {
            System.out.println("Координаты должны иметь целочисленное значение!");
            scanner.next();
        }

        return scanner.nextInt();
    }

    private static boolean checkRange(int index) {
        return index >= 1 && index <= SIZE;
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];

        for (int row = 0; row < map.length; row++) {
            Arrays.fill(map[row], DOT_EMPTY);
        }
    }

    private static void printMap() {
        printMapHeader();
        printMapState();
        System.out.println();
    }

    private static void printMapState() {
        for (int row = 0; row < map.length; row++) {
            printRowNumber(row);
            printRow(map[row]);
            System.out.println();
        }
    }

    private static void printRow(char[] chars) {
        for (int col = 0; col < chars.length; col++) {
            System.out.print(chars[col] + " ");
        }
    }

    private static void printRowNumber(int rowNumber) {
        System.out.print((rowNumber + 1) + " ");
    }

    private static void printMapHeader() {
        for (int col = 0; col <= SIZE; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
    }


}


