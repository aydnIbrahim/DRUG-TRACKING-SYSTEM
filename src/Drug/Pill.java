package Drug;

public class Pill extends Drug{

    public Pill(String name, String barcode, String price) {
        super(name, barcode, price, "Drug.Pill");
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBarcode() {
        return barcode;
    }

    @Override
    public String getPrice() {
        return price;
    }

    @Override
    public String getType() {
        return "Type";
    }
}
