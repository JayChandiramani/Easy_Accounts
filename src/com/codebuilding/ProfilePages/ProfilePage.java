package com.codebuilding.ProfilePages;

import com.codebuilding.CurrentUser;
import com.codebuilding.EasyAccountsMain;
import com.codebuilding.HomePages.OfficeManagerHomePage;
import com.codebuilding.HomePages.OfficeWorkerHomePage;
import com.codebuilding.HomePages.OwnerHomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePage {
    public JPanel ProfilePanel;
    public JTextField FirstNameTextField;
    public JTextField LastNameTextField;
    public JTextField UsernameTextField;
    public JTextField EmailTextField;
    public JButton backButton;
    public JButton editProfileButton;
    public JTextField GenderTextField;
    public JTextField DateOfBirthTextField;
    public JTextField SecQ1TextField;
    public JTextField SecAns1TextField;
    public JTextField SecQ2TextField;
    public JTextField SecAns2TextField;
    public JLabel ProfilePic;
    private JFormattedTextField profileFormattedTextField;

    public ProfilePage() {

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(UsernameTextField.getText());
                CU1.GetCUInfo(UsernameTextField.getText());

                if (CU1.GetDesignation().equals("Office Worker")) {
                    OfficeWorkerHomePage OWHPObject = new OfficeWorkerHomePage();
                    OWHPObject.WelcomeUsernameLabel.setText(CU1.GetUsername());
                    OWHPObject.WelcomeFullNameLabel.setText("Welcome " + CU1.GetFName() + " " + CU1.GetLName());
                    OWHPObject.ProfilePicLabel.setIcon(new ImageIcon(CU1.GetProfilePic().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    ProfilePanel.setVisible(false);
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
                    ProfilePanel.setVisible(false);
                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(OMHPObject.OfficeManagerHomePanel);
                    EasyAccountsMain.EasyAccountsMainFrame.setSize(500,350);
                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                    EasyAccountsMain.EasyAccountsMainFrame.setResizable(false);
                }

                if (CU1.GetDesignation().equals("Owner")) {
                    OwnerHomePage OHPObject = new OwnerHomePage();
                    OHPObject.WelcomeUsernameLabel.setText(CU1.GetUsername());
                    OHPObject.WelcomeFullNameLabel.setText("Welcome " + CU1.GetFName() + " " + CU1.GetLName());
                    OHPObject.ProfilePicLabel.setIcon(new ImageIcon(CU1.GetProfilePic().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    ProfilePanel.setVisible(false);
                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(OHPObject.OwnerHomePanel);
                    EasyAccountsMain.EasyAccountsMainFrame.setSize(500,350);
                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                    EasyAccountsMain.EasyAccountsMainFrame.setResizable(false);
                }
            }
        });

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(UsernameTextField.getText());
                CU1.GetCUInfo(UsernameTextField.getText());
                ProfilePanel.setVisible(false);
                EditProfilePage EPPObject = new EditProfilePage();
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(EPPObject.EditProfilePanel);
                EasyAccountsMain.EasyAccountsMainFrame.setSize(800,650);
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);

                EPPObject.UsernameTextField.setText(CU1.GetUsername());
                EPPObject.FirstNameTextField.setText(CU1.GetFName());
                EPPObject.LastNameTextField.setText(CU1.GetLName());
                EPPObject.EmailTextField.setText(CU1.GetEmail());
                EPPObject.GenderComboBox.setSelectedItem(CU1.GetGender());
                EPPObject.DateOfBirthTextField.setText(String.valueOf(CU1.GetDOB()));
                EPPObject.SecurityQuestion1ComboBox.setSelectedItem(CU1.GetSQ1());
                EPPObject.SecurityQuestion1AnswerTextField.setText(CU1.GetSQ1A());
                EPPObject.SecurityQuestion2ComboBox.setSelectedItem(CU1.GetSQ2());
                EPPObject.SecurityQuestion2AnswerTextField.setText(CU1.GetSQ2A());
                EPPObject.PasswordTextField.setText(CU1.GetPassword());
                EPPObject.ConfirmPasswordTextField.setText(CU1.GetPassword());
                EPPObject.ProfilePic.setIcon(new ImageIcon(CU1.GetProfilePic().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
            }
        });
    }
}
