package View;

import Controller.SudokuSolver;
import Controller.Timer;
import javax.swing.*;
import java.awt.*;

public class ButtonBar extends JPanel {

    SudokuSolver solver = new SudokuSolver();
    GameBoard board;
    JFrame parentFrame;

    ButtonBar(GameBoard board, JFrame parentFrame) {

        this.parentFrame=parentFrame;

        System.out.println(parentFrame);
        this.board = board;
        generate();
    }

    private void generate() {
        JButton reset = new JButton("Reset");
        reset.setBackground(Color.yellow);
        reset.setForeground(Color.black);
        reset.setFont(new Font("Poppin", Font.BOLD, 14));
        add(reset);
        JButton solve = new JButton("Show Answer");
        solve.setBackground(Color.yellow);
        solve.setForeground(Color.black);
        solve.setFont(new Font("Poppin", Font.BOLD, 14));
        add(solve);
        JButton exit = new JButton("Exit");
        exit.setBackground(Color.yellow);
        exit.setForeground(Color.black);
        exit.setFont(new Font("Poppin", Font.BOLD, 14));
        add(exit);
        add(board.timer.getLabel());
        setBackground(Color.black);

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
                System.out.println(Timer.getMinutesPart() + " Menit " + Timer.getSeconds() + " Detik");
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
                board.timer.reset();
                board.timerCheck = 0;
                board.timer.start();
        
                // Redraw the board
                board.repaint();
            }
        });

        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                board.timer.stopTimer();
                board.timer.reset();
                if(parentFrame!=null){
                    parentFrame.dispose();
                }
                new Menu();
            }
        });
    }
}