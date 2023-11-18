import javax.swing.*; // Swing kütüphanesini içe aktarır
import java.awt.event.ActionEvent; // ActionEvent sınıfını içe aktarır
import java.awt.event.ActionListener; // ActionListener arayüzünü içe aktarır

// Java GUI arayüzü sınıfı, ilaç takip sistemi ile etkileşimli bir arayüz oluşturur
public class IlacTakipArayuzu {

    JFrame frame = new JFrame(); // Ana pencereyi oluşturur

    JButton kaydet_buton = new JButton("İlaç Kaydet"); // İlaç kaydetmek için bir buton oluşturur
    JButton sorgula_buton = new JButton("İlaç Sorgula"); // İlaç sorgulamak için bir buton oluşturur

    public IlacTakipArayuzu() {

        kaydet_buton.setBounds(50, 50, 200, 40); // Kaydet butonunun konumunu ve boyutunu ayarlar
        sorgula_buton.setBounds(300, 50, 200, 40); // Sorgula butonunun konumunu ve boyutunu ayarlar

        kaydet_buton.setFocusable(false); // Kaydet butonunun odaklanabilirliğini kapatır
        sorgula_buton.setFocusable(false); // Sorgula butonunun odaklanabilirliğini kapatır

        kaydet_buton.addActionListener(new ActionListener() { // Kaydet butonuna tıklandığında çalışacak kodu tanımlar
            @Override
            public void actionPerformed(ActionEvent e) {
                Window_Kaydet window_kaydet = new Window_Kaydet(); // Yeni bir Window_Kaydet nesnesi oluşturur
            }
        });
        sorgula_buton.addActionListener(new ActionListener() { // Sorgula butonuna tıklandığında çalışacak kodu tanımlar
            @Override
            public void actionPerformed(ActionEvent e) {
                Window_Sorgula windows_sorgula = new Window_Sorgula(); // Yeni bir Window_Sorgula nesnesi oluşturur
            }
        });

        frame.add(kaydet_buton); // Kaydet butonunu ana pencereye ekler
        frame.add(sorgula_buton); // Sorgula butonunu ana pencereye ekler

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ana pencerenin kapatılması durumunda programın sonlanmasını sağlar
        frame.setSize(550, 200); // Ana pencerenin boyutunu ayarlar
        frame.setTitle("İlaç Takip Sistemi"); // Ana pencerenin başlığını ayarlar
        frame.setLocationRelativeTo(null); // Ana pencerenin ortalanmasını sağlar
        frame.setLayout(null); // Ana pencerenin düzenleyicisini kapatır
        frame.setVisible(true); // Ana pencerenin görünür olmasını sağlar

    }
}
