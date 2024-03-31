import com.sun.xml.internal.ws.api.ha.StickyFeature;

public class ProductModel {
    private int id;
    private String name;
    private double price;
    private int qty;

    public int getID() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQty() { return qty; }

    public void setID(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setQty(int qty) { this.qty = qty; }
}
