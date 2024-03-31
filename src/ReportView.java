import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportView extends JFrame{
    public JTextArea txtReport = new JTextArea(50,50);
    public JTextField txtStartDate = new JTextField();
    public JTextField txtEndDate = new JTextField();

    public JButton btnTotalSale = new JButton("Total Sales");
    public JButton btnProduct = new JButton("Total Sale by Product");
    public JButton btnCustomer = new JButton("Total Sale by Customer");
    public JButton btnSave = new JButton("Save as Text File");
    public JCheckBox chkDesc = new JCheckBox("Order as Descending");

    public ReportView(){
        this.setSize(500, 500);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().add(new JLabel("Report View"));

        JPanel main = new JPanel(new SpringLayout());
        main.add(txtReport);
        SpringUtilities.makeCompactGrid(main,
                1, 1, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        JPanel input = new JPanel(new SpringLayout());
        input.add(new JLabel("Start Date: "));
        input.add(txtStartDate);
        input.add(new JLabel("End Date: "));
        input.add(txtEndDate);
        SpringUtilities.makeCompactGrid(input,
                1, 4, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        JPanel buttons = new JPanel(new SpringLayout());
        buttons.add(btnTotalSale);
        buttons.add(btnProduct);
        buttons.add(btnCustomer);
        buttons.add(btnSave);
        buttons.add(chkDesc);
        buttons.add(new JLabel(""));
        SpringUtilities.makeCompactGrid(buttons,
                2,3, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        this.getContentPane().add(main);
        this.getContentPane().add(input);
        this.getContentPane().add(buttons);

        this.btnTotalSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sdate = txtStartDate.getText().trim();
                if (sdate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid start date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                String edate = txtEndDate.getText().trim();
                if (edate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid end date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                String s = Application.getInstance().dataAccessReport.totalSale(sdate, edate, chkDesc.isSelected());

                txtReport.setText(s);
            }
        });

        this.btnProduct.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String sdate = txtStartDate.getText().trim();
                if (sdate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid start date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                String edate = txtEndDate.getText().trim();
                if (edate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid end date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                String s = Application.getInstance().dataAccessReport.productSale(sdate, edate, chkDesc.isSelected());

                txtReport.setText(s);
            }
        });

        this.btnCustomer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String sdate = txtStartDate.getText().trim();
                if (sdate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid start date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                String edate = txtEndDate.getText().trim();
                if (edate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid end date! Please provide a non-empty value! Use the format YYYY-MM-DD");
                    return;
                }

                String s = Application.getInstance().dataAccessReport.customerSale(sdate, edate, chkDesc.isSelected());

                txtReport.setText(s);
            }
        });

        this.btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File myObj = new File("report.txt");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    } else {
                        System.out.println("File already exists.");
                    }

                    FileWriter myWriter = new FileWriter("report.txt");
                    myWriter.write(txtReport.getText());
                    myWriter.close();

                    JOptionPane.showMessageDialog(null, "Report saved!");

                } catch (IOException ex) {
                    System.out.println("An error occurred.");
                    ex.printStackTrace();
                }
            }
        });
    }

}
