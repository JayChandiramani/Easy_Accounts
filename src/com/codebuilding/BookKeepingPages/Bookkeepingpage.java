package com.codebuilding.BookKeepingPages;

import com.codebuilding.CurrentUser;
import com.codebuilding.EasyAccountsMain;
import com.codebuilding.HomePages.OfficeManagerHomePage;
import com.codebuilding.HomePages.OfficeWorkerHomePage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Bookkeepingpage {
    public JPanel mainBKPanel;
    private JPanel buttonPanel;
    private JButton manageBalanceSheetButton;
    private JButton backButton;
    private JButton manageProfitAndLossButton;
    public JLabel UsernameLabel;
    private JFormattedTextField welcomeToBookKeepingFormattedTextField;
    private JTable BSCATable;
    private JTable PNLRTable;
    public JButton saveButton;
    public JButton financialTablesButton;
    public JScrollPane BSCAPanel;
    public JScrollPane PNLRPanel;
    private JTable BSFATable;
    private JTable BSCLTable;
    private JTable BSLTLTable;
    private JTable BSETable;
    private JTable PNLExpTable;
    private JTable PNLCOGSTable;
    private JScrollPane BSFAPanel;
    private JScrollPane BSCLPanel;
    private JScrollPane BSLTLPanel;
    private JScrollPane BSEPanel;
    public JPanel BSPanel;
    public JPanel PNLPanel;
    private JScrollPane PNLCOGSPanel;
    private JScrollPane PNLExpPanel;
    public JPanel WPanel;
    private JFormattedTextField bookKeepingFormattedTextField;

    public Bookkeepingpage() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(UsernameLabel.getText());
                CU1.GetCUInfo(UsernameLabel.getText());

                if (CU1.GetDesignation().equals("Office Worker")) {
                    OfficeWorkerHomePage OWHPObject = new OfficeWorkerHomePage();
                    OWHPObject.WelcomeUsernameLabel.setText(CU1.GetUsername());
                    OWHPObject.WelcomeFullNameLabel.setText("Welcome " + CU1.GetFName() + " " + CU1.GetLName());
                    OWHPObject.ProfilePicLabel.setIcon(new ImageIcon(CU1.GetProfilePic().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    mainBKPanel.setVisible(false);
                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(OWHPObject.OfficeWorkerHomePanel);
                    EasyAccountsMain.EasyAccountsMainFrame.setSize(500,350);
                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                    EasyAccountsMain.EasyAccountsMainFrame.setResizable(false);
                }

                if (CU1.GetDesignation().equals("Office Manager")) {
                    OfficeManagerHomePage OMHPObject = new OfficeManagerHomePage();
                    OMHPObject.WelcomeUsernameLabel.setText(CU1.GetUsername());
                    OMHPObject.WelcomeFullNameLabel.setText("Welcome " + CU1.GetFName() + " " + CU1.GetLName());
                    OMHPObject.ProfilePicLabel.setIcon(new ImageIcon(CU1.GetProfilePic().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    mainBKPanel.setVisible(false);
                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(OMHPObject.OfficeManagerHomePanel);
                    EasyAccountsMain.EasyAccountsMainFrame.setSize(500,350);
                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                    EasyAccountsMain.EasyAccountsMainFrame.setResizable(false);
                }
            }
        });

        manageBalanceSheetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(UsernameLabel.getText());
                CU1.GetCUInfo(UsernameLabel.getText());

                EasyAccountsMain.EasyAccountsMainFrame.setSize(700,650);
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);

                saveButton.setEnabled(true);
                financialTablesButton.setEnabled(true);
                WPanel.setVisible(false);
                PNLPanel.setVisible(false);
                BSPanel.setVisible(true);

                BSCATable.setModel(CU1.GetTable(UsernameLabel.getText(), "Current Assets", "Balance Sheet"));
                BSFATable.setModel(CU1.GetTable(UsernameLabel.getText(), "Fixed Assets", "Balance Sheet"));
                BSCLTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Current Liabilities", "Balance Sheet"));
                BSLTLTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Long Term Liabilities", "Balance Sheet"));
                BSETable.setModel(CU1.GetTable(UsernameLabel.getText(), "Equity", "Balance Sheet"));
            }
        });

        manageProfitAndLossButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(UsernameLabel.getText());
                CU1.GetCUInfo(UsernameLabel.getText()); //create instance of current user

                EasyAccountsMain.EasyAccountsMainFrame.setSize(700,650); //resize JFrame
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null); //set location of JFrame to center

                saveButton.setEnabled(true); //enable button
                financialTablesButton.setEnabled(true); //enable button
                WPanel.setVisible(false); //set panel invisible
                BSPanel.setVisible(false); //set panel invisible
                PNLPanel.setVisible(true); //set panel visible

                PNLRTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Revenue",
                        "Profit and Loss Statement")); //put data from database in table
                PNLCOGSTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Cost of Goods Sold",
                        "Profit and Loss Statement")); //put data from database in table
                PNLExpTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Expenses",
                        "Profit and Loss Statement")); //put data from database in table
            }
        });

        financialTablesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(UsernameLabel.getText());
                CU1.GetCUInfo(UsernameLabel.getText());

                mainBKPanel.setVisible(false);
                FinancialTablesPage FTPObject = new FinancialTablesPage();
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(FTPObject.FTablePanel);
                EasyAccountsMain.EasyAccountsMainFrame.setSize(700, 500);
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                FTPObject.FTablePanel.setVisible(true);
                FTPObject.Username.setText(UsernameLabel.getText());

                FTPObject.BSTable.setModel(CU1.GetBS(UsernameLabel.getText()));
                FTPObject.PNLTable.setModel(CU1.GetPNL(UsernameLabel.getText()));
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(UsernameLabel.getText());
                CU1.GetCUInfo(UsernameLabel.getText());

                if(BSPanel.isVisible()) {

                    if (JOptionPane.showConfirmDialog(null, "Are you sure that you want to save changes?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        CU1.UpdateTable(UsernameLabel.getText(), BSCATable.getModel(), "Current Assets", "Balance Sheet");
                        BSCATable.setModel(CU1.GetTable(UsernameLabel.getText(), "Current Assets", "Balance Sheet"));

                        CU1.UpdateTable(UsernameLabel.getText(), BSFATable.getModel(), "Fixed Assets", "Balance Sheet");
                        BSFATable.setModel(CU1.GetTable(UsernameLabel.getText(), "Fixed Assets", "Balance Sheet"));

                        CU1.UpdateTable(UsernameLabel.getText(), BSCLTable.getModel(), "Current Liabilities", "Balance Sheet");
                        BSCLTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Current Liabilities", "Balance Sheet"));

                        CU1.UpdateTable(UsernameLabel.getText(), BSLTLTable.getModel(), "Long Term Liabilities", "Balance Sheet");
                        BSLTLTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Long Term Liabilities", "Balance Sheet"));

                        CU1.UpdateTable(UsernameLabel.getText(), BSETable.getModel(), "Equity", "Balance Sheet");
                        BSETable.setModel(CU1.GetTable(UsernameLabel.getText(), "Equity", "Balance Sheet"));
                    } else {
                        BSCATable.setModel(CU1.GetTable(UsernameLabel.getText(), "Current Assets", "Balance Sheet"));
                        BSFATable.setModel(CU1.GetTable(UsernameLabel.getText(), "Fixed Assets", "Balance Sheet"));
                        BSCLTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Current Liabilities", "Balance Sheet"));
                        BSLTLTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Long Term Liabilities", "Balance Sheet"));
                        BSETable.setModel(CU1.GetTable(UsernameLabel.getText(), "Equity", "Balance Sheet"));
                    }
                }

                if(PNLPanel.isVisible()){
                    if (JOptionPane.showConfirmDialog(null, "Are you sure that you want to save changes?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        CU1.UpdateTable(UsernameLabel.getText(), PNLRTable.getModel(), "Revenue", "Profit and Loss Statement");
                        PNLRTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Revenue", "Profit and Loss Statement"));

                        CU1.UpdateTable(UsernameLabel.getText(), PNLCOGSTable.getModel(), "Cost of Goods Sold", "Profit and Loss Statement");
                        PNLCOGSTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Cost of Goods Sold", "Profit and Loss Statement"));

                        CU1.UpdateTable(UsernameLabel.getText(), PNLExpTable.getModel(), "Expenses", "Profit and Loss Statement");
                        PNLExpTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Expenses", "Profit and Loss Statement"));
                    }else{
                        PNLRTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Revenue", "Profit and Loss Statement"));
                        PNLCOGSTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Cost of Goods Sold", "Profit and Loss Statement"));
                        PNLExpTable.setModel(CU1.GetTable(UsernameLabel.getText(), "Expenses", "Profit and Loss Statement"));
                    }
                }
            }
        });

        BSCATable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                BSCATable.addKeyListener(new KeyAdapter()
                {
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) BSCATable.getModel();
                            Vector Rowdata = null;
                            dtm.addRow(Rowdata);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) BSCATable.getModel();
                            dtm.removeRow(BSCATable.getSelectedRow());
                        }
                    }
                });
            }
        });

        BSFATable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                BSFATable.addKeyListener(new KeyAdapter()
                {
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) BSFATable.getModel();
                            Vector Rowdata = null;
                            dtm.addRow(Rowdata);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) BSFATable.getModel();
                            dtm.removeRow(BSFATable.getSelectedRow());
                        }
                    }
                });
            }
        });

        BSCLTable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) { //checks for focus on JTable
                super.focusGained(e);
                BSCLTable.addKeyListener(new KeyAdapter()
                {
                    public void keyPressed(KeyEvent e)
                    { // checks which key is clicked
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) //adds row if enter is clicked
                        {
                            DefaultTableModel dtm = (DefaultTableModel) BSCLTable.getModel();
                            Vector Rowdata = null;
                            dtm.addRow(Rowdata);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) //removes row if backspace/delete is clicked.
                        {
                            DefaultTableModel dtm = (DefaultTableModel) BSCLTable.getModel();
                            dtm.removeRow(BSCLTable.getSelectedRow());
                        }
                    }
                });
            }
        });

        BSLTLTable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                BSLTLTable.addKeyListener(new KeyAdapter()
                {
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) BSLTLTable.getModel();
                            Vector Rowdata = null;
                            dtm.addRow(Rowdata);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) BSLTLTable.getModel();
                            dtm.removeRow(BSLTLTable.getSelectedRow());
                        }
                    }
                });
            }
        });

        BSETable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                BSETable.addKeyListener(new KeyAdapter()
                {
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) BSETable.getModel();
                            Vector Rowdata = null;
                            dtm.addRow(Rowdata);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) BSETable.getModel();
                            dtm.removeRow(BSETable.getSelectedRow());
                        }
                    }
                });
            }
        });

        PNLRTable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                PNLRTable.addKeyListener(new KeyAdapter()
                {
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) PNLRTable.getModel();
                            Vector Rowdata = null;
                            dtm.addRow(Rowdata);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) PNLRTable.getModel();
                            dtm.removeRow(PNLRTable.getSelectedRow());
                        }
                    }
                });
            }
        });

        PNLCOGSTable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                PNLCOGSTable.addKeyListener(new KeyAdapter()
                {
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) PNLCOGSTable.getModel();
                            Vector Rowdata = null;
                            dtm.addRow(Rowdata);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) PNLCOGSTable.getModel();
                            dtm.removeRow(PNLCOGSTable.getSelectedRow());
                        }
                    }
                });
            }
        });

        PNLExpTable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                PNLExpTable.addKeyListener(new KeyAdapter()
                {
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) PNLExpTable.getModel();
                            Vector Rowdata = null;
                            dtm.addRow(Rowdata);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        {
                            DefaultTableModel dtm = (DefaultTableModel) PNLExpTable.getModel();
                            dtm.removeRow(PNLExpTable.getSelectedRow());
                        }
                    }
                });
            }
        });
    }
}