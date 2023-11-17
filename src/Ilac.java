
// İlaç sınıfı, ilaç ismi, barkod no ve satış fiyatı gibi özellikleri tutar
class Ilac {
    private String isim;
    private String barkod;
    private double fiyat;

    // İlaç nesnesi oluşturmak için kurucu metot
    public Ilac(String isim, String barkod, double fiyat) {
        this.isim = isim;
        this.barkod = barkod;
        this.fiyat = fiyat;
    }

    // İlaç ismini döndüren metot
    public String getIsim() {
        return isim;
    }

    // İlaç barkodunu döndüren metot
    public String getBarkod() {
        return barkod;
    }

    // İlaç fiyatını döndüren metot
    public double getFiyat() {
        return fiyat;
    }

    // İlaç nesnesinin bilgilerini birleştiren metot
    public String toString() {
        return "İlaç İsmi: " + isim + "\nBarkod No: " + barkod + "\nSatış Fiyatı: " + fiyat + " TL";
    }
}
