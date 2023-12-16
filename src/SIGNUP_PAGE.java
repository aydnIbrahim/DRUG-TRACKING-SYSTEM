import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Arrays;

public class SIGNUP_PAGE {

    private final JFrame frame;
    private final JTextField companyNameField;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JPasswordField confirmPasswordField;

    public SIGNUP_PAGE() {

        frame = new JFrame();

        JLabel titleLabel = new JLabel("SIGN UP FORM");
        titleLabel.setFont(new Font("Pt Mono", Font.BOLD, 30));
        titleLabel.setForeground(new Color(202, 204, 220));
        titleLabel.setBounds(243, 30, 300, 50);

        JLabel companyNameLabel = new JLabel("Company Name");
        companyNameLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        companyNameLabel.setForeground(new Color(37, 153, 252));
        companyNameLabel.setBounds(200, 115, 200, 30);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        usernameLabel.setForeground(new Color(37, 153, 252));
        usernameLabel.setBounds(200, 145, 200, 30);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(37, 153, 252));
        passwordLabel.setBounds(200, 175, 200, 30);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        confirmPasswordLabel.setForeground(new Color(37, 153, 252));
        confirmPasswordLabel.setBounds(200, 205, 200, 30);

        companyNameField = new JTextField();
        companyNameField.setBounds(375, 115, 100, 30);

        usernameField = new JTextField();
        usernameField.setBounds(375, 145, 100, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(375, 175, 100, 30);
        passwordField.setEchoChar('*');

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(375, 205, 100, 30);
        confirmPasswordField.setEchoChar('*');

        JButton signupButton = getButton();
        JButton login_button = getjButton();

        frame.add(titleLabel);
        frame.add(companyNameLabel);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(companyNameField);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(signupButton);
        frame.add(login_button);


        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setTitle("Drug Tracking System");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private JButton getButton() {
        JButton signupButton = new JButton("SIGN UP");
        signupButton.setFont(new Font("Pt Mono", Font.BOLD, 15));
        signupButton.setBounds(307, 270, 100, 30);

        signupButton.addActionListener(e -> {
            char[] password = passwordField.getPassword ();
            char[] confirmPassword = confirmPasswordField.getPassword();
            if (companyNameField.getText().isEmpty() || usernameField.getText().isEmpty() || password.length == 0 || confirmPassword.length == 0){
                JOptionPane.showMessageDialog(frame, "Check Sign Up Information", "Empty Inputs", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if (Arrays.equals(password, confirmPassword)){
                    DTS_DAO itsDao = new DTS_DAO();
                    try {
                        boolean r = itsDao.save(companyNameField.getText(), usernameField.getText(), password);
                        if (r){
                            new LOGIN_PAGE();
                            frame.dispose();
                            JOptionPane.showMessageDialog(frame, "Registration Successful", "Registration", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(frame, "Registration Failed", "Registration", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Check Password", "Password Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return signupButton;
    }

    private JButton getjButton(){
        JButton signup_button = new JButton("ALREADY HAVE AN ACCOUNT SIGN IN");
        signup_button.setFont(new Font("Pt Mono", Font.BOLD, 8));
        signup_button.setBounds(257, 320, 200, 30);

        signup_button.addActionListener(e -> {
            new LOGIN_PAGE();
            frame.dispose();
        });
        return signup_button;
    }
}
