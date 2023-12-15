import javax.swing.*; // Swing kütüphanesini içe aktarır

// Java GUI arayüzü sınıfı, ilaç takip sistemi ile etkileşimli bir arayüz oluşturur
public class MainPage {

    JFrame frame = new JFrame(); // Ana pencereyi oluşturur
    JLabel image_label = new JLabel( new ImageIcon("/Users/ibrahimaydin/Desktop/Ilac_takip_sistemi/Resources/main.png"));
    JButton kaydet_buton = new JButton("İlaç Kaydet"); // İlaç kaydetmek için bir buton oluşturur
    JButton sorgula_buton = new JButton("İlaç Sorgula"); // İlaç sorgulamak için bir buton oluşturur

    public MainPage() {

        image_label.setBounds(225, 50, 250, 260);

        kaydet_buton.setBounds(100, 360, 200, 40); // Kaydet butonunun konumunu ve boyutunu ayarlar
        sorgula_buton.setBounds(400, 360, 200, 40); // Sorgula butonunun konumunu ve boyutunu ayarlar

        kaydet_buton.setFocusable(false); // Kaydet butonunun odaklanabilirliğini kapatır
        sorgula_buton.setFocusable(false); // Sorgula butonunun odaklanabilirliğini kapatır

        // Kaydet butonuna tıklandığında çalışacak kodu tanımlar
        kaydet_buton.addActionListener(e -> {
            new Window_Kaydet(); // Yeni bir Window_Kaydet nesnesi oluşturur
        });
        // Sorgula butonuna tıklandığında çalışacak kodu tanımlar
        sorgula_buton.addActionListener(e -> {
            new Window_Sorgula(); // Yeni bir Window_Sorgula nesnesi oluşturur
        });

        frame.add(kaydet_buton); // Kaydet butonunu ana pencereye ekler
        frame.add(sorgula_buton); // Sorgula butonunu ana pencereye ekler
        frame.add(image_label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ana pencerenin kapatılması durumunda programın sonlanmasını sağlar
        frame.setSize(700, 500); // Ana pencerenin boyutunu ayarlar
        frame.setResizable(false);
        frame.setTitle("İlaç Takip Sistemi"); // Ana pencerenin başlığını ayarlar
        frame.setLocationRelativeTo(null); // Ana pencerenin ortalanmasını sağlar
        frame.setLayout(null); // Ana pencerenin düzenleyicisini kapatır
        frame.setVisible(true); // Ana pencerenin görünür olmasını sağlar

    }
}
