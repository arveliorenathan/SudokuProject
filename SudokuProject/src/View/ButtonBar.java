package View;

import Controller.SudokuSolver;
import Controller.Timer;
import javax.swing.*;
import java.awt.*;

public class ButtonBar extends JPanel {

    SudokuSolver solver = new SudokuSolver();
    GameBoard board;

    ButtonBar(GameBoard board) {
        this.board = board;
        generate();
    }

    private void generate() {
        JButton reset = new JButton("Reset");
        add(reset);
        JButton solve = new JButton("Show Answer");
        add(solve);
        JButton exit = new JButton("Exit");
        add(exit);
        add(board.timer.getLabel());

        solve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solver.solveBoard(board.temp);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        for (int i = 0; i < board.size; i++) {
                            for (int j = 0; j < board.size; j++) {
                                String text = Integer.toString(board.temp[i][j]);
                                board.cell[i][j].setText(text);
                                board.cell[i][j].setEditable(false);
                            }
                        }
                    }
                });
                board.timer.stopTimer();
                board.timer.reset();
                board.timerCheck = 0;
            }
        });
        
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Clear the board
                for (int i = 0; i < GameBoard.size; i++) {
                    for (int j = 0; j < GameBoard.size; j++) {
                        board.cell[i][j].setText("");
                        board.temp[i][j] = 0;
                        board.cell[i][j].setEditable(true);
                    }
                }
        
                // Generate a new random Sudoku puzzle
                board.randomSudoku();
                board.timer.stopTimer();
                board.timer.reset();
                board.timerCheck = 0;
        
                // Redraw the board
                board.repaint();
            }
        });


        
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
    }

    private void SolveActionPerformed(java.awt.event.ActionEvent evt) {
        SudokuSolver solve = new SudokuSolver();

    }

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {
        Menu menu = new Menu();
    }

}