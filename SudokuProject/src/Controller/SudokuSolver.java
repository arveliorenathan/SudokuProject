package Controller;

import java.util.Arrays;

public class SudokuSolver {
    public static final int size = 6;
    public static int [][] solved = new int[size][size];

    private static boolean isNumberInRow(int[][] board, int number, int row){
        for (int i = 0; i < size; i++) {
            if (board[row][i] == number){
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberInCol(int[][] board, int number, int col){
        for (int i = 0; i < size; i++) {
            if (board[i][col] == number){
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberInBox(int[][] board, int number, int row, int col){
        int localBoxRow = row - row % 2;
        int localBoxCol = col - col % 3;
        for (int i = localBoxRow; i < localBoxRow + 2; i++) {
            for (int j = localBoxCol; j < localBoxCol + 3; j++) {
                if (board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlace(int[][]board, int number, int row, int col){
        return !isNumberInRow(board,number,row) && !isNumberInCol(board,number,col) && !isNumberInBox(board,number,row,col);
    }

    public static boolean solveBoard(int[][]board){
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = solved[row][col];
            }
        }
        return true;
    }

    public static void solvingBegin(int[][] board) {
        System.out.println(Arrays.deepToString(board));
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                solved[row][col] = board[row][col];
            }
        }
        beginSolve(solved);
        System.out.println(Arrays.deepToString(board));
        System.out.println(Arrays.deepToString(solved));

    }

    public static boolean beginSolve(int[][]board){
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == 0){
                    for (int numberToTry = 1; numberToTry <= size; numberToTry++) {
                        if (isValidPlace(board,numberToTry,row,col)){
                            board[row][col] = numberToTry;
                            if (beginSolve(board)){
                                return true;
                            }
                            else {
                                board[row][col] = 0;
                            }
                        }

                    }
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkBoard(int posX, int posY, int numberToCheck) {
        System.out.println(numberToCheck);
        System.out.println(solved[posX][posY]);
        return numberToCheck == solved[posX][posY];
    }

    public static boolean isWin(int[][] board){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != solved[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
