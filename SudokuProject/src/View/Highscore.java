package View;

import Controller.Database;

import javax.swing.*;
import java.sql.ResultSet;

public class Highscore extends JFrame {
    public Highscore(){

        JPanel panel = new JPanel();
        setSize(400,370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(panel);
        panel.setLayout(null);

        JLabel yourProfile = new JLabel("LEADERBOARD");
        yourProfile.setBounds(150, 20, 160, 20);
        panel.add(yourProfile);

        JLabel rankLabel = new JLabel("RANK");
        rankLabel.setBounds(70, 60, 70, 20);
        panel.add(rankLabel);

        JLabel usernameLabel = new JLabel("USERNAME");
        usernameLabel.setBounds(155, 60, 70, 20);
        panel.add(usernameLabel);

        JLabel timLabel = new JLabel("TIME");
        timLabel.setBounds(280, 60, 70, 20);
        panel.add(timLabel);

        JButton back = new JButton("BACK");
        back.setBounds(150, 230, 70, 20);
        panel.add(back);

        setVisible(true);
        panel.setVisible(true);

        try {
            Database database = new Database();
            ResultSet resultSet = database.getLeaderboard();
            while (resultSet.next()){
                String username = resultSet.getString("username");
                int minute = resultSet.getInt("menit");
                int second = resultSet.getInt("detik");

                JLabel rank = new JLabel();
                rank.setText(String.valueOf(resultSet.getRow()));
                rank.setBounds(20, 60 + (resultSet.getRow() *25), 70, 20);
                panel.add(rank);

                JLabel nameLalbel = new JLabel();
                nameLalbel.setText(username);
                nameLalbel.setBounds(105, 60 + (resultSet.getRow() *25), 90, 20);
                panel.add(nameLalbel);

                JLabel timeLabel = new JLabel();
                timeLabel.setText(minute + ":" + second);
                timeLabel.setBounds(230, 60 + (resultSet.getRow() *25), 90, 20);
                panel.add(timeLabel);
            }

        }catch(Exception e){
            System.out.println(e);
        }

        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
                new Menu();
            }
        });
    }

    public static void main(String[] args) {
        new Highscore();
    }
}
