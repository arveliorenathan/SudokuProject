package View;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    public Menu(){
        JPanel panel = new JPanel();
        setSize(400,370);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);

        panel.setLayout(null);
        panel.setBackground(Color.black);

        JLabel menu = new JLabel("WELCOME TO SUDOKU");
        menu.setBounds(130, 10, 150, 30);
        menu.setForeground(Color.yellow);
        panel.add(menu);

        JButton start = new JButton("START GAME");
        start.setBounds(100, 40, 200, 30);
        panel.add(start);

        JButton leaderboard = new JButton("HIGH SCORE");
        leaderboard.setBounds(100, 80, 200, 30);
        panel.add(leaderboard);

        JButton logout = new JButton("LOG OUT");
        logout.setBounds(100, 120, 200, 30);
        panel.add(logout);

        setVisible(true);

        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
                new Window();
            }
        });

        leaderboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
                new Highscore();
            }
        });


        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
                Login login = new Login();
            }
        });
    }


     public static void main(String[] args) {
        new Menu();
    }
}
