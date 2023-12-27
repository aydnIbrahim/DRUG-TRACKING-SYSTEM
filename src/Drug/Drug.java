package Drug;

// Ana ilaç sınıfı
abstract class Drug {

    final String name;
    final String barcode;
    final String price;
    final String Type;

    public Drug(String name, String barcode, String price, String type) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        Type = type;
    }

    public abstract String getName();

    public abstract String getBarcode();

    public abstract String getPrice();

    public abstract String getType();
}
