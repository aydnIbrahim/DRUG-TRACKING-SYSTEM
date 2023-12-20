package Drug;

public class Syrup extends Drug{

    public Syrup(String name, String barcode, String price) {
        super(name, barcode, price, "Drug.Syrup");
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
        return "Drug.Syrup";
    }

}
