package com.codebuilding.EmployeePages;

import com.codebuilding.CurrentUser;
import com.codebuilding.EasyAccountsMain;
import com.codebuilding.HomePages.OfficeManagerHomePage;
import com.codebuilding.HomePages.OfficeWorkerHomePage;
import com.codebuilding.HomePages.OwnerHomePage;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

public class EmployeePage {
    public JLabel usernameLabel;
    private JFormattedTextField employeesFormattedTextField;
    public JTable Employees;
    private JButton backButton;
    private JButton removeEmployeeButton;
    private JButton saveAsPDFButton;
    public JPanel EmployeePanel;

    public EmployeePage(){

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(usernameLabel.getText());
                CU1.GetCUInfo(usernameLabel.getText());

                if (CU1.GetDesignation().equals("Owner")) {
                    OwnerHomePage OHPObject = new OwnerHomePage();
                    OHPObject.WelcomeUsernameLabel.setText(CU1.GetUsername());
                    OHPObject.WelcomeFullNameLabel.setText("Welcome " + CU1.GetFName() + " " + CU1.GetLName());
                    OHPObject.ProfilePicLabel.setIcon(new ImageIcon(CU1.GetProfilePic().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    EmployeePanel.setVisible(false);
                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(OHPObject.OwnerHomePanel);
                    EasyAccountsMain.EasyAccountsMainFrame.setSize(500,350);
                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                    EasyAccountsMain.EasyAccountsMainFrame.setResizable(false);
                }
            }
        });

        removeEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(usernameLabel.getText());
                CU1.GetCUInfo(usernameLabel.getText());
                int selectedRow = Employees.getSelectedRow();
                if (selectedRow>=0 && JOptionPane.showConfirmDialog(null, "Are you sure that you wish to delete this employee?", "Confirmation", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION){
                    TableModel employees = CU1.RemoveEmployee(selectedRow);
                    Employees.setModel(employees);
                } else {
                    JOptionPane.showMessageDialog(null, "Please Select a Row to Delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveAsPDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Employees.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
