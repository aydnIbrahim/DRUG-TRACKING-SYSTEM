import java.sql.*; // SQL kütüphanesini içe aktarır
import java.util.Arrays;

// İlaç takip sistemi için veritabanı işlemlerini yapan bir sınıf
public class ItsDao {

    String url_ilac = "jdbc:mysql://localhost:3306/ilacdb"; // Veritabanı bağlantı adresi
    String username = "root"; // Veritabanı kullanıcı adı
    String passwd = ""; // Veritabanı şifresi

    public ItsDao() {
        // Yapıcı metot (constructor) içinde istisna fırlatmayın
    }

    // Veritabanında ilaç sorgusu yapar
    public ResultSet ilac_sorgu(String barkod) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC sürücüsünü yükler
            Connection connection = DriverManager.getConnection(url_ilac, username, passwd); // Veritabanına bağlanır
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Bir ifade nesnesi oluşturur
            ResultSet resultSet = statement.executeQuery("select * from ilac"); // Veritabanından ilaç tablosunu seçer

            while (resultSet.next()){ // Sonuç kümesindeki her satır için
                if (resultSet.getString(2).equals(barkod)){ // Barkod numarası eşleşiyorsa
                    return resultSet;
                }
            }
            return resultSet;

        } catch (ClassNotFoundException | SQLException e) { // Hata yakalar
            // İstisna durumunu burada yönetin
            e.printStackTrace(); // Hata mesajını yazdırır
        }
        return null; // Hata durumunda null döndürür
    }

    // Veritabanına ilaç ekler
    public void ilac_ekle(String name, String barkod, String p){
        try {
            int price = Integer.parseInt(p); // Fiyatı tam sayıya dönüştürür
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC sürücüsünü yükler
            Connection connection = DriverManager.getConnection(url_ilac, username, passwd); // Veritabanına bağlanır
            // Tablo adını ve kolonlarını belirtin
            String sql = "INSERT INTO ilac (name, barkod, price) VALUES (?, ?, ?)"; // Ekleme sorgusunu oluşturur
            // PreparedStatement nesnesi oluşturur
            PreparedStatement ps = connection.prepareStatement(sql);
            // Parametreleri ayarlar
            ps.setString(1, name); // İlaç ismini ayarlar
            ps.setString(2, barkod); // Barkod numarasını ayarlar
            ps.setInt(3, price); // Fiyatı ayarlar
            // Sorguyu çalıştırır
            ps.executeUpdate();
            // Kaynakları serbest bırakır
            ps.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) { // Hata yakalar
            // İstisna durumunu burada yönetin
            e.printStackTrace(); // Hata mesajını yazdırır
        }
    }

    public boolean ilac_sil(String barkod){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url_ilac, username, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ilac");

            while (resultSet.next()){ // Sonuç kümesindeki her satır için
                if (resultSet.getString(2).equals(barkod)){ // Barkod numarası eşleşiyorsa
                    statement.executeUpdate("DELETE FROM ilac WHERE barkod = '" + barkod + "'");
                    return true;
                }
            }
            return false;

        }catch (Exception e2){
            e2.printStackTrace();
            return false;
        }
    }

    public boolean control(String username, char[] password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url_ilac, this.username, passwd);
        String query = "SELECT * FROM login WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, String.valueOf(password));
        ResultSet result = statement.executeQuery();

        return result.next();
    }
}
