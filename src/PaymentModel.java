public class PaymentModel {
    private int customerID;
    private int productID;
    private String date;
    private int qty;
    private double total;
    private String card;
    private int cvv;

    public int getCustomerID() { return customerID; }
    public int getProductID() { return productID; }
    public String getDate() { return date; }
    public int getQty() { return qty; }
    public double getTotal() { return total; }
    public String getCard() { return card; }
    public int getCVV() { return cvv; }

    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setProductID(int productID) { this.productID = productID; }
    public void setDate(String date) { this.date = date; }
    public void setQty(int qty) { this.qty = qty; }
    public void setTotal(double total) { this.total = total; }
    public void setCard(String card) { this.card = card; }
    public void setCVV(int cvv) { this.cvv = cvv; }
}
