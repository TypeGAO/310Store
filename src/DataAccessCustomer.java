public interface DataAccessCustomer extends DataConnect{
    public boolean createCustomer(CustomerModel customer);
    public CustomerModel readCustomer(int customerID);
    public boolean updateCustomer(CustomerModel customer);
    public boolean deleteCustomer(int customerID);
}
