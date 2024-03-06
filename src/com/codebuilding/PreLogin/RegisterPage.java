package com.codebuilding.PreLogin;

import com.codebuilding.CurrentUser;
import com.codebuilding.EasyAccountsMain;
import com.codebuilding.NewUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage {
    public JPanel RegisterPanel;
    private JButton CancelButton;
    private JTextField EmailTextField;
    private JTextField YearTextField;
    private JTextField MonthTextField;
    private JTextField DayTextField;
    private JTextField FirstNameTextField;
    private JTextField LastNameTextField;
    private JComboBox GenderComboBox;
    private JPasswordField PasswordTextField;
    private JTextField UsernameTextField;
    private JPasswordField ConfirmPasswordTextField;
    private JButton RegisterButton;
    private JComboBox EmailDomainComboBox;
    private JComboBox SecurityQuestion1ComboBox;
    private JTextField SecurityQuestion1AnswerTextField;
    private JComboBox SecurityQuestion2ComboBox;
    private JTextField SecurityQuestion2AnswerTextField;
    private JComboBox DesignationComboBox;
    private JFormattedTextField registrationFormFormattedTextField;
    private String Email,DateString;

    public RegisterPage() {

        EasyAccountsMain.EasyAccountsMainFrame.setSize(700, 500);
        EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);

        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterPanel.setVisible(false);
                LoginPage LPObject = new LoginPage();
                EasyAccountsMain.EasyAccountsMainFrame.setSize(700,500);
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(LPObject.LoginPanel);
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
            }
        });

        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (FirstNameTextField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Invalid First Name.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                else {
                    if (LastNameTextField.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Invalid Last Name.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                    else {
                        if (UsernameTextField.getText().equals(""))
                            JOptionPane.showMessageDialog(null, "Invalid username.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                        else {
                            if (GenderComboBox.getSelectedItem().toString().equals("Please choose your gender."))
                                JOptionPane.showMessageDialog(null, "Please select your gender.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                            else {
                                if (DesignationComboBox.getSelectedItem().toString().equals("Please Choose Your Designation"))
                                    JOptionPane.showMessageDialog(null, "Please select your designation.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                                else {
                                    if (YearTextField.getText().equals("") || YearTextField.getText().length() > 4 || YearTextField.getText().length() < 4)
                                        JOptionPane.showMessageDialog(null, "Invalid Date of Birth. Please check that the date is in the format 'yyyy-MM-dd'", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                                    else {
                                        if (MonthTextField.getText().equals("") || MonthTextField.getText().length() > 2 || MonthTextField.getText().length() < 0 || Integer.parseInt(MonthTextField.getText()) > 12 || Integer.parseInt(MonthTextField.getText()) < 0)
                                            JOptionPane.showMessageDialog(null, "Invalid Date of Birth. Please check that the date is in the format 'yyyy-MM-dd'", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                                        else {
                                            if (DayTextField.getText().equals("") || DayTextField.getText().length() > 2 || DayTextField.getText().length() < 0 || Integer.parseInt(DayTextField.getText()) > 31 || Integer.parseInt(DayTextField.getText()) < 0)
                                                JOptionPane.showMessageDialog(null, "Invalid Date of Birth. Please check that the date is in the format 'yyyy-MM-dd'", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                                            else {
                                                if (EmailTextField.getText().equals(""))
                                                    JOptionPane.showMessageDialog(null, "Invalid Email Address.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                                                else {
                                                    if (EmailDomainComboBox.getSelectedItem().equals("Choose an email service."))
                                                        JOptionPane.showMessageDialog(null, "Please enter a valid email domain.", "Invalid Email Domain", JOptionPane.WARNING_MESSAGE);
                                                    else {
                                                        if (PasswordTextField.getText().equals(""))
                                                            JOptionPane.showMessageDialog(null, "Invalid Password.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                                                        else {
                                                            if (checkPasswordStrength(PasswordTextField.getText()) < 100)
                                                                JOptionPane.showMessageDialog(null, "Please ensure that your password has the following \n --> At least one upper case and lower case letter. \n --> At least one number and special character. \n --> Ensure that the password is at least 7 characters long.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                                                            else {
                                                                if (ConfirmPasswordTextField.getText().equals(""))
                                                                    JOptionPane.showMessageDialog(null, "Please re-enter the same password.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                                                                else {
                                                                    if (PasswordTextField.getText().equals(ConfirmPasswordTextField.getText())) {
                                                                        if (SecurityQuestion1ComboBox.getSelectedItem().equals("Choose a question here."))
                                                                            JOptionPane.showMessageDialog(null, "Please enter a valid security question 1.", "Invalid Security Question", JOptionPane.WARNING_MESSAGE);
                                                                        else {
                                                                            if (SecurityQuestion1AnswerTextField.getText().equals(""))
                                                                                JOptionPane.showMessageDialog(null, "Please enter a valid answer for Security Question 1.", "Invalid Answer", JOptionPane.WARNING_MESSAGE);
                                                                            else {
                                                                                if (SecurityQuestion2ComboBox.getSelectedItem().equals("Choose a question here."))
                                                                                    JOptionPane.showMessageDialog(null, "Please enter a valid security question 2.", "Invalid Security Question", JOptionPane.WARNING_MESSAGE);
                                                                                else {
                                                                                    if (SecurityQuestion1ComboBox.getSelectedItem().equals(SecurityQuestion2ComboBox.getSelectedItem())) {
                                                                                        JOptionPane.showMessageDialog(null, "Please make sure that the security question are not the same.", "Invalid Security Question", JOptionPane.WARNING_MESSAGE);
                                                                                        SecurityQuestion1ComboBox.setSelectedItem("Choose a question here.");
                                                                                        SecurityQuestion2ComboBox.setSelectedItem("Choose a question here.");
                                                                                    } else {
                                                                                        if (SecurityQuestion2AnswerTextField.getText().equals(""))
                                                                                            JOptionPane.showMessageDialog(null, "Please enter a valid answer for Security Question 2.", "Invalid Answer", JOptionPane.WARNING_MESSAGE);
                                                                                        else {
                                                                                            DateString = YearTextField.getText() + "-" + MonthTextField.getText() + "-" + DayTextField.getText();
                                                                                            Email = EmailTextField.getText() + EmailDomainComboBox.getSelectedItem().toString();
                                                                                            java.sql.Date DateOfBirth = java.sql.Date.valueOf(DateString);

                                                                                            NewUser NU1 = new NewUser(UsernameTextField.getText());
                                                                                            NU1.SetNUInfo(UsernameTextField.getText(), FirstNameTextField.getText(), LastNameTextField.getText(), PasswordTextField.getText(), GenderComboBox.getSelectedItem().toString(), Email, SecurityQuestion1ComboBox.getSelectedItem().toString(), SecurityQuestion1AnswerTextField.getText(), SecurityQuestion2ComboBox.getSelectedItem().toString(), SecurityQuestion2AnswerTextField.getText(), DesignationComboBox.getSelectedItem().toString(), DateOfBirth);
                                                                                            NU1.SetTables(UsernameTextField.getText());

                                                                                            JOptionPane.showMessageDialog(null,"Registration Complete.", "Process Complete", JOptionPane.INFORMATION_MESSAGE);
                                                                                            RegisterPanel.setVisible(false);
                                                                                            LoginPage LPObject = new LoginPage();
                                                                                            EasyAccountsMain.EasyAccountsMainFrame.setContentPane(LPObject.LoginPanel);
                                                                                            EasyAccountsMain.EasyAccountsMainFrame.setSize(700, 500);
                                                                                            EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(null, "The passwords which you have entered do not match.", "Invalid Password", JOptionPane.WARNING_MESSAGE);
                                                                        PasswordTextField.setText("");
                                                                        ConfirmPasswordTextField.setText("");
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
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

    private int checkPasswordStrength(String password) {
        int strengthPercentage=0;
        String[] partialRegexChecks = { ".*[a-z]+.*", // lower
                ".*[A-Z]+.*", // upper
                ".*[\\d]+.*", // digits
                ".*[@#$%]+.*" // symbols
        };

        if (password.matches(partialRegexChecks[0])) {
            strengthPercentage+=25;
        }
        if (password.matches(partialRegexChecks[1])) {
            strengthPercentage+=25;
        }
        if (password.matches(partialRegexChecks[2])) {
            strengthPercentage+=25;
        }
        if (password.matches(partialRegexChecks[3])) {
            strengthPercentage+=24;
        }
        if (password.length() >= 7) {
            strengthPercentage+=1;
        }

        return strengthPercentage;
    }
}