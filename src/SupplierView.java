import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SupplierView extends JFrame {
    public JTextField txtSupplierID = new JTextField(10);
    public JTextField txtSupplierName = new JTextField(50);
    public JTextField txtSupplierPhone = new JTextField(10);
    public JTextField txtSupplierAddr = new JTextField(300);

    public JButton btnAddSupplier = new JButton("Add Supplier");

    public JButton btnGetSupplier = new JButton("Search for Supplier");

    public JButton btnUpdateSupplier = new JButton("Update Supplier");
    public JButton btnDeleteSupplier = new JButton("Delete Supplier");

    public SupplierView() {

        this.setSize(550, 200);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.getContentPane().add(new JLabel("Supplier View"));

        JPanel main = new JPanel(new SpringLayout());
        main.add(new JLabel("ID:"));
        main.add(txtSupplierID);
        main.add(new JLabel("Name:"));
        main.add(txtSupplierName);
        main.add(new JLabel("Phone:"));
        main.add(txtSupplierPhone);
        main.add(new JLabel("Address:"));
        main.add(txtSupplierAddr);

        JPanel buttons = new JPanel(new SpringLayout());
        buttons.add(btnAddSupplier);
        buttons.add(btnGetSupplier);
        buttons.add(btnUpdateSupplier);
        buttons.add(btnDeleteSupplier);

        SpringUtilities.makeCompactGrid(main,
                4 , 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        SpringUtilities.makeCompactGrid(buttons,
                1,4,6,10,6,6);
        this.getContentPane().add(main);
        this.getContentPane().add(buttons);

        this.btnAddSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtSupplierID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Supplier ID! Please provide a valid ID!");
                    return;
                }

                String phone = txtSupplierPhone.getText().trim();
                if (phone.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name! Please provide a non-empty value!");
                    return;
                }

                String name = txtSupplierName.getText().trim();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name! Please provide a non-empty value!");
                    return;
                }

                String addr = txtSupplierAddr.getText().trim();
                if (addr.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid address! Please provide a non-empty value!");
                    return;
                }

                // Done all validations! Make an object for this product!

                SupplierModel model = new SupplierModel();
                model.setID(id);
                model.setPhone(phone);
                model.setName(name);
                model.setAddr(addr);

                // Store the model to the database

                boolean res = Application.getInstance().dataAdaptSupplier.createSupplier(model);
                if (!res) {
                    JOptionPane.showMessageDialog(null, "Supplier is NOT saved!");
                } else {
                    JOptionPane.showMessageDialog(null, "Supplier is saved!");
                }

            }
        });

        this.btnGetSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtSupplierID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Job ID! Please provide a valid ID!");
                    return;
                }


                SupplierModel model = Application.getInstance().dataAdaptSupplier.readSupplier(id);

                if(model == null){
                    JOptionPane.showMessageDialog(null, "Unable to find Supplier from id!");
                }

                txtSupplierName.setText(model.getName());
                txtSupplierPhone.setText(model.getPhone());
                txtSupplierAddr.setText(model.getAddr());
            }
        });

        this.btnUpdateSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtSupplierID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Supplier ID! Please provide a valid ID!");
                    return;
                }

                String phone = txtSupplierPhone.getText().trim();
                if (phone.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name! Please provide a non-empty value!");
                    return;
                }

                String name = txtSupplierName.getText().trim();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name! Please provide a non-empty value!");
                    return;
                }

                String addr = txtSupplierAddr.getText().trim();
                if (addr.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid address! Please provide a non-empty value!");
                    return;
                }

                // Done all validations! Make an object for this product!

                SupplierModel model = new SupplierModel();
                model.setID(id);
                model.setPhone(phone);
                model.setName(name);
                model.setAddr(addr);

                // Store the model to the database

                boolean res = Application.getInstance().dataAdaptSupplier.updateSupplier(model);
                if (!res) {
                    JOptionPane.showMessageDialog(null, "Supplier is NOT updated!");
                } else {
                    JOptionPane.showMessageDialog(null, "Supplier has been updated");
                }

            }
        });

        this.btnDeleteSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtSupplierID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Supplier ID! Please provide a valid ID!");
                    return;
                }

                boolean res = Application.getInstance().dataAdaptSupplier.deleteSupplier(id);
                if (!res) {
                    JOptionPane.showMessageDialog(null, "Supplier was NOT deleted!");
                } else {
                    JOptionPane.showMessageDialog(null, "Supplier was deleted!");
                }
            }
        });
    }
}
