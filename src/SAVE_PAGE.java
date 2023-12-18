import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class SAVE_PAGE {
    private final JFrame frame;
    private final JTextField emailField;
    private final JTextField barcodeField;
    private final JTextField priceField;


    public SAVE_PAGE() throws IOException {

        frame = new JFrame();

        BufferedImage image = ImageIO.read(new File("Resources/savePageImage.png"));

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(50, 50, 300, 300);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        nameLabel.setForeground(new Color(37, 153, 252));
        nameLabel.setBounds(385, 110, 100, 30);

        JLabel barcodeLabel = new JLabel("Barcode");
        barcodeLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        barcodeLabel.setForeground(new Color(37, 153, 252));
        barcodeLabel.setBounds(385, 140, 100, 30);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        priceLabel.setForeground(new Color(37, 153, 252));
        priceLabel.setBounds(385, 170, 100, 30);

        emailField = new JTextField();
        emailField.setBounds(485, 110, 120, 30);
        emailField.setBackground(new Color(32, 34, 46));
        emailField.setForeground(Color.white);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        emailField.setCaretColor(new Color(37, 153, 252));

        barcodeField = new JTextField();
        barcodeField.setBounds(485, 140, 120, 30);
        barcodeField.setBackground(new Color(32, 34, 46));
        barcodeField.setForeground(Color.white);
        barcodeField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        barcodeField.setCaretColor(new Color(37, 153, 252));

        priceField = new JTextField();
        priceField.setBounds(485, 170, 120, 30);
        priceField.setBackground(new Color(32, 34, 46));
        priceField.setForeground(Color.white);
        priceField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        priceField.setCaretColor(new Color(37, 153, 252));

        JButton backButton = getBackButton();
        JButton saveButton = getButton();

        frame.add(imageLabel);
        frame.add(nameLabel);
        frame.add(barcodeLabel);
        frame.add(priceLabel);
        frame.add(emailField);
        frame.add(barcodeField);
        frame.add(priceField);
        frame.add(saveButton);
        frame.add(backButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 428);
        frame.setResizable(false);
        frame.setTitle("SAVE");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private JButton getButton(){
        JButton saveButton = new JButton("SAVE");
        saveButton.setFont(new Font("Pt Mono", Font.BOLD, 15));
        saveButton.setBounds(450, 250, 150, 30);

        saveButton.addActionListener(e -> {
            DTS_DAO dtsDao = new DTS_DAO();
            String name = emailField.getText();
            String barcode = barcodeField.getText();
            String price = priceField.getText();

            try{
                if (name.isEmpty())
                    JOptionPane.showMessageDialog(frame, "Check the name.", "NAME ERROR", JOptionPane.ERROR_MESSAGE);
                else if (barcode.length() != 13)
                    JOptionPane.showMessageDialog(frame, "Check the barcode.", "BARCODE ERROR", JOptionPane.ERROR_MESSAGE);
                else if (price.isEmpty())
                    JOptionPane.showMessageDialog(frame, "Check the price.", "PRICE ERROR", JOptionPane.ERROR_MESSAGE);
                else if (dtsDao.query(barcode))
                    JOptionPane.showMessageDialog(frame, "The barcode has been added before.", "PRICE ERROR", JOptionPane.ERROR_MESSAGE);
                else{
                    dtsDao.add(name, barcode, price);
                    JOptionPane.showMessageDialog(frame, "The barcode has been added successfully.", "BARCODE ADDED", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }


        });
        return saveButton;
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
                new MAIN_PAGE();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        return backButton;
    }
}

