package Windows;

import Database.DTS_DAO;

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
    JButton eyeButtonPassword = getEyeButtonPassword();
    JButton eyeSlashButtonPassword = getEyeSlashButtonPassword();
    JButton eyeButtonConfirmPassword = getEyeButtonConfirmPassword();
    JButton eyeSlashButtonConfirmPassword = getEyeSlashButtonConfirmPassword();

    BufferedImage eye;
    BufferedImage eyeSlash = ImageIO.read(new File("Resources/eye.slash.png"));

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
        companyNameField.setBounds(375, 115, 175, 30);
        companyNameField.setBackground(new Color(32, 34, 46));
        companyNameField.setForeground(Color.white);
        companyNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        companyNameField.setCaretColor(new Color(37, 153, 252));

        emailField = new JTextField();
        emailField.setBounds(375, 145, 175, 30);
        emailField.setBackground(new Color(32, 34, 46));
        emailField.setForeground(Color.white);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        emailField.setCaretColor(new Color(37, 153, 252));

        passwordField = new JPasswordField();
        passwordField.setBounds(375, 175, 175, 30);
        passwordField.setEchoChar('*');
        passwordField.setBackground(new Color(32, 34, 46));
        passwordField.setForeground(Color.white);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        passwordField.setCaretColor(new Color(37, 153, 252));

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(375, 205, 175, 30);
        confirmPasswordField.setEchoChar('*');
        confirmPasswordField.setBackground(new Color(32, 34, 46));
        confirmPasswordField.setForeground(Color.white);
        confirmPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        confirmPasswordField.setCaretColor(new Color(37, 153, 252));

        JButton backButton = getBackButton();

        JButton signupButton = getSignupButton();
        JButton login_button = getSignInButton();

        frame.add(eyeButtonPassword);
        frame.add(eyeSlashButtonPassword);
        frame.add(eyeButtonConfirmPassword);
        frame.add(eyeSlashButtonConfirmPassword);
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
        frame.setTitle("Drug.Drug Tracking System");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private JButton getSignupButton() {
        JButton signupButton = new JButton("SIGN UP");
        signupButton.setFont(new Font("Pt Mono", Font.BOLD, 15));
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
        JButton signingButton = new JButton("ALREADY HAVE AN ACCOUNT? SIGN IN");
        signingButton.setFont(new Font("Pt Mono", Font.BOLD, 10));
        signingButton.setForeground(Color.white);
        signingButton.setBorderPainted(false);
        signingButton.setFocusable(false);
        signingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signingButton.setBounds(235, 320, 250, 30);

        signingButton.addActionListener(e -> {
            try {
                new LOGIN_PAGE();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            frame.dispose();
        });
        return signingButton;
    }

    public JButton getBackButton() throws IOException {
        BufferedImage imageBack = ImageIO.read(new File("Resources/arrowshape.backward.png"));
        JButton backButton = new JButton(new ImageIcon(imageBack));
        backButton.setBackground(new Color(32, 34,46));
        backButton.setFocusable(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

    private JButton getEyeButtonPassword() throws IOException {
        eye = ImageIO.read(new File("Resources/eye.png"));
        JButton eyeButton = new JButton(new ImageIcon(eye));
        eyeButton.setBorder(BorderFactory.createEmptyBorder());
        eyeButton.setContentAreaFilled(false);
        eyeButton.setBackground(new Color(255, 255, 255));
        eyeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        eyeButton.setBounds(513, 170, 40, 40);

        eyeButton.addActionListener(e -> {
            passwordField.setEchoChar((char)0);
            eyeButton.setVisible(false);
            eyeSlashButtonPassword.setVisible(true);
        });

        return eyeButton;
    }

    private JButton getEyeSlashButtonPassword() throws IOException {
        eyeSlash = ImageIO.read(new File("Resources/eye.slash.png"));
        JButton eyeSlashButton = new JButton(new ImageIcon(eyeSlash));
        eyeSlashButton.setBorder(BorderFactory.createEmptyBorder());
        eyeSlashButton.setContentAreaFilled(false);
        eyeSlashButton.setBackground(new Color(255, 255, 255));
        eyeSlashButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        eyeSlashButton.setBounds(513, 170, 40, 40);

        eyeSlashButton.setVisible(false);

        eyeSlashButton.addActionListener(e -> {
            passwordField.setEchoChar('*');
            eyeSlashButton.setVisible(false);
            eyeButtonPassword.setVisible(true);
        });

        return eyeSlashButton;
    }

    private JButton getEyeButtonConfirmPassword() throws IOException {
        eye = ImageIO.read(new File("Resources/eye.png"));
        JButton eyeButton = new JButton(new ImageIcon(eye));
        eyeButton.setBorder(BorderFactory.createEmptyBorder());
        eyeButton.setContentAreaFilled(false);
        eyeButton.setBackground(new Color(255, 255, 255));
        eyeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        eyeButton.setBounds(513, 200, 40, 40);

        eyeButton.addActionListener(e -> {
            confirmPasswordField.setEchoChar((char)0);
            eyeButton.setVisible(false);
            eyeSlashButtonConfirmPassword.setVisible(true);
        });

        return eyeButton;
    }

    private JButton getEyeSlashButtonConfirmPassword() throws IOException {
        eyeSlash = ImageIO.read(new File("Resources/eye.slash.png"));
        JButton eyeSlashButton = new JButton(new ImageIcon(eyeSlash));
        eyeSlashButton.setBorder(BorderFactory.createEmptyBorder());
        eyeSlashButton.setContentAreaFilled(false);
        eyeSlashButton.setBackground(new Color(255, 255, 255));
        eyeSlashButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        eyeSlashButton.setBounds(513, 200, 40, 40);

        eyeSlashButton.setVisible(false);

        eyeSlashButton.addActionListener(e -> {
            confirmPasswordField.setEchoChar('*');
            eyeSlashButton.setVisible(false);
            eyeButtonConfirmPassword.setVisible(true);
        });

        return eyeSlashButton;
    }
}
