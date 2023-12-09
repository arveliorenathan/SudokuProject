package View;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    JFrame frame = new JFrame();
    public Menu(){
        JPanel panel = new JPanel();
        frame.setSize(400,370);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel menu = new JLabel("MENU GAME");
        menu.setBounds(100, 10, 100, 30);
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

        frame.setVisible(true);

        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });

        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });
    }

    private void StartActionPerformed(java.awt.event.ActionEvent evt){
        frame.dispose();
        Window window = new Window();
    }

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt){
        frame.dispose();
        Login login = new Login();
    }

     public static void main(String[] args) {
        new Menu();
    }
}
