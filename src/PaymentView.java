import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentView extends JFrame {
    public JTextField txtCustomerID = new JTextField(10);
    public JTextField txtProductID = new JTextField(10);
    public JTextField txtPaymentDate = new JTextField(10);
    public JTextField txtPaymentQty = new JTextField(30);
    public JTextField txtPaymentTotal = new JTextField(10);
    public JTextField txtPaymentCard = new JTextField(10);
    public JTextField txtPaymentCVV = new JTextField(10);

    public JButton btnAddPayment = new JButton("Add Payment");
    public JButton btnGetPayment = new JButton("Search for Payment");

    public JButton btnUpdatePayment = new JButton("Update Payment");
    public JButton btnDeletePayment = new JButton("Delete Payment");

    public PaymentView() {

        this.setSize(550, 300);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.getContentPane().add(new JLabel("Payment View"));

        JPanel main = new JPanel(new SpringLayout());
        main.add(new JLabel("Customer ID:"));
        main.add(txtCustomerID);
        main.add(new JLabel("Product ID:"));
        main.add(txtProductID);
        main.add(new JLabel("Date:"));
        main.add(txtPaymentDate);
        main.add(new JLabel("Qty:"));
        main.add(txtPaymentQty);
        main.add(new JLabel("Total:"));
        main.add(txtPaymentTotal);
        main.add(new JLabel("Card Number:"));
        main.add(txtPaymentCard);
        main.add(new JLabel("CVV:"));
        main.add(txtPaymentCVV);

        JPanel buttons = new JPanel(new SpringLayout());
        buttons.add(btnAddPayment);
        buttons.add(btnGetPayment);
        buttons.add(btnUpdatePayment);
        buttons.add(btnDeletePayment);

        SpringUtilities.makeCompactGrid(main,
                7, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        SpringUtilities.makeCompactGrid(buttons,
                1,4,6,10,6,6);
        this.getContentPane().add(main);
        this.getContentPane().add(buttons);

        this.btnAddPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cid;
                try {
                    cid = Integer.parseInt(txtCustomerID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Customer ID! Please provide a valid ID!");
                    return;
                }

                int pid;
                try {
                    pid = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }

                double total;
                try {
                    total = Double.parseDouble(txtPaymentTotal.getText());

                    if (total < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid total! It should be positive!");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format! Please provide a valid number!");
                    return;
                }

                int qty;
                try {
                    qty = Integer.parseInt(txtPaymentQty.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity! Please provide a valid ID!");
                    return;
                }

                String date = txtPaymentDate.getText().trim();
                if (date.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                String card = txtPaymentCard.getText().trim().replaceAll("\\s","");
                String regex = "\\d+";
                if (card.isEmpty() || !card.matches(regex)) {
                    JOptionPane.showMessageDialog(null, "Invalid card! Please provide a non-empty value!");
                    return;
                }

                int cvv;
                try {
                    cvv = Integer.parseInt(txtPaymentCVV.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity! Please provide a valid ID!");
                    return;
                }

                // Done all validations! Make an object for this product!

                PaymentModel model = new PaymentModel();
                model.setCustomerID(cid);
                model.setProductID(pid);
                model.setDate(date);
                model.setTotal(total);
                model.setQty(qty);
                model.setCard(card);
                model.setCVV(cvv);

                // Store the model to the database

                ProductModel product = Application.getInstance().dataAdaptProduct.readProduct(pid);
                int oldQty = product.getQty();
                int newQty = oldQty - qty;
                if(newQty < 0){
                    JOptionPane.showMessageDialog(null, "There is not enough of the product" + product.getName());
                } else {
                    product.setQty(newQty);
                    Application.getInstance().dataAdaptProduct.updateProduct(product);
                    boolean res = Application.getInstance().dataAdaptPayment.createPayment(model);
                    if (!res) {
                        JOptionPane.showMessageDialog(null, "Payment is NOT saved!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Payment is saved!");
                    }
                }
            }
        });

        this.btnGetPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cid;
                try {
                    cid = Integer.parseInt(txtCustomerID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Customer ID! Please provide a valid ID!");
                    return;
                }

                int pid;
                try {
                    pid = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }

                String date = txtPaymentDate.getText().trim();
                if (date.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                PaymentModel model = Application.getInstance().dataAdaptPayment.readPayment(cid,pid,date);

                if(model == null){
                    JOptionPane.showMessageDialog(null, "Unable to find payment!");
                }

                txtPaymentQty.setText(Integer.toString(model.getQty()));
                txtPaymentTotal.setText(Double.toString(model.getTotal()));
                txtPaymentDate.setText(model.getDate());
                txtPaymentCard.setText(model.getCard());
                txtPaymentCVV.setText(Integer.toString(model.getCVV()));
            }
        });

        this.btnUpdatePayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cid;
                try {
                    cid = Integer.parseInt(txtCustomerID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Customer ID! Please provide a valid ID!");
                    return;
                }

                int pid;
                try {
                    pid = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }

                double total;
                try {
                    total = Double.parseDouble(txtPaymentTotal.getText());

                    if (total < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid total! It should be positive!");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format! Please provide a valid number!");
                    return;
                }

                int qty;
                try {
                    qty = Integer.parseInt(txtPaymentQty.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity! Please provide a valid ID!");
                    return;
                }

                String date = txtPaymentDate.getText().trim();
                if (date.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                String card = txtPaymentCard.getText().trim().replaceAll("\\s","");
                String regex = "\\d+";
                if (card.isEmpty() || !card.matches(regex)) {
                    JOptionPane.showMessageDialog(null, "Invalid card! Please provide a non-empty value!");
                    return;
                }

                int cvv;
                try {
                    cvv = Integer.parseInt(txtPaymentCVV.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity! Please provide a valid ID!");
                    return;
                }

                // Done all validations! Make an object for this product!

                PaymentModel model = new PaymentModel();
                model.setCustomerID(cid);
                model.setProductID(pid);
                model.setDate(date);
                model.setTotal(total);
                model.setQty(qty);
                model.setCard(card);
                model.setCVV(cvv);

                // Store the model to the database

                ProductModel product = Application.getInstance().dataAdaptProduct.readProduct(pid);
                int oldQty = product.getQty();
                int newQty = oldQty - qty;
                if(newQty < 0){
                    JOptionPane.showMessageDialog(null, "There is not enough of the product" + product.getName());
                } else {
                    boolean res = Application.getInstance().dataAdaptPayment.updatePayment(model);
                    if (!res) {
                        JOptionPane.showMessageDialog(null, "Payment is NOT updated!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Payment is updated!");
                    }
                }

            }
        });

        this.btnDeletePayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cid;
                try {
                    cid = Integer.parseInt(txtCustomerID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Customer ID! Please provide a valid ID!");
                    return;
                }

                int pid;
                try {
                    pid = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }

                String date = txtPaymentDate.getText().trim();
                if (date.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                boolean res = Application.getInstance().dataAdaptPayment.deletePayment(cid,pid,date);

                if (!res) {
                    JOptionPane.showMessageDialog(null, "Payment was NOT deleted!");
                } else {
                    JOptionPane.showMessageDialog(null, "Payment was deleted!");
                }
            }
        });
    }
}
