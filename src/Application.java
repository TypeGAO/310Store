public class Application {
    private static Application instance;

    public static Application getInstance(){
        if(instance == null){
            instance = new Application();
        }
        return instance;
    }

    DataAccessCustomer dataAdaptCustomer = new MySQLDataAdapter();
    DataAccessSupplier dataAdaptSupplier = new MySQLDataAdapter();
    DataAccessProduct dataAdaptProduct = new MySQLDataAdapter();
    DataAccessPayment dataAdaptPayment = new MySQLDataAdapter();
    DataAccessOrder dataAccessOrder = new MySQLDataAdapter();
    DataAccessReport dataAccessReport = new MySQLDataAdapter();

    public CustomerView customerView = new CustomerView();
    public ProductView productView = new ProductView();
    public SupplierView supplierView = new SupplierView();
    public PaymentView paymentView = new PaymentView();
    public OrderView orderView = new OrderView();
    public  ReportView reportView = new ReportView();
    public HomeView homeView = new HomeView();

    public static void main(String[] args){
        Application.getInstance().dataAdaptCustomer.connect();
        Application.getInstance().dataAdaptSupplier.connect();
        Application.getInstance().dataAdaptProduct.connect();
        Application.getInstance().dataAdaptPayment.connect();
        Application.getInstance().dataAccessOrder.connect();
        Application.getInstance().dataAccessReport.connect();
        Application.getInstance().homeView.setVisible(true);
    }
}
