package com.codebuilding.ProfilePages;

import com.codebuilding.CurrentUser;
import com.codebuilding.EasyAccountsMain;
import com.codebuilding.PreLogin.LoginPage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EditProfilePage extends LoginPage {
    private JButton CancelButton;
    public JTextField EmailTextField;
    public JTextField DateOfBirthTextField;
    public JTextField FirstNameTextField;
    public JTextField LastNameTextField;
    public JPasswordField PasswordTextField;
    public JPasswordField ConfirmPasswordTextField;
    private JButton SaveProfileButton;
    public JComboBox SecurityQuestion1ComboBox;
    public JTextField SecurityQuestion1AnswerTextField;
    public JComboBox SecurityQuestion2ComboBox;
    public JTextField SecurityQuestion2AnswerTextField;
    public JComboBox GenderComboBox;
    public JPanel EditProfilePanel;
    public JTextField UsernameTextField;
    public JLabel ProfilePic;
    private JButton editProfilePictureButton;
    private JFormattedTextField editProfileFormattedTextField;
    String path;

    public EditProfilePage() {

        EasyAccountsMain.EasyAccountsMainFrame.setSize(700, 500);
        EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);

        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(UsernameTextField.getText());
                CU1.GetCUInfo(UsernameTextField.getText());

                EditProfilePanel.setVisible(false);
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

        SaveProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (FirstNameTextField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Invalid First Name.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                else {
                    if (LastNameTextField.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Invalid Last Name.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                    else {
                        if (GenderComboBox.getSelectedItem().toString().equals("Please choose your gender."))
                            JOptionPane.showMessageDialog(null, "Please select your gender.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                        else {
                            if (DateOfBirthTextField.getText().equals(""))
                                JOptionPane.showMessageDialog(null, "Invalid Date of Birth. Please check that the date is in the format 'yyyy-MM-dd'", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                            else {
                                if (EmailTextField.getText().equals(""))
                                    JOptionPane.showMessageDialog(null, "Invalid Email Address.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
                                else {
                                    if (PasswordTextField.getText().equals(""))
                                        JOptionPane.showMessageDialog(null, "Invalid Password.", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
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
                                                                    CurrentUser CU1 = new CurrentUser(UsernameTextField.getText());
                                                                    CU1.SaveUpdate(FirstNameTextField.getText(), LastNameTextField.getText(), UsernameTextField.getText(), GenderComboBox.getSelectedItem().toString(), DateOfBirthTextField.getText(), EmailTextField.getText(), PasswordTextField.getText(), SecurityQuestion1ComboBox.getSelectedItem().toString(), SecurityQuestion1AnswerTextField.getText(), SecurityQuestion2ComboBox.getSelectedItem().toString(), SecurityQuestion2AnswerTextField.getText());
                                                                    CU1.GetCUInfo(UsernameTextField.getText());

                                                                    EditProfilePanel.setVisible(false);
                                                                    ProfilePage PPObject = new ProfilePage();
                                                                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(PPObject.ProfilePanel);
                                                                    EasyAccountsMain.EasyAccountsMainFrame.setResizable(false);
                                                                    EasyAccountsMain.EasyAccountsMainFrame.setSize(700,400);
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
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            else{
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
        });

        editProfilePictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(UsernameTextField.getText());
                CU1.GetCUInfo(UsernameTextField.getText());

                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home"),"Pictures")); //opens window with user's pictures
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images",
                        "jpg","gif","png"); //ensure filetype is appropriate
                file.addChoosableFileFilter(filter); //add filter
                int result = file.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = file.getSelectedFile();
                    String fileName = selectedFile.getName(); //save name of file
                    if (fileName.endsWith("jpg") || fileName.endsWith("png") || fileName.endsWith("PNG") || fileName.endsWith("JPG") ||
                            fileName.endsWith("GIF") || fileName.endsWith("gif")) {
                        path = selectedFile.getAbsolutePath(); // save path of file
                        ProfilePic.setIcon(ResizeImage(path)); // set profile pic to selected file
                        CU1.SaveImage(path, UsernameTextField.getText()); //save image to database
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Please choose an appropriate filetype.",
                                "Invalid File Type", JOptionPane.WARNING_MESSAGE); //error message
                }
            }
        });
    }

    public ImageIcon ResizeImage(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image NewImg = img.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        ImageIcon Image = new ImageIcon(NewImg);
        return Image;
    }
}