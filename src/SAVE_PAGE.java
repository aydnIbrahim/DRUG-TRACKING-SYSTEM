import javax.swing.*; // Swing kütüphanesini içe aktarır
import java.awt.*; // AWT kütüphanesini içe aktarır
import java.awt.event.ActionEvent; // ActionEvent sınıfını içe aktarır
import java.awt.event.ActionListener; // ActionListener arayüzünü içe aktarır


// İlaç kaydetme arayüzü sınıfı, ilaç takip sistemi ile etkileşimli bir arayüz oluşturur
public class SAVE_PAGE {
    JFrame frame = new JFrame(); // Ana pencereyi oluşturur

    private JPanel panel_kaydet; // Kaydetme panelini tanımlar
    private JButton kaydet_buton; // Kaydetme butonunu tanımlar
    private JTextField ilac_isim_alan; // İlaç isim alanını tanımlar
    private JTextField ilac_barkod_alan; // İlaç barkod alanını tanımlar
    private JTextField ilac_fiyat_alan; // İlaç fiyat alanını tanımlar
    private JLabel ilac_isim_etiket; // İlaç isim etiketini tanımlar
    private JLabel ilac_barkod_etiket; // İlaç barkod etiketini tanımlar
    private JLabel ilac_fiyat_etiket; // İlaç fiyat etiketini tanımlar


    public SAVE_PAGE() {

        panel_kaydet = new JPanel(); // Kaydetme panelini oluşturur
        panel_kaydet.setLayout(new GridLayout(6, 2)); // Kaydetme panelinin düzenini ayarlar

        kaydet_buton = new JButton("Kaydet"); // Kaydetme butonunu oluşturur
        kaydet_buton.addActionListener(new ActionListener() { // Kaydetme butonuna tıklandığında çalışacak kodu tanımlar
            @Override
            public void actionPerformed(ActionEvent e) {

                ItsDao itsDao = new ItsDao();               // Yeni bir ItsDao nesnesi oluşturur
                String name = ilac_isim_alan.getText();     // İlaç isim alanındaki değeri alır
                String barkod = ilac_barkod_alan.getText(); // İlaç barkod alanındaki değeri alır
                String price = ilac_fiyat_alan.getText();   // İlaç fiyat alanındaki değeri alır

                try{
                    if(name.isEmpty()){ // İsim alanı boşsa
                        JOptionPane.showMessageDialog(frame,"İsim bilgisi boş bırakılamaz.", "Kaydetme Hatası", JOptionPane.ERROR_MESSAGE); // Hata mesajı gösterir
                    }
                    else if(!(barkod.length() == 13)){ // Barkod numarası 13 haneli değilse
                        JOptionPane.showMessageDialog(frame,"Lütfen barkod no bilgisini kontrol edin.", "Kaydetme Hatası", JOptionPane.ERROR_MESSAGE); // Hata mesajı gösterir
                    }
                    else if(price.isEmpty()){ // Fiyat alanı boşsa
                        JOptionPane.showMessageDialog(frame,"Fiyat bilgisi boş bırakılamaz.", "Kaydetme Hatası", JOptionPane.ERROR_MESSAGE); // Hata mesajı gösterir
                    } else if (itsDao.ilac_sorgu(ilac_barkod_alan.getText()).next()) { // Barkod numarası veritabanında varsa
                        if (itsDao.ilac_sorgu(ilac_barkod_alan.getText()).getString(2).equals(ilac_barkod_alan.getText())) // Barkod numarası eşleşiyorsa
                            JOptionPane.showMessageDialog(frame, "Bu barkod daha önce kaydedilmiş.", "Kaydetme Hatası", JOptionPane.ERROR_MESSAGE); // Hata mesajı gösterir
                    } else{
                        itsDao.ilac_ekle(name, barkod, price); // İlaç bilgilerini veritabanına ekler
                        JOptionPane.showMessageDialog(frame, "Kaydetme işlemi başarılı.", "Kaydet", JOptionPane.INFORMATION_MESSAGE); // Başarı mesajı gösterir
                        return;
                    }
                }
                catch (Exception ex){ // Hata yakalar
                    System.out.println(ex); // Hata mesajını yazdırır
                }

            }
        });


        ilac_isim_alan = new JTextField();    // Yeni bir ilaç ismi giriş alanı oluşturur
        ilac_barkod_alan = new JTextField();  // Yeni bir barkod no giriş alanı oluşturur
        ilac_fiyat_alan = new JTextField();   // Yeni bir ilaç fiyat giriş alanı oluşturur

        ilac_isim_etiket = new JLabel("ilaç ismi");     // Yeni bir ilaç ismi etiketi oluşturur
        ilac_barkod_etiket = new JLabel("barkod no");   // Yeni bir barkod no etiketi oluşturur
        ilac_fiyat_etiket = new JLabel("ilaç fiyatı");  // Yeni bir ilaç fiyatı etiketi oluşturur

        panel_kaydet.add(ilac_isim_etiket);        // Panelin ilk hücresine ilaç ismi etiketini ekler
        panel_kaydet.add(ilac_isim_alan);          // Panelin ikinci hücresine ilaç ismi giriş alanını ekler

        panel_kaydet.add(ilac_barkod_etiket);      // Panelin üçüncü hücresine ilaç barkodu etiketini ekler
        panel_kaydet.add(ilac_barkod_alan);        // Panelin dördündü hücresine ilaç barkod no giriş alanını ekler

        panel_kaydet.add(ilac_fiyat_etiket);       // Panelin beşinci hücresine ilaç fiyat etiketini ekler
        panel_kaydet.add(ilac_fiyat_alan);         // Panelin altıncı hücresine ilaç fiyat giriş alanını ekler

        panel_kaydet.add(kaydet_buton);            // Panelin son hücresine kaydetme butonunu ekler

        frame.add(panel_kaydet);                   // Kaydetme panelini ana pencereye ekler
        frame.setTitle("İlaç Kaydet");             // Ana pencerenin başlığını ayarlar
        frame.setSize(400, 300);      // Ana pencerenin boyutunu ayarlar
        frame.setLocationRelativeTo(null);         // Ana pencerenin ortalanmasını sağlar
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ana pencerenin kapatılma davranışını ayarlar
        frame.setVisible(true);                    // Ana pencerenin görünürlüğünü ayarlar
    }
}

