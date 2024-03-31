public interface DataAccessOrder extends DataConnect{
    public boolean createOrder(OrderModel order);
    public OrderModel readOrder(int productID, int supplierID, String date);
    public boolean updateOrder(OrderModel order);
    public boolean deleteOrder(int productID, int supplierID, String date);
}
