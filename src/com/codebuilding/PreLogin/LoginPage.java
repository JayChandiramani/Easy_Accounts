package com.codebuilding.PreLogin;

import com.codebuilding.CurrentUser;
import com.codebuilding.EasyAccountsMain;
import com.codebuilding.HomePages.*;
import com.codebuilding.PasswordPages.ForgotPasswordPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    public JPanel LoginPanel;
    private JButton RegisterButton;
    private JButton LoginButton;
    private JTextField UsernameTextField;
    private JPasswordField PasswordTextField;
    private JButton forgotPasswordButton;

    public LoginPage() {

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UsernameTextField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Please enter a valid username.", "Invalid Username", JOptionPane.WARNING_MESSAGE);
                else {
                    if (PasswordTextField.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Please enter a valid password.", "Invalid Password", JOptionPane.WARNING_MESSAGE);
                    else {
                        CurrentUser CU1 = new CurrentUser(UsernameTextField.getText());
                        CU1.GetCUInfo(UsernameTextField.getText());
                        if (CU1.GetUsername() != null) {
                            if (PasswordTextField.getText().equals(CU1.GetPassword())) {

                                if (CU1.GetDesignation().equals("Office Worker")) {
                                    OfficeWorkerHomePage OWHPObject = new OfficeWorkerHomePage();
                                    OWHPObject.WelcomeUsernameLabel.setText(CU1.GetUsername());
                                    OWHPObject.WelcomeFullNameLabel.setText("Welcome " + CU1.GetFName() + " " + CU1.GetLName());
                                    OWHPObject.ProfilePicLabel.setIcon(new ImageIcon(CU1.GetProfilePic().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                                    LoginPanel.setVisible(false);
                                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(OWHPObject.OfficeWorkerHomePanel);
                                    EasyAccountsMain.EasyAccountsMainFrame.setResizable(false);
                                    EasyAccountsMain.EasyAccountsMainFrame.setSize(500, 350);
                                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                                }

                                if (CU1.GetDesignation().equals("Office Manager")) {
                                    OfficeManagerHomePage OMHPObject = new OfficeManagerHomePage();
                                    OMHPObject.WelcomeUsernameLabel.setText(CU1.GetUsername());
                                    OMHPObject.WelcomeFullNameLabel.setText("Welcome " + CU1.GetFName() + " " + CU1.GetLName());
                                    OMHPObject.ProfilePicLabel.setIcon(new ImageIcon(CU1.GetProfilePic().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                                    LoginPanel.setVisible(false);
                                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(OMHPObject.OfficeManagerHomePanel);
                                    EasyAccountsMain.EasyAccountsMainFrame.setResizable(false);
                                    EasyAccountsMain.EasyAccountsMainFrame.setSize(500, 350);
                                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                                }

                                if (CU1.GetDesignation().equals("Owner")){
                                    OwnerHomePage OHPObject = new OwnerHomePage();
                                    OHPObject.WelcomeUsernameLabel.setText(CU1.GetUsername());
                                    OHPObject.WelcomeFullNameLabel.setText("Welcome " + CU1.GetFName() + " " + CU1.GetLName());
                                    OHPObject.ProfilePicLabel.setIcon(new ImageIcon(CU1.GetProfilePic().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                                    LoginPanel.setVisible(false);
                                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(OHPObject.OwnerHomePanel);
                                    EasyAccountsMain.EasyAccountsMainFrame.setResizable(false);
                                    EasyAccountsMain.EasyAccountsMainFrame.setSize(500, 350);
                                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                                }

                            /*if (CU1.GetDesignation().equals("Cashier")){
                                LoginPanel.setVisible(false);
                                CashierHomePage CHPObject = new CashierHomePage();
                                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(CHPObject.CashierHomePanel);
                                CHPObject.CU1 = CU1;}

                            if (CU1.GetDesignation().equals("Sales Person")){
                                LoginPanel.setVisible(false);
                                SalesPersonHomePage SPHPObject = new SalesPersonHomePage();
                                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(SPHPObject.SalesPersonHomePanel);
                                SPHPObject.CU1 = CU1;}

                            if (CU1.GetDesignation().equals("Floor Manager")){
                                LoginPanel.setVisible(false);
                                FloorManagerHomePage FMHPObject = new FloorManagerHomePage();
                                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(FMHPObject.FloorManagerHomePanel);
                                FMHPObject.CU1 = CU1;}*/

                                } else {
                                    JOptionPane.showMessageDialog(null, "Incorrect Password Entered", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                                    PasswordTextField.setText("");
                                }
                            }
                        }
                    }
                }
            });

        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel.setVisible(false);
                RegisterPage RPObject = new RegisterPage();
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(RPObject.RegisterPanel);
            }
        });

        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel.setVisible(false);
                ForgotPasswordPage FPPObject = new ForgotPasswordPage();
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(FPPObject.ForgotPasswordPanel);
            }
        });
    }
}