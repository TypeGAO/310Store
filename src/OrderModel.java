public class OrderModel {
    private int productID;
    private int supplierID;
    private int qty;
    private double total;
    private String date;

    public int getProductID() { return productID; }
    public int getSupplierID() { return supplierID; }
    public int getQty() { return qty; }
    public double getTotal() { return total; }
    public String getDate() { return date; }

    public void setProductID(int productID) { this.productID = productID; }
    public void setSupplierID(int supplierID) { this.supplierID = supplierID; }
    public void setQty(int qty) { this.qty = qty; }
    public void setTotal(double total) { this.total = total; }

    public void setDate(String date) { this.date = date; }
}
