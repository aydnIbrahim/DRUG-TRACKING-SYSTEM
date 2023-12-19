import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class QUERY_PAGE {

    private final JFrame frame;
    private final JTextField barcodeField;

    public QUERY_PAGE() throws IOException {

        frame = new JFrame();

        BufferedImage image = ImageIO.read(new File("Resources/queryPageImage.png"));

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(50, 50, 300, 300);

        JLabel barcodeLabel = new JLabel("Barcode");
        barcodeLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        barcodeLabel.setForeground(new Color(37, 153, 252));
        barcodeLabel.setBounds(385, 110, 100, 30);

        barcodeField = new JTextField();
        barcodeField.setBounds(485, 110, 120, 30);
        barcodeField.setBackground(new Color(32, 34, 46));
        barcodeField.setForeground(Color.white);
        barcodeField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        barcodeField.setCaretColor(new Color(37, 153, 252));

        JButton backButton = getBackButton();
        JButton queryButton = getQueryButton();
        JButton removeButton = getRemoveButton();

        frame.add(imageLabel);
        frame.add(barcodeLabel);
        frame.add(barcodeField);
        frame.add(queryButton);
        frame.add(removeButton);
        frame.add(backButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 428);
        frame.setResizable(false);
        frame.setTitle("QUERY");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public JButton getQueryButton(){
        JButton queryButton = new JButton("QUERY");
        queryButton.setFont(new Font("Pt Mono", Font.BOLD, 15));
        queryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        queryButton.setBounds(450, 220, 150, 30);

        queryButton.addActionListener(e -> {
            DTS_DAO dtsDao = new DTS_DAO();
            try {
                boolean s = dtsDao.query(barcodeField.getText());
                if (s)
                    JOptionPane.showMessageDialog(frame, "A record of the searched barcode was found.\n" +
                            dtsDao.getDrugInformation(barcodeField.getText()),
                            "RECORD FOUND", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame, "A record of the searched barcode was not found.",
                            "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);

            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        return queryButton;
    }

    public JButton getRemoveButton(){
        JButton removeButton = new JButton("DELETE");
        removeButton.setFont(new Font("Pt Mono", Font.BOLD, 15));
        removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeButton.setBounds(450, 260, 150, 30);

        removeButton.addActionListener(e -> {
            DTS_DAO dtsDao = new DTS_DAO();
            try {
                boolean s = dtsDao.remove(barcodeField.getText());
                if (s)
                    JOptionPane.showMessageDialog(frame, "The barcode record has been deleted.",
                            "RECORD REMOVED", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame, "The barcode record was not able to removed.",
                            "RECORD NOT REMOVED", JOptionPane.ERROR_MESSAGE);

            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        return removeButton;
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
                new MAIN_PAGE();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        return backButton;
    }
}
