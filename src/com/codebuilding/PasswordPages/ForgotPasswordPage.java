package com.codebuilding.PasswordPages;

import com.codebuilding.CurrentUser;
import com.codebuilding.EasyAccountsMain;
import com.codebuilding.PreLogin.LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPasswordPage {
    public JPanel ForgotPasswordPanel;
    private JButton enterButton;
    private JButton cancelButton;
    private JTextField UsernameTextField;
    private JTextField EmailTextField;
    private JComboBox Sec1ComboBox;
    private JComboBox Sec2ComboBox;
    private JTextField Sec1AnswerTextField;
    private JTextField Sec2AnswerTextField;
    private JFormattedTextField FormattedTextField1;
    private JComboBox EmailDomainComboBox;
    private String Email;

    public ForgotPasswordPage() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ForgotPasswordPanel.setVisible(false);
                LoginPage LPObject = new LoginPage();
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(LPObject.LoginPanel);
            }
        });

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UsernameTextField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Please enter a valid username.", "Invalid Username", JOptionPane.WARNING_MESSAGE);
                else{
                    if (EmailTextField.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Please enter a valid email address.", "Invalid Email Address", JOptionPane.WARNING_MESSAGE);
                    else{
                        if (EmailDomainComboBox.getSelectedItem().equals("Choose an email service."))
                            JOptionPane.showMessageDialog(null, "Please enter a valid email domain.", "Invalid Email Domain", JOptionPane.WARNING_MESSAGE);
                        else{
                            if (Sec1ComboBox.getSelectedItem().equals("Choose a question here."))
                                JOptionPane.showMessageDialog(null, "Please enter a valid security question 1.", "Invalid Security Question", JOptionPane.WARNING_MESSAGE);
                            else{
                                if (Sec1AnswerTextField.getText().equals(""))
                                    JOptionPane.showMessageDialog(null, "Please enter a valid answer for Security Question 1.", "Invalid Answer", JOptionPane.WARNING_MESSAGE);
                                else{
                                    if (Sec2ComboBox.getSelectedItem().equals("Choose a question here."))
                                        JOptionPane.showMessageDialog(null, "Please enter a valid security question 2.", "Invalid Security Question", JOptionPane.WARNING_MESSAGE);
                                    else{
                                        if (Sec1ComboBox.getSelectedItem().equals(Sec2ComboBox.getSelectedItem())) {
                                            JOptionPane.showMessageDialog(null, "Please make sure that the security question are not the same.", "Invalid Security Question", JOptionPane.WARNING_MESSAGE);
                                            Sec1ComboBox.setSelectedItem("Choose a question here.");
                                            Sec2ComboBox.setSelectedItem("Choose a question here.");
                                        }
                                        else{
                                            if (Sec2AnswerTextField.getText().equals(""))
                                                JOptionPane.showMessageDialog(null, "Please enter a valid answer for Security Question 2.", "Invalid Answer", JOptionPane.WARNING_MESSAGE);
                                            else{
                                                CurrentUser CU1 = new CurrentUser(UsernameTextField.getText());
                                                CU1.GetCUInfo(UsernameTextField.getText());
                                                Email = EmailTextField.getText() + EmailDomainComboBox.getSelectedItem().toString();

                                                if (Email.equals(CU1.GetEmail())){
                                                    if (Sec1ComboBox.getSelectedItem().toString().equals(CU1.GetSQ1())){
                                                        if (Sec1AnswerTextField.getText().equals(CU1.GetSQ1A())){
                                                            if (Sec2ComboBox.getSelectedItem().toString().equals(CU1.GetSQ2())){
                                                                if (Sec2AnswerTextField.getText().equals(CU1.GetSQ2A())){
                                                                    ForgotPasswordPanel.setVisible(false);
                                                                    ResetPasswordPage RPPObject = new ResetPasswordPage();
                                                                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(RPPObject.ResetPasswordPanel);
                                                                    EasyAccountsMain.EasyAccountsMainFrame.setSize(700,500);
                                                                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                                                                    RPPObject.UsernameTextField.setText(CU1.GetUsername());

                                                                }
                                                                else
                                                                    JOptionPane.showMessageDialog(null, "Please enter the correct answer for security question 2.", "Invalid Answer", JOptionPane.WARNING_MESSAGE);
                                                            }
                                                            else
                                                                JOptionPane.showMessageDialog(null, "Please enter the correct security question 2.", "Invalid Email Address", JOptionPane.WARNING_MESSAGE);
                                                        }
                                                        else
                                                            JOptionPane.showMessageDialog(null, "Please enter the correct answer for security question 1.", "Invalid Answer", JOptionPane.WARNING_MESSAGE);
                                                    }
                                                    else
                                                        JOptionPane.showMessageDialog(null, "Please enter the correct security question 1.", "Invalid Security Question", JOptionPane.WARNING_MESSAGE);
                                                }
                                                else
                                                    JOptionPane.showMessageDialog(null, "Please enter a valid email address.", "Invalid Email Address", JOptionPane.WARNING_MESSAGE);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}