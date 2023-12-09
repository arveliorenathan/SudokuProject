package View;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import javax.swing.border.Border;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import Controller.Timer;
import java.awt.event.MouseEvent;

public class GameBoard extends JPanel {
    public static final int size = 6;
    JTextField[][] cell;
    int[][] temp = new int[size][size];
    int timerCheck = 0;
    Timer timer = new Timer();

    GameBoard() {
        generate();
    }

    public void generate() {
        setLayout(new GridLayout(size, size));
        cell = new JTextField[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cell[i][j] = new JTextField();
                cell[i][j].setHorizontalAlignment(JTextField.CENTER);
                add(cell[i][j]);
                cell[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        JTextField source = (JTextField) e.getSource();
                        System.out.println("dggd berfpr " + timerCheck);
                        if (timerCheck == 0) {
                            timer.start();
                            timerCheck = 1;
                            
                        }
                        System.out.println("dggd afte " + timerCheck);
                        for (int row = 0; row < size; row++) {
                            for (int col = 0; col < size; col++) {
                                if (cell[row][col] == source) {
                                    System.out.println(
                                            "User input at row " + row + ", column " + col + ": " + source.getText());
                                    cell[row][col].setText(source.getText());
                                    temp[row][col] = Integer.parseInt(source.getText());
                                    for (int i = 0; i < size; i++) {
                                        for (int j = 0; j < size; j++) {
                                            if (temp[i][j] == 0) {
                                                System.out.print("0");
                                            } else {

                                                // System.out.print(cell[i][j].getText());
                                                System.out.print(temp[i][j]);
                                            }
                                        }
                                        System.out.println();
                                    }
                                    // temp[row][col] = Integer.parseInt(source.getText());
                                    return;
                                }
                            }
                        }
                    }
                });
                if ((i + 1) % 3 == 0 && (j + 1) % 3 == 0) {
                    Border border = BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK);
                    cell[i][j].setBorder(border);
                } else if ((i + 1) % 3 == 0) {
                    Border bottom = BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK);
                    cell[i][j].setBorder(bottom);
                } else if ((j + 1) % 3 == 0) {
                    Border border = BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK);
                    cell[i][j].setBorder(border);
                } else {
                    Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
                    cell[i][j].setBorder(border);
                }

            }

        }
        randomSudoku();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (temp[i][j] == 0) {
                    System.out.print("0");
                } else {
                    System.out.print(temp[i][j]);
                }
            }
            System.out.println();
        }
    }

    public void randomSudoku() {
        Random random = new Random();
        Set<Integer> usedValues = new HashSet<>();
        for (int i = 0; i < size; i++) {
            int randomRow, randomCol, randomValue;

            do {
                randomRow = random.nextInt(size);
                randomCol = random.nextInt(size);
                randomValue = random.nextInt(6) + 1;

            } while (!isValid(usedValues, randomValue));

            usedValues.add(randomValue);

            cell[randomRow][randomCol].setText(String.valueOf(randomValue));
            temp[randomRow][randomCol] = randomValue;
            cell[randomRow][randomCol].setEditable(false);
        }
    }

    private boolean isValid(Set<Integer> usedValues, int value) {
        return !usedValues.contains(value);
    }
}