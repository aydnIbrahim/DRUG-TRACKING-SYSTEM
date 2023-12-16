import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

public class SIGNUP_PAGE {

    private final JFrame frame;
    private final JTextField companyNameField;
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JPasswordField confirmPasswordField;

    public SIGNUP_PAGE() throws IOException {

        frame = new JFrame();

        JLabel titleLabel = new JLabel("SIGN UP FORM");
        titleLabel.setFont(new Font("Pt Mono", Font.BOLD, 30));
        titleLabel.setForeground(new Color(202, 204, 220));
        titleLabel.setBounds(243, 30, 300, 50);

        JLabel companyNameLabel = new JLabel("Company Name");
        companyNameLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        companyNameLabel.setForeground(new Color(37, 153, 252));
        companyNameLabel.setBounds(200, 115, 200, 30);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        emailLabel.setForeground(new Color(37, 153, 252));
        emailLabel.setBounds(200, 145, 200, 30);

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

        emailField = new JTextField();
        emailField.setBounds(375, 145, 100, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(375, 175, 100, 30);
        passwordField.setEchoChar('*');

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(375, 205, 100, 30);
        confirmPasswordField.setEchoChar('*');

        JButton backButton = getBackButton();

        JButton signupButton = getSignupButton();
        JButton login_button = getSignInButton();

        frame.add(titleLabel);
        frame.add(companyNameLabel);
        frame.add(emailLabel);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(companyNameField);
        frame.add(emailField);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(signupButton);
        frame.add(login_button);
        frame.add(backButton);

        SwingUtilities.getRootPane(signupButton).setDefaultButton(signupButton);

        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setTitle("Drug Tracking System");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private JButton getSignupButton() {
        JButton signupButton = new JButton("SIGN UP");
        signupButton.setFont(new Font("Pt Mono", Font.BOLD, 15));
        signupButton.setBounds(307, 270, 100, 30);

        signupButton.addActionListener(e -> {
            char[] password = passwordField.getPassword ();
            char[] confirmPassword = confirmPasswordField.getPassword();
            if (companyNameField.getText().isEmpty() || emailField.getText().isEmpty() || password.length == 0 || confirmPassword.length == 0){
                JOptionPane.showMessageDialog(frame, "Check Sign Up Information", "Empty Inputs", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if (Arrays.equals(password, confirmPassword)){
                    DTS_DAO dtsDao = new DTS_DAO();
                    try {
                        boolean r = dtsDao.save(companyNameField.getText(), emailField.getText(), password);
                        if (r){
                            new LOGIN_PAGE();
                            frame.dispose();
                            JOptionPane.showMessageDialog(frame, "Registration Successful", "Registration", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(frame, "Registration Failed", "Registration", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException | ClassNotFoundException | IOException ex) {
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

    private JButton getSignInButton(){
        JButton signup_button = new JButton("ALREADY HAVE AN ACCOUNT? SIGN IN");
        signup_button.setFont(new Font("Pt Mono", Font.BOLD, 10));
        signup_button.setForeground(Color.white);
        signup_button.setBorderPainted(false);
        signup_button.setFocusable(false);
        signup_button.setBounds(235, 320, 250, 30);

        signup_button.addActionListener(e -> {
            try {
                new LOGIN_PAGE();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            frame.dispose();
        });
        return signup_button;
    }

    public JButton getBackButton() throws IOException {
        BufferedImage imageBack = ImageIO.read(new File("Resources/arrowshape.backward.png"));
        JButton backButton = new JButton(new ImageIcon(imageBack));
        backButton.setBackground(new Color(32, 34,46));
        backButton.setFocusable(false);
        backButton.setBorderPainted(false);
        backButton.setBounds(5, 5, 50, 50);

        backButton.addActionListener(e -> {
            frame.dispose();
            try {
                new LOGIN_PAGE();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        return backButton;
    }
}
