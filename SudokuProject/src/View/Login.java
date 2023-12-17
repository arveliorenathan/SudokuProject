package View;

import Controller.Database;
import javax.swing.*;
import java.awt.*;

public class Login extends Cleaner{
    JTextField username;
    JTextField password;
    JFrame frame = new JFrame();

    public Login(){
        new Database();
        JPanel panel = new JPanel();
        frame.setSize(400,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setBounds(50, 10, 80, 25);
        panel.add(loginLabel);

        JLabel welcomeLabel = new JLabel("Please Enter Your Details");
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

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(50, 190, 250, 25);
        loginBtn.setBackground(Color.BLUE);
        loginBtn.setForeground(Color.WHITE);
        panel.add(loginBtn);

        JLabel signUpLabel = new JLabel("not have an account ?");
        signUpLabel.setBounds(50, 220, 150, 25);
        signUpLabel.setForeground(Color.GRAY);
        panel.add(signUpLabel);

        JButton signUpBtn = new JButton("Register Here");
        signUpBtn.setForeground(Color.BLUE);
        signUpBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        signUpBtn.setBounds(190, 220, 110, 25);
        panel.add(signUpBtn);

        frame.setVisible(true);

        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
                cleanForm();
            }
        });

        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpBtnActionPerformed(evt);
            }
        });
    }

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt){
        String usernameText = username.getText();
        String passwordText = password.getText();
        if (!usernameText.isEmpty() && !passwordText.isEmpty()){
            if (Database.loginUser(usernameText, passwordText)){
                frame.dispose();
                new Menu();
            }
            else {
                cleanForm();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Username or Password Wrong", "Login Failed", JOptionPane.ERROR_MESSAGE);
            cleanForm();
        }
    }

    public void cleanForm(){
        username.setText("");
        password.setText("");
    }

    private void SignUpBtnActionPerformed(java.awt.event.ActionEvent evt){
        frame.dispose();
        new Register();
    }

}
