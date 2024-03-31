public interface DataAccessSupplier extends DataConnect {
    public boolean createSupplier(SupplierModel supplier);
    public SupplierModel readSupplier(int supplierID);
    public boolean updateSupplier(SupplierModel supplier);
    public boolean deleteSupplier(int supplierID);
}
