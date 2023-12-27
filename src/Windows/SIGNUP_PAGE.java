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

// Kayıt olma sayfası
public class SIGNUP_PAGE {

    private final JFrame frame;
    private final JTextField nameField;
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

        // Sayfa başlığı
        JLabel titleLabel = new JLabel("SIGN UP FORM");
        titleLabel.setFont(new Font("Pt Mono", Font.BOLD, 30));
        titleLabel.setForeground(new Color(202, 204, 220));
        titleLabel.setBounds(243, 30, 300, 50);

        // İsim-Soyisim etiketi
        JLabel nameLabel = new JLabel("Name - Surname");
        nameLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        nameLabel.setForeground(new Color(37, 153, 252));
        nameLabel.setBounds(200, 115, 200, 30);

        // E-Posta etiketi
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        emailLabel.setForeground(new Color(37, 153, 252));
        emailLabel.setBounds(200, 145, 200, 30);

        // Şifre etiketi
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(37, 153, 252));
        passwordLabel.setBounds(200, 175, 200, 30);

        // Şifre doğrulama etiketi
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        confirmPasswordLabel.setForeground(new Color(37, 153, 252));
        confirmPasswordLabel.setBounds(200, 205, 200, 30);

        // İsim-Soyisim giriş alanı
        nameField = new JTextField();
        nameField.setBounds(375, 115, 175, 30);
        nameField.setBackground(new Color(32, 34, 46));
        nameField.setForeground(Color.white);
        nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        nameField.setCaretColor(new Color(37, 153, 252));

        // E-Posta giriş alanı
        emailField = new JTextField();
        emailField.setBounds(375, 145, 175, 30);
        emailField.setBackground(new Color(32, 34, 46));
        emailField.setForeground(Color.white);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        emailField.setCaretColor(new Color(37, 153, 252));

        // Şifre giriş alanı
        passwordField = new JPasswordField();
        passwordField.setBounds(375, 175, 175, 30);
        passwordField.setEchoChar('*');
        passwordField.setBackground(new Color(32, 34, 46));
        passwordField.setForeground(Color.white);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        passwordField.setCaretColor(new Color(37, 153, 252));

        // Şifre doğrulama giriş alanı
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(375, 205, 175, 30);
        confirmPasswordField.setEchoChar('*');
        confirmPasswordField.setBackground(new Color(32, 34, 46));
        confirmPasswordField.setForeground(Color.white);
        confirmPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        confirmPasswordField.setCaretColor(new Color(37, 153, 252));

        // Buton atamaları
        JButton backButton = getBackButton();
        JButton signupButton = getSignupButton();
        JButton login_button = getSignInButton();

        // Bileşenlerin çerçeveye eklenmesi
        frame.add(eyeButtonPassword);
        frame.add(eyeSlashButtonPassword);
        frame.add(eyeButtonConfirmPassword);
        frame.add(eyeSlashButtonConfirmPassword);
        frame.add(titleLabel);
        frame.add(nameLabel);
        frame.add(emailLabel);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(nameField);
        frame.add(emailField);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(signupButton);
        frame.add(login_button);
        frame.add(backButton);

        // Enter tuşu ile ilişkilendirme
        SwingUtilities.getRootPane(signupButton).setDefaultButton(signupButton);

        // Çerçeve ayarları
        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setTitle("Drug Tracking System");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // Kayıt ol butonu
    private JButton getSignupButton() {
        JButton signupButton = new JButton("SIGN UP");
        signupButton.setFont(new Font("Pt Mono", Font.BOLD, 15));
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signupButton.setBounds(307, 270, 100, 30);

        signupButton.addActionListener(e -> {
            char[] password = passwordField.getPassword ();
            char[] confirmPassword = confirmPasswordField.getPassword();
            if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || password.length == 0 || confirmPassword.length == 0){
                JOptionPane.showMessageDialog(frame, "Check Sign Up Information", "Empty Inputs", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if (Arrays.equals(password, confirmPassword)){
                    DTS_DAO dtsDao = new DTS_DAO();
                    try {
                        boolean r = dtsDao.save(nameField.getText(), emailField.getText(), password);
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

    // Hesabı varsa giriş yap butonu
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

    // Bir önceki sayfaya dönme butonu
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

    // Şifre görüntüleme butonu
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

    // Şifre gizleme butonu
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

    // Şifre doğrulama görüntüleme butonu
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

    // Şifre doğrulama gizleme butonu
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
