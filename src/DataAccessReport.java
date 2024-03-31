public interface DataAccessReport extends DataConnect {
    public String totalSale(String startDate, String endDate, boolean desc);
    public String productSale(String startDate, String endDate, boolean desc);
    public String customerSale(String startDate, String endDate, boolean desc);
}
