public interface DataAccessPayment extends DataConnect  {
    public boolean createPayment(PaymentModel payment);
    public PaymentModel readPayment(int customerID, int productID, String date);
    public boolean updatePayment(PaymentModel payment);
    public boolean deletePayment(int customerID, int productID, String date);
}
