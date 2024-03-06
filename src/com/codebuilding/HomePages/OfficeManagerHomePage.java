package com.codebuilding.HomePages;

import com.codebuilding.BookKeepingPages.Bookkeepingpage;
import com.codebuilding.CalendarPages.Calendar;
import com.codebuilding.CurrentUser;
import com.codebuilding.EasyAccountsMain;
import com.codebuilding.PreLogin.LoginPage;
import com.codebuilding.ProfilePages.ProfilePage;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OfficeManagerHomePage extends LoginPage {
    public JPanel OfficeManagerHomePanel;
    public JLabel ProfilePicLabel;
    public JLabel WelcomeFullNameLabel;
    public JLabel WelcomeUsernameLabel;
    private JButton BookKeepingButton;
    private JButton CalendarButton;
    private JButton ProfileButton;
    private JButton logOutButton;

    public OfficeManagerHomePage() {

        ProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(WelcomeUsernameLabel.getText());
                CU1.GetCUInfo(WelcomeUsernameLabel.getText());
                OfficeManagerHomePanel.setVisible(false);
                ProfilePage PPObject = new ProfilePage();
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(PPObject.ProfilePanel);
                EasyAccountsMain.EasyAccountsMainFrame.setSize(700, 400);
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);

                PPObject.UsernameTextField.setText(CU1.GetUsername());
                PPObject.FirstNameTextField.setText(CU1.GetFName());
                PPObject.LastNameTextField.setText(CU1.GetLName());
                PPObject.EmailTextField.setText(CU1.GetEmail());
                PPObject.GenderTextField.setText(CU1.GetGender());
                PPObject.DateOfBirthTextField.setText(String.valueOf(CU1.GetDOB()));
                PPObject.SecQ1TextField.setText(CU1.GetSQ1());
                PPObject.SecAns1TextField.setText(CU1.GetSQ1A());
                PPObject.SecQ2TextField.setText(CU1.GetSQ2());
                PPObject.SecAns2TextField.setText(CU1.GetSQ2A());

                PPObject.ProfilePic.setIcon(new ImageIcon(CU1.GetProfilePic().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
            }
        });

        CalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(WelcomeUsernameLabel.getText());
                CU1.GetCUInfo(WelcomeUsernameLabel.getText());
                OfficeManagerHomePanel.setVisible(false);
                Calendar CObject = new Calendar();
                EasyAccountsMain.EasyAccountsMainFrame.setSize(500, 350);
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(CObject.CalendarPanel);
                CObject.usernameLabel.setText(WelcomeUsernameLabel.getText());
                TableModel events = CU1.GetEvents(WelcomeUsernameLabel.getText());
                CObject.Events.setModel(events);
            }
        });

        BookKeepingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(WelcomeUsernameLabel.getText());
                CU1.GetCUInfo(WelcomeUsernameLabel.getText());
                OfficeManagerHomePanel.setVisible(false);
                Bookkeepingpage BKObject = new Bookkeepingpage();
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(BKObject.mainBKPanel);
                BKObject.UsernameLabel.setText(WelcomeUsernameLabel.getText());
                BKObject.WPanel.setVisible(true);
                BKObject.BSPanel.setVisible(false);
                BKObject.PNLPanel.setVisible(false);
                EasyAccountsMain.EasyAccountsMainFrame.setSize(600, 350);
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( JOptionPane.showConfirmDialog(null, "Are you sure that you wish to log out?", "Confirmation", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
                    OfficeManagerHomePanel.setVisible(false);
                    LoginPage LObject = new LoginPage();
                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(LObject.LoginPanel);
                    LObject.LoginPanel.setVisible(true);
                    EasyAccountsMain.EasyAccountsMainFrame.setSize(700, 500);
                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                }
            }
        });
    }
}
