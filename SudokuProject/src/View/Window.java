package View;

import javax.swing.*;
import java.awt.*;

import static Controller.SudokuSolver.*;

public class Window extends JFrame {
    private  GameBoard board;
    private ButtonBar button;
    public Window(){
        board = new GameBoard(); 
        button = new ButtonBar(board);
        generateSudoku();
        solvingBegin(board.temp);
    }

    public void generateSudoku(){
        add(board, BorderLayout.CENTER);
        add(button, BorderLayout.PAGE_END);
        setTitle("Sudoku Project");
        setSize(450,550);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Window test = new Window();
    }
}
