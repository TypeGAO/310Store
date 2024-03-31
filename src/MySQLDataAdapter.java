import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Supplier;

public class MySQLDataAdapter implements DataAccessCustomer, DataAccessPayment, DataAccessProduct, DataAccessSupplier, DataAccessOrder, DataAccessReport {
    private Connection connection = null;

    @Override
    public int connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "INSERT DB URL HERE";

            connection = DriverManager.getConnection("INSERT DB INFO HERE");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int disconnect() {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public boolean createCustomer(CustomerModel customer){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customer VALUES (?, ?, ?)");
            statement.setInt(1, customer.getID());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getAddr());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public CustomerModel readCustomer(int customerID){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer WHERE CustomerID=?");
            statement.setInt(1, customerID);
            ResultSet result = statement.executeQuery();

            result.next();
            CustomerModel customer = new CustomerModel();
            customer.setID(result.getInt("customerid"));
            customer.setName(result.getString("customername"));
            customer.setAddr(result.getString("customeraddr"));

            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateCustomer(CustomerModel customer){
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE customer SET customername=?, customeraddr=? WHERE customerid=?");
            statement.setInt(3, customer.getID());
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddr());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createSupplier(SupplierModel supplier){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO supplier VALUES (?, ?, ?, ?)");
            statement.setInt(1, supplier.getID());
            statement.setString(2, supplier.getName());
            statement.setString(3, supplier.getPhone());
            statement.setString(4, supplier.getAddr());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public SupplierModel readSupplier(int supplierID){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM supplier WHERE SupplierID=?");
            statement.setInt(1, supplierID);
            ResultSet result = statement.executeQuery();

            result.next();
            SupplierModel supplier = new SupplierModel();
            supplier.setID(result.getInt("SupplierID"));
            supplier.setName(result.getString("SupplierName"));
            supplier.setPhone(result.getString("SupplierPhone"));
            supplier.setAddr(result.getString("SupplierAddr"));

            return supplier;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateSupplier(SupplierModel supplier){
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE supplier SET SupplierName=?, SupplierPhone=?, SupplierAddr=? WHERE SupplierID=?");
            statement.setInt(4, supplier.getID());
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getPhone());
            statement.setString(3, supplier.getAddr());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createProduct(ProductModel product){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO product VALUES (?, ?, ?, ?)");
            statement.setInt(1, product.getID());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQty());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ProductModel readProduct(int productID){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE ProductID=?");
            statement.setInt(1, productID);
            ResultSet result = statement.executeQuery();

            result.next();
            ProductModel supplier = new ProductModel();
            supplier.setID(result.getInt("ProductID"));
            supplier.setName(result.getString("ProductName"));
            supplier.setPrice(result.getDouble("ProductPrice"));
            supplier.setQty(result.getInt("ProductQty"));

            return supplier;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateProduct(ProductModel product){
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE product SET ProductName=?, ProductPrice=?, ProductQty=? WHERE ProductID=?");
            statement.setInt(4, product.getID());
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQty());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createPayment(PaymentModel payment){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO payment VALUES (?, ?, DATE ?, ?, ?, ?, ?)");
            statement.setInt(1, payment.getCustomerID());
            statement.setInt(2, payment.getProductID());
            statement.setString(3, payment.getDate());
            statement.setInt(4, payment.getQty());
            statement.setDouble(5, payment.getTotal());
            statement.setString(6, payment.getCard());
            statement.setInt(7, payment.getCVV());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public PaymentModel readPayment(int customerID, int productID, String date){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM payment WHERE PaymentCustomer=? and PaymentProduct=? and PaymentDate=DATE ?");
            statement.setInt(1, customerID);
            statement.setInt(2, productID);
            statement.setString(3, date);
            ResultSet result = statement.executeQuery();

            result.next();
            PaymentModel payment = new PaymentModel();
            payment.setCustomerID(result.getInt("PaymentCustomer"));
            payment.setProductID(result.getInt("PaymentProduct"));
            payment.setDate(result.getString("PaymentDate"));
            payment.setQty(result.getInt("PaymentQty"));
            payment.setTotal(result.getDouble("PaymentTotal"));
            payment.setCard(result.getString("PaymentCard"));
            payment.setCVV(result.getInt("PaymentCVV"));

            return payment;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updatePayment(PaymentModel payment){
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE payment SET PaymentDate=?, PaymentQty=?, PaymentTotal=?, PaymentCard=?, PaymentCVV=? WHERE PaymentCustomer=? AND PaymentProduct=?");
            statement.setInt(6, payment.getCustomerID());
            statement.setInt(7, payment.getProductID());
            statement.setString(1, payment.getDate());
            statement.setInt(2, payment.getQty());
            statement.setDouble(3, payment.getTotal());
            statement.setString(4, payment.getCard());
            statement.setInt(5, payment.getCVV());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createOrder(OrderModel order){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO orders VALUES (?, ?, ?, ?, DATE ?)");
            statement.setInt(1, order.getProductID());
            statement.setInt(2, order.getSupplierID());
            statement.setInt(3, order.getQty());
            statement.setDouble(4, order.getTotal());
            statement.setString(5, order.getDate());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public OrderModel readOrder(int productID, int supplierID, String date){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE OrderProduct=? AND OrderSupplier=? AND OrderDate=DATE ?");
            statement.setInt(1, productID);
            statement.setInt(2, supplierID);
            statement.setString(3, date);
            ResultSet result = statement.executeQuery();

            result.next();
            OrderModel order = new OrderModel();
            order.setProductID(result.getInt("OrderProduct"));
            order.setSupplierID(result.getInt("OrderSupplier"));
            order.setQty(result.getInt("OrderQty"));
            order.setTotal(result.getDouble("OrderTotal"));
            order.setDate(result.getString("OrderDate"));

            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateOrder(OrderModel order){
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE orders SET OrderQty=?, OrderTotal=?, OrderDate=? WHERE OrderProduct=? AND OrderSupplier=?");
            statement.setInt(4, order.getProductID());
            statement.setInt(5, order.getSupplierID());
            statement.setInt(1, order.getQty());
            statement.setDouble(2, order.getTotal());
            statement.setString(3, order.getDate());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String totalSale(String startDate, String endDate, boolean desc){
        String report = String.format("%s, %s, %s\n", "Year", "Month", "TotalSale");
        try{
            String sql = "SELECT YEAR(PaymentDate) AS Year,\n" +
                    "       MONTH(PaymentDate) AS Month,\n" +
                    "       SUM(PaymentTotal) AS TotalSale\n" +
                    "FROM Payment\n" +
                    "WHERE PaymentDate BETWEEN DATE ? AND DATE ?\n" +
                    "GROUP BY YEAR(PaymentDate), MONTH(PaymentDate)\n";
            if(desc == true){
                sql += "ORDER BY Year DESC, Month DESC";
            } else {
                sql += "ORDER BY Year ASC, Month ASC";
            }

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, startDate);
            statement.setString(2, endDate);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                String s = String.format("%d, %d, %.2f", result.getInt("Year"), result.getInt("Month"), result.getDouble("TotalSale"));
                report += s + '\n';
            }

        }catch (SQLException e) {
            e.printStackTrace();
            return "Failed to compute";
        }
        return report;
    }

    public String productSale(String startDate, String endDate, boolean desc){
        String report = String.format("%s, %s, %s, %s\n", "ProductID", "ProductName", "TotalQuantity", "TotalSale");
        try{
            String sql = "SELECT p.ProductID,\n" +
                    "       p.ProductName,\n" +
                    "       SUM(py.PaymentQty) AS TotalQuantity,\n" +
                    "       SUM(py.PaymentTotal) AS TotalSale\n" +
                    "FROM Product p\n" +
                    "JOIN Payment py ON p.ProductID = py.PaymentProduct\n" +
                    "WHERE py.PaymentDate BETWEEN DATE ? AND DATE ?\n" +
                    "GROUP BY p.ProductID, p.ProductName\n";
            if(desc == true){
                sql += "ORDER BY TotalSale DESC;";
            } else {
                sql += "ORDER BY TotalSale ASC;";
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                String s = String.format("%d, %s, %d,%.2f", result.getInt("ProductID"), result.getString("ProductName"), result.getInt("TotalQuantity"), result.getDouble("TotalSale"));
                report += s + '\n';
            }

        }catch (SQLException e) {
            e.printStackTrace();
            return "Failed to compute";
        }
        return report;
    }
    public String customerSale(String startDate, String endDate, boolean desc){
        String report = String.format("%s, %s, %s\n", "CustomerID", "CustomerName", "TotalSale");
        try{
            String sql = "SELECT c.CustomerID,\n" +
                    "       c.CustomerName,\n" +
                    "       SUM(py.PaymentTotal) AS TotalSale\n" +
                    "FROM Customer c\n" +
                    "JOIN Payment py ON c.CustomerID = py.PaymentCustomer\n" +
                    "WHERE py.PaymentDate BETWEEN DATE ? AND DATE ?\n" +
                    "GROUP BY c.CustomerID, c.CustomerName\n";
            if(desc == true){
                sql += "ORDER BY TotalSale DESC;";
            } else {
                sql += "ORDER BY TotalSale ASC;";
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                String s = String.format("%d, %s,%.2f", result.getInt("CustomerID"), result.getString("CustomerName"), result.getDouble("TotalSale"));
                report += s + '\n';
            }

        }catch (SQLException e) {
            e.printStackTrace();
            return "Failed to compute";
        }
        return report;
    }

    @Override
    public boolean deleteCustomer(int customerID) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM customer WHERE CustomerID=?");
            statement.setInt(1, customerID);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteSupplier(int supplierID){
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM supplier WHERE SupplierID=?");
            statement.setInt(1, supplierID);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteProduct(int productID){
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE ProductID=?");
            statement.setInt(1, productID);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deletePayment(int customerID, int productID, String date){
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM payment WHERE PaymentCustomer=? AND PaymentProduct=? AND PaymentDate=DATE ?");
            statement.setInt(1, customerID);
            statement.setInt(2, productID);
            statement.setString(3, date);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteOrder(int productID, int supplierID, String date){
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE OrderProduct=? AND OrderSupplier=? and OrderDate=DATE ?");
            statement.setInt(1, productID);
            statement.setInt(2, supplierID);
            statement.setString(3, date);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}