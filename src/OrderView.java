import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OrderView extends JFrame {
    public JTextField txtProductID = new JTextField(10);
    public JTextField txtSupplierID = new JTextField(10);
    public JTextField txtOrderDate = new JTextField(30);
    public JTextField txtOrderTotal = new JTextField(10);
    public JTextField txtOrderQty = new JTextField(10);

    public JButton btnAddOrder = new JButton("Add Order");
    public JButton btnGetOrder = new JButton("Search for Order");
    public JButton btnUpdateOrder = new JButton("Update Order");
    public JButton btnDeleteOrder = new JButton("Delete Order");

    public OrderView() {

        this.setSize(500, 250);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.getContentPane().add(new JLabel("Order View"));

        JPanel main = new JPanel(new SpringLayout());
        main.add(new JLabel("ProductID:"));
        main.add(txtProductID);
        main.add(new JLabel("SupplierID:"));
        main.add(txtSupplierID);
        main.add(new JLabel("Date:"));
        main.add(txtOrderDate);
        main.add(new JLabel("Qty:"));
        main.add(txtOrderQty);
        main.add(new JLabel("Total:"));
        main.add(txtOrderTotal);

        JPanel buttons = new JPanel(new SpringLayout());
        buttons.add(btnAddOrder);
        buttons.add(btnGetOrder);
        buttons.add(btnUpdateOrder);
        buttons.add(btnDeleteOrder);

        SpringUtilities.makeCompactGrid(main,
                5, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        SpringUtilities.makeCompactGrid(buttons,
                1,4,6,10,6,6);
        this.getContentPane().add(main);
        this.getContentPane().add(buttons);

        this.btnAddOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pid;
                try {
                    pid = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }

                int sid;
                try {
                    sid = Integer.parseInt(txtSupplierID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Supplier ID! Please provide a valid ID!");
                    return;
                }

                double total;
                try {
                    total = Double.parseDouble(txtOrderTotal.getText());

                    if (total < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid total! It should be positive!");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format! Please provide a valid number!");
                    return;
                }

                String date = txtOrderDate.getText().trim();
                if (date.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid date! Please provide a non-empty value! Use the format YYY-MM-DD");
                    return;
                }

                int qty;
                try {
                    qty = Integer.parseInt(txtOrderQty.getText());

                    if (qty < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity! It should be positive!");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity! Please provide a valid ID!");
                    return;
                }

                // Done all validations! Make an object for this product!

                OrderModel model = new OrderModel();
                model.setProductID(pid);
                model.setSupplierID(sid);
                model.setQty(qty);
                model.setTotal(total);
                model.setDate(date);

                // Store the model to the database
                ProductModel product = Application.getInstance().dataAdaptProduct.readProduct(pid);
                int newQty = product.getQty() + qty;
                product.setQty(newQty);
                Application.getInstance().dataAdaptProduct.updateProduct(product);

                boolean res = Application.getInstance().dataAccessOrder.createOrder(model);
                if (!res) {
                    JOptionPane.showMessageDialog(null, "Order is NOT saved!");
                } else {
                    JOptionPane.showMessageDialog(null, "Order is saved!");
                }

            }
        });

        this.btnGetOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pid;
                try {
                    pid = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }

                int sid;
                try {
                    sid = Integer.parseInt(txtSupplierID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Supplier ID! Please provide a valid ID!");
                    return;
                }

                String date = txtOrderDate.getText().trim();
                if (date.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                OrderModel model = Application.getInstance().dataAccessOrder.readOrder(pid, sid, date);

                if(model == null){
                    JOptionPane.showMessageDialog(null, "Unable to find order!");
                }

                txtOrderDate.setText(model.getDate());
                txtOrderTotal.setText(Double.toString(model.getTotal()));
                txtOrderQty.setText(Integer.toString(model.getQty()));
            }
        });

        this.btnUpdateOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pid;
                try {
                    pid = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }

                int sid;
                try {
                    sid = Integer.parseInt(txtSupplierID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Supplier ID! Please provide a valid ID!");
                    return;
                }

                double total;
                try {
                    total = Double.parseDouble(txtOrderTotal.getText());

                    if (total < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid total! It should be positive!");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format! Please provide a valid number!");
                    return;
                }

                String date = txtOrderDate.getText().trim();
                if (date.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                int qty;
                try {
                    qty = Integer.parseInt(txtOrderQty.getText());

                    if (qty < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity! It should be positive!");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity! Please provide a valid ID!");
                    return;
                }

                // Done all validations! Make an object for this product!

                OrderModel model = new OrderModel();
                model.setProductID(pid);
                model.setSupplierID(sid);
                model.setQty(qty);
                model.setTotal(total);
                model.setDate(date);

                // Store the model to the database
                ProductModel product = Application.getInstance().dataAdaptProduct.readProduct(pid);
                OrderModel oldOrder = Application.getInstance().dataAccessOrder.readOrder(pid, sid, date);

                int oldOrderQty = oldOrder.getQty();
                int newQty = product.getQty() + qty - oldOrderQty;
                product.setQty(newQty);
                Application.getInstance().dataAdaptProduct.updateProduct(product);

                boolean res = Application.getInstance().dataAccessOrder.updateOrder(model);
                if (!res) {
                    JOptionPane.showMessageDialog(null, "Order is NOT updated!");
                } else {
                    JOptionPane.showMessageDialog(null, "Order has been updated");
                }

            }
        });

        this.btnDeleteOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pid;
                try {
                    pid = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }

                int sid;
                try {
                    sid = Integer.parseInt(txtSupplierID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Supplier ID! Please provide a valid ID!");
                    return;
                }

                String date = txtOrderDate.getText().trim();
                if (date.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                boolean res = Application.getInstance().dataAccessOrder.deleteOrder(pid, sid, date);
                if (!res) {
                    JOptionPane.showMessageDialog(null, "Order was NOT deleted!");
                } else {
                    JOptionPane.showMessageDialog(null, "Order was deleted!");
                }
            }
        });
    }
}
