import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProductView extends JFrame {
    public JTextField txtProductID = new JTextField(10);
    public JTextField txtProductName = new JTextField(300);
    public JTextField txtProductPrice = new JTextField(10);
    public JTextField txtProductQty = new JTextField(10);

    public JButton btnAddProduct = new JButton("Add Product");
    public JButton btnGetProduct = new JButton("Search for Product");
    public JButton btnUpdateProduct = new JButton("Update Product");
    public JButton btnDeleteProduct = new JButton("Delete Product");

    public ProductView() {

        this.setSize(530, 200);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.getContentPane().add(new JLabel("Product View"));

        JPanel main = new JPanel(new SpringLayout());
        main.add(new JLabel("ID:"));
        main.add(txtProductID);
        main.add(new JLabel("Name:"));
        main.add(txtProductName);
        main.add(new JLabel("Price:"));
        main.add(txtProductPrice);
        main.add(new JLabel("Qty:"));
        main.add(txtProductQty);

        JPanel buttons = new JPanel(new SpringLayout());
        buttons.add(btnAddProduct);
        buttons.add(btnGetProduct);
        buttons.add(btnUpdateProduct);
        buttons.add(btnDeleteProduct);

        SpringUtilities.makeCompactGrid(main,
                4, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        SpringUtilities.makeCompactGrid(buttons,
                1,4,6,10,6,6);
        this.getContentPane().add(main);
        this.getContentPane().add(buttons);

        this.btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }

                double price;
                try {
                    price = Double.parseDouble(txtProductPrice.getText());

                    if (price < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid price! It should be positive!");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format for price! Please provide a valid number!");
                    return;
                }

                String name = txtProductName.getText().trim();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid product title! Please provide a non-empty value!");
                    return;
                }

                int qty;
                try {
                    qty = Integer.parseInt(txtProductQty.getText());

                    if (qty < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity! It should be positive!");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity! Please provide a valid value!");
                    return;
                }

                // Done all validations! Make an object for this product!

                ProductModel model = new ProductModel();
                model.setID(id);
                model.setPrice(price);
                model.setName(name);
                model.setQty(qty);

                // Store the model to the database

                boolean res = Application.getInstance().dataAdaptProduct.createProduct(model);
                if (!res) {
                    JOptionPane.showMessageDialog(null, "Product is NOT saved!");
                } else {
                    JOptionPane.showMessageDialog(null, "Product is saved!");
                }

            }
        });

        this.btnGetProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }


                ProductModel model = Application.getInstance().dataAdaptProduct.readProduct(id);

                if(model == null){
                    JOptionPane.showMessageDialog(null, "Unable to find product from id!");
                }

                txtProductName.setText(model.getName());
                txtProductPrice.setText(Double.toString(model.getPrice()));
                txtProductName.setText(model.getName());
                txtProductQty.setText(Integer.toString(model.getQty()));
            }
        });

        this.btnUpdateProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }

                double price;
                try {
                    price = Double.parseDouble(txtProductPrice.getText());

                    if (price < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid price! It should be positive!");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format for price! Please provide a valid number!");
                    return;
                }

                String name = txtProductName.getText().trim();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid product title! Please provide a non-empty value!");
                    return;
                }

                int qty;
                try {
                    qty = Integer.parseInt(txtProductQty.getText());

                    if (qty < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity! It should be positive!");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity! Please provide a valid value!");
                    return;
                }

                // Done all validations! Make an object for this product!

                ProductModel model = new ProductModel();
                model.setID(id);
                model.setPrice(price);
                model.setName(name);
                model.setQty(qty);

                // Store the model to the database

                boolean res = Application.getInstance().dataAdaptProduct.updateProduct(model);
                if (!res) {
                    JOptionPane.showMessageDialog(null, "Product is NOT updated!");
                } else {
                    JOptionPane.showMessageDialog(null, "Product has been updated");
                }

            }
        });

        this.btnDeleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(txtProductID.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID! Please provide a valid ID!");
                    return;
                }

                boolean res = Application.getInstance().dataAdaptProduct.deleteProduct(id);
                if (!res) {
                    JOptionPane.showMessageDialog(null, "Product was NOT deleted!");
                } else {
                    JOptionPane.showMessageDialog(null, "Product was deleted!");
                }
            }
        });
    }
}
