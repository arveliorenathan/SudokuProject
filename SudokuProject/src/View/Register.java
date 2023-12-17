package View;

import Controller.Database;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Register extends Cleaner{
    JTextField username;
    JPasswordField password;
    JPasswordField confirmPassword;
    JFrame frame = new JFrame();
    Database database = new Database();
    public Register(){
        JPanel panel = new JPanel();
        frame.setSize(400, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel registerLabel = new JLabel("Register");
        registerLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        registerLabel.setForeground(Color.BLUE);
        registerLabel.setBounds(50, 10, 80, 25);
        panel.add(registerLabel);

        JLabel welcomeLabel = new JLabel("Please Enter Your Details");
        welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        welcomeLabel.setBounds(50, 35, 150, 25);
        panel.add(welcomeLabel);

        JLabel userLabel = new JLabel("Username : ");
        userLabel.setBounds(50, 60, 80, 25);
        panel.add(userLabel);

        username = new JTextField(20);
        username.setBounds(50, 90, 250, 25);
        panel.add(username);

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setBounds(50, 110, 80, 25);
        panel.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(50, 140, 250, 25);
        panel.add(password);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password : ");
        confirmPasswordLabel.setBounds(50, 160, 120, 25);
        panel.add(confirmPasswordLabel);

        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(50, 190, 250, 25);
        panel.add(confirmPassword);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(50, 235, 250, 25);
        registerBtn.setBackground(Color.BLUE);
        registerBtn.setForeground(Color.WHITE);
        panel.add(registerBtn);

        JLabel loginLabel = new JLabel("Already have an account ?");
        loginLabel.setBounds(50, 270, 150, 25);
        loginLabel.setForeground(Color.GRAY);
        panel.add(loginLabel);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(210, 270, 90, 25);
        loginBtn.setForeground(Color.BLUE);
        panel.add(loginBtn);

        frame.setVisible(true);

        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String usernameText = username.getText();
                String passwordText = new String(password.getPassword());
                String confirmPasswordText = new String(confirmPassword.getPassword());
                if (passwordText.equals(confirmPasswordText)){
                    if (!usernameText.isEmpty() && !passwordText.isEmpty()){
                        try {
                            if (Database.register(usernameText, passwordText) == true) {
                                frame.dispose();
                                new Login();
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please Input Username and Password", "Register Failed", JOptionPane.ERROR_MESSAGE);
                        cleanForm();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Password Do Not Match", "Register Failed", JOptionPane.ERROR_MESSAGE);
                    cleanForm();
                }
            }
        });

        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });
    }

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        frame.dispose();
        Login login = new Login();
    }

    public void cleanForm(){
        username.setText("");
        password.setText("");
        confirmPassword.setText("");
    }

}
