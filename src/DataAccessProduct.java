public interface DataAccessProduct extends DataConnect{
    public boolean createProduct(ProductModel product);
    public ProductModel readProduct(int productID);
    public boolean updateProduct(ProductModel product);
    public boolean deleteProduct(int productID);
}
