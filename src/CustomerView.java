import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CustomerView extends JFrame {
    public JTextField txtCustomerID = new JTextField(10);
    public JTextField txtCustomerName = new JTextField(300);
    public JTextField txtCustomerAddr = new JTextField(300);

    public JButton btnAddCustomer = new JButton("Add Customer");
    public JButton btnGetCustomer = new JButton("Search for Customer");
    public JButton btnUpdateCustomer = new JButton("Update Customer");
    public JButton btnDeleteCustomer = new JButton("Delete Customer");

    public CustomerView() {

        this.setSize(580, 200);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.getContentPane().add(new JLabel("Customer View"));

        JPanel main = new JPanel(new SpringLayout());
        main.add(new JLabel("ID:"));
        main.add(txtCustomerID);
        main.add(new JLabel("Name:"));
        main.add(txtCustomerName);
        main.add(new JLabel("Address:"));
        main.add(txtCustomerAddr);

        JPanel buttons = new JPanel(new SpringLayout());
        buttons.add(btnAddCustomer);
        buttons.add(btnGetCustomer);
        buttons.add(btnUpdateCustomer);
        buttons.add(btnDeleteCustomer);

        SpringUtilities.makeCompactGrid(main,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        SpringUtilities.makeCompactGrid(buttons,
                1,4,6,10,6,6);
        this.getContentPane().add(main);
        this.getContentPane().add(buttons);

        this.btnAddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtCustomerID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Customer ID! Please provide a valid ID!");
                    return;
                }

                String name;
                name = txtCustomerName.getText().trim();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name! Please provide a non-empty value!");
                    return;
                }

                String addr = txtCustomerAddr.getText().trim();
                if (addr.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid address! Please provide a non-empty value!");
                    return;
                }

                // Done all validations! Make an object for this product!

                CustomerModel model = new CustomerModel();
                model.setID(id);
                model.setName(name);
                model.setAddr(addr);

                // Store the model to the database

                boolean res = Application.getInstance().dataAdaptCustomer.createCustomer(model);
                if (!res) {
                    JOptionPane.showMessageDialog(null, "Customer is NOT saved!");
                } else {
                    JOptionPane.showMessageDialog(null, "Customer is saved!");
                }
            }
        });

        this.btnGetCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtCustomerID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Customer ID! Please provide a valid ID!");
                    return;
                }


                CustomerModel model = Application.getInstance().dataAdaptCustomer.readCustomer(id);

                if(model == null){
                    JOptionPane.showMessageDialog(null, "Unable to find customer from id!");
                }

                txtCustomerName.setText(model.getName());
                txtCustomerAddr.setText(model.getAddr());
            }
        });

        this.btnUpdateCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtCustomerID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Customer ID! Please provide a valid ID!");
                    return;
                }

                String name;
                name = txtCustomerName.getText();

                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name! Please provide a non-empty value!");
                    return;
                }

                String addr = txtCustomerAddr.getText().trim();
                if (addr.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid address! Please provide a non-empty value!");
                    return;
                }

                // Done all validations! Make an object for this product!

                CustomerModel model = new CustomerModel();
                model.setID(id);
                model.setName(name);
                model.setAddr(addr);

                // Store the model to the database

                boolean res = Application.getInstance().dataAdaptCustomer.updateCustomer(model);
                if (!res) {
                    JOptionPane.showMessageDialog(null, "Customer is NOT updated!");
                } else {
                    JOptionPane.showMessageDialog(null, "Customer has been updated");
                }

            }
        });

        this.btnDeleteCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtCustomerID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Customer ID! Please provide a valid ID!");
                    return;
                }

                boolean res = Application.getInstance().dataAdaptCustomer.deleteCustomer(id);

                if (!res) {
                    JOptionPane.showMessageDialog(null, "Customer is NOT deleted!");
                } else {
                    JOptionPane.showMessageDialog(null, "Customer has been deleted");
                }
            }
        });
    }
}
