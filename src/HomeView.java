import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JFrame{
    public JButton btnSupplier = new JButton("Supplier Screen");
    public JButton btnCustomer = new JButton("Customer Screen");
    public JButton btnProduct = new JButton("Product Screen");
    public JButton btnPayment = new JButton("Payment Screen");
    public JButton btnOrder = new JButton("Order Screen");
    public JButton btnReport = new JButton("Report Screen");

    public HomeView() {
        this.setSize(300, 300);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel(new SpringLayout());
        panel.add(btnCustomer);
        panel.add(btnProduct);
        panel.add(btnSupplier);
        panel.add(btnPayment);
        panel.add(btnOrder);
        panel.add(btnReport);

        SpringUtilities.makeCompactGrid(panel,
                3,2,0,50,0,10);

        this.add(panel);

        this.btnCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().customerView.setVisible(true);
            }
        });

        this.btnProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().productView.setVisible(true);
            }
        });

        this.btnSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().supplierView.setVisible(true);
            }
        });

        this.btnPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().paymentView.setVisible(true);
            }
        });

        this.btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().orderView.setVisible(true);
            }
        });

        this.btnReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().reportView.setVisible(true);
            }
        });

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
