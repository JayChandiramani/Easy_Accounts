package com.codebuilding.BookKeepingPages;

import com.codebuilding.CurrentUser;
import com.codebuilding.EasyAccountsMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

public class FinancialTablesPage {
    private JButton backButton;
    private JButton saveBalanceSheetAsButton;
    private JButton saveProfitAndLButton;
    public JTable BSTable;
    public JTable PNLTable;
    public JLabel Username;
    public JPanel FTablePanel;
    private JFormattedTextField financialStatementsFormattedTextField;

    public FinancialTablesPage() {
        saveBalanceSheetAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BSTable.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });

        saveProfitAndLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PNLTable.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(Username.getText());
                CU1.GetCUInfo(Username.getText());
                FTablePanel.setVisible(false);
                Bookkeepingpage BKObject = new Bookkeepingpage();
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(BKObject.mainBKPanel);
                BKObject.UsernameLabel.setText(Username.getText());
                BKObject.WPanel.setVisible(true);
                BKObject.BSPanel.setVisible(false);
                BKObject.PNLPanel.setVisible(false);
                EasyAccountsMain.EasyAccountsMainFrame.setSize(600, 350);
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
            }
        });
    }
}
