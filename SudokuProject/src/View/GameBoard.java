package View;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import javax.swing.border.Border;
import javax.xml.crypto.Data;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Controller.Database;
import Controller.Timer;
import static Controller.SudokuSolver.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameBoard extends JPanel {
    public static final int size = 6;
    JTextField[][] cell;
    int[][] temp = new int[size][size];
    int timerCheck = 0;
    Timer timer = new Timer();

    GameBoard() {
        generate();
        timer.start();
    }

    public void generate() {
        setLayout(new GridLayout(size, size));
        cell = new JTextField[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cell[i][j] = new JTextField();
                cell[i][j].setHorizontalAlignment(JTextField.CENTER);
                cell[i][j].setFont(new Font("Poppin", Font.PLAIN, 16));
                cell[i][j].setForeground(Color.yellow);
                cell[i][j].setBackground(Color.black);
                add(cell[i][j]);
                cell[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        JTextField source = (JTextField) e.getSource();
                        System.out.println("TimeCheck " + timerCheck);
                        if (timerCheck == 0) {
                            timer.start();
                            timerCheck = 1;

                        }
                        for (int row = 0; row < size; row++) {
                            for (int col = 0; col < size; col++) {
                                if (cell[row][col] == source) {
                                    System.out.println("User input at row " + row + ", column " + col + ": " + source.getText());
                                    cell[row][col].setText(source.getText());
                                    temp[row][col] = Integer.parseInt(source.getText());
                                    if(checkBoard(row, col, Integer.parseInt(source.getText()))) {
                                        source.setForeground(Color.green);
                                    }
                                    else {
                                        source.setForeground(Color.red);
                                    }
                                    if (isWin(temp)) {
                                        timer.stopTimer();
                                        try {
                                            popUp();
                                        } catch (SQLException ex) {
                                            throw new RuntimeException(ex);
                                        }

                                    }
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
                                    return;
                                }
                            }
                        }
                    }
                });
                if ((i + 1) % 2 == 0 && (j + 1) % 3 == 0) {
                    Border border = BorderFactory.createMatteBorder(1, 1, 4, 4, Color.WHITE);
                    cell[i][j].setBorder(border);
                } else if ((i + 1) % 2 == 0) {
                    Border bottom = BorderFactory.createMatteBorder(1, 1, 3, 1, Color.WHITE);
                    cell[i][j].setBorder(bottom);
                } else if ((j + 1) % 3 == 0) {
                    Border border = BorderFactory.createMatteBorder(1, 1, 1, 3, Color.WHITE);
                    cell[i][j].setBorder(border);
                } else {
                    Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
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
            cell[randomRow][randomCol].setForeground(Color.yellow);


        }
    }

    private void popUp() throws SQLException {
        Database database = new Database();
        database.updateTime(Timer.getMinutesPart(),Timer.getSeconds());
        JFrame frame = new JFrame("CONGRATULATION");
        JPanel panel = new JPanel();

        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        panel.setBackground(Color.black);

        JLabel win = new JLabel("Congrats You Win");
        win.setBounds(90, 20, 160, 20);
        win.setFont(new Font("Poppin", Font.BOLD, 18));
        win.setForeground(Color.YELLOW);
        panel.add(win);

        JLabel time = new JLabel("Solve : " + Timer.getMinutesPart() + "Minute" + Timer.getSeconds() + "Second");
        time.setBounds(80, 50, 200, 50);
        time.setFont(new Font("Poppin", Font.BOLD, 14));
        time.setForeground(Color.YELLOW);
        panel.add(time);

        JButton quit = new JButton("Quit");
        quit.setBounds(90, 150, 160, 20);
        quit.setBackground(Color.yellow);
        quit.setForeground(Color.BLACK);
        quit.setFont(new Font("Poppin", Font.BOLD, 14));
        panel.add(quit);

        frame.setVisible(true);

        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new Menu();
            }
        });

    }

    private boolean isValid(Set<Integer> usedValues, int value) {
        return !usedValues.contains(value);
    }
}