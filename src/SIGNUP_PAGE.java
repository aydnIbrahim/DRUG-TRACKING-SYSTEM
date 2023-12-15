import javax.swing.*;
import java.awt.*;

public class SIGNUP_PAGE {

    private JFrame frame;
    private JLabel titleLabel;
    private JLabel companyNameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JTextField companyNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton signupButton;

    public SIGNUP_PAGE() {

        frame = new JFrame();

        titleLabel = new JLabel("SIGN UP FORM");
        titleLabel.setFont(new Font("Pt Mono", Font.BOLD, 30));
        titleLabel.setForeground(new Color(202, 204, 220));
        titleLabel.setBounds(260, 30, 300, 50);

        companyNameLabel = new JLabel("Company Name");
        companyNameLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        companyNameLabel.setForeground(new Color(37, 153, 252));
        companyNameLabel.setBounds(252, 70, 200, 30);

        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        usernameLabel.setForeground(new Color(37, 153, 252));
        usernameLabel.setBounds(252, 100, 200, 30);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(37, 153, 252));
        passwordLabel.setBounds(252, 130, 200, 30);

        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        confirmPasswordLabel.setForeground(new Color(37, 153, 252));
        confirmPasswordLabel.setBounds(252, 160, 200, 30);

        companyNameField = new JTextField();
        companyNameField.setBounds(390, 170, 100, 30);

        usernameField = new JTextField();


        frame.add(titleLabel);
        frame.add(companyNameLabel);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(companyNameField);

        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setTitle("Drug Tracking System");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }
}
