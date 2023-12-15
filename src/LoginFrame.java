// LoginFrame.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {

    // Tanımladığımız değişkenler
    private JLabel label1, label2, label3;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1, button2;

    // LoginFrame sınıfının yapıcısı
    public LoginFrame() {
        // Pencere başlığını ayarlıyoruz
        super("Login Page");

        // Pencere boyutunu ve konumunu ayarlıyoruz
        setSize(300, 200);
        setLocation(400, 200);

        // Pencereyi kapatma işlemini ayarlıyoruz
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pencereye bir panel ekliyoruz
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        add(panel);

        // Panel üzerine etiket, metin alanı, şifre alanı ve düğme ekliyoruz
        label1 = new JLabel("Username:");
        panel.add(label1);

        textField1 = new JTextField(10);
        panel.add(textField1);

        label2 = new JLabel("Password:");
        panel.add(label2);

        passwordField1 = new JPasswordField(10);
        panel.add(passwordField1);

        button1 = new JButton("Login");
        button1.addActionListener(this); // Düğmeye tıklama olayını ekliyoruz
        panel.add(button1);

        button2 = new JButton("Reset");
        button2.addActionListener(this); // Düğmeye tıklama olayını ekliyoruz
        panel.add(button2);

        // Pencereyi görünür yapıyoruz
        setVisible(true);
    }

    // actionPerformed() metodunu geçersiz kılıyoruz
    @Override
    public void actionPerformed(ActionEvent e) {
        // Hangi düğmeye tıkladığımızı kontrol ediyoruz
        if (e.getSource() == button1) {
            // Kullanıcı adı ve şifreyi alıyoruz
            String username = textField1.getText();
            String password = new String(passwordField1.getPassword());

            // Kullanıcı adı ve şifreyi doğruluyoruz
            if (username.equals("admin") && password.equals("1234")) {
                // Doğruysa, başarılı mesajı gösteriyoruz
                label3 = new JLabel("Login successful!");
                label3.setForeground(Color.GREEN);
                add(label3, BorderLayout.SOUTH);
            } else {
                // Yanlışsa, hata mesajı gösteriyoruz
                label3 = new JLabel("Invalid username or password!");
                label3.setForeground(Color.RED);
                add(label3, BorderLayout.SOUTH);
            }
            // Pencereyi yeniden boyutlandırıyoruz
            setSize(300, 220);
        } else if (e.getSource() == button2) {
            // Sıfırla düğmesine tıklarsak, metin alanlarını temizliyoruz
            textField1.setText("");
            passwordField1.setText("");
        }
    }

    // Ana metot
    public static void main(String[] args) {
        // LoginFrame nesnesi oluşturuyoruz
        LoginFrame frame = new LoginFrame();
    }
}
