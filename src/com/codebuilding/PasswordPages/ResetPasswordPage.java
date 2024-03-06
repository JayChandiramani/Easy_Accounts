package com.codebuilding.PasswordPages;

import com.codebuilding.CurrentUser;
import com.codebuilding.EasyAccountsMain;
import com.codebuilding.PreLogin.LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetPasswordPage {
    public JPanel ResetPasswordPanel;
    public JTextField UsernameTextField;
    private JPasswordField NewPasswordTextField;
    private JPasswordField ConfirmPasswordTextField;
    private JButton cancelButton;
    private JButton resetPasswordButton;
    private JFormattedTextField resetPasswordFormattedTextField;

    public ResetPasswordPage() {

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetPasswordPanel.setVisible(false);
                LoginPage LPObject = new LoginPage();
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(LPObject.LoginPanel);
            }
        });

        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (NewPasswordTextField.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Please fill in a new password.","Invalid Entry",JOptionPane.WARNING_MESSAGE);
                else {
                    if (checkPasswordStrength(NewPasswordTextField.getText()) < 100)
                        JOptionPane.showMessageDialog(null, "Please ensure that your password has the following \n --> At least one upper case and lower case letter. \n --> At least one number and special character. \n --> Ensure that the password is at least 7 characters long.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                    else {
                        if (ConfirmPasswordTextField.getText().equals(""))
                            JOptionPane.showMessageDialog(null, "Please confirm your password.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                        else {
                            if (!(NewPasswordTextField.getText().equals(ConfirmPasswordTextField.getText()))) {
                                JOptionPane.showMessageDialog(null, "The entered passwords do not match.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                                NewPasswordTextField.setText("");
                                ConfirmPasswordTextField.setText("");
                            } else {
                                if (JOptionPane.showConfirmDialog(null, "Are you sure that you wish to reset your password?", "Confirmation", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION){
                                    CurrentUser CU1 = new CurrentUser(UsernameTextField.getText());
                                    CU1.SetNewPassword(CU1.GetUsername(), NewPasswordTextField.getText());

                                    ResetPasswordPanel.setVisible(false);
                                    LoginPage LPObject = new LoginPage();
                                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(LPObject.LoginPanel);
                                } else{
                                    ResetPasswordPanel.setVisible(false);
                                    LoginPage LPObject = new LoginPage();
                                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(LPObject.LoginPanel);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private int checkPasswordStrength(String password) {
        int strengthPercentage=0;
        String[] partialRegexChecks = { ".*[a-z]+.*", // lowercase
                ".*[A-Z]+.*", // uppercase
                ".*[\\d]+.*", // digits
                ".*[@#$%]+.*" // symbols
        };


        if (password.matches(partialRegexChecks[0])) { //has lowercase
            strengthPercentage+=25;
        }
        if (password.matches(partialRegexChecks[1])) { //has uppercase
            strengthPercentage+=25;
        }
        if (password.matches(partialRegexChecks[2])) { //has digits
            strengthPercentage+=25;
        }
        if (password.matches(partialRegexChecks[3])) { //has symbols
            strengthPercentage+=24;
        }
        if (password.length() >= 7) { //has or is longer than 7 characters
            strengthPercentage+=1;
        }

        return strengthPercentage;
    }
}