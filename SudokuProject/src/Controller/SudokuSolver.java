package Controller;

public class SudokuSolver {
    public static final int size = 6;

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
                if (board[row][col] == 0){
                    for (int numberToTry = 1; numberToTry <= size; numberToTry++) {
                        if (isValidPlace(board,numberToTry,row,col)){
                            board[row][col] = numberToTry;
                            if (solveBoard(board)){
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

}
