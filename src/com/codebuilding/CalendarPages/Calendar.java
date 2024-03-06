package com.codebuilding.CalendarPages;

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

public class Calendar {
    private JFormattedTextField eventsFormattedTextField;
    private JButton backButton;
    private JButton addEventButton;
    public JPanel CalendarPanel;
    public JLabel usernameLabel;
    public JTable Events;
    private JButton removeEventButton;
    private JButton saveAsPDFButton;

    public Calendar() {

        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalendarPanel.setVisible(false);
                AddEventPage AEPObject = new AddEventPage();
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(AEPObject.AddEventPanel);
                AEPObject.Username.setText(usernameLabel.getText());
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(usernameLabel.getText());
                CU1.GetCUInfo(usernameLabel.getText());

                if (CU1.GetDesignation().equals("Office Worker")) {
                    OfficeWorkerHomePage OWHPObject = new OfficeWorkerHomePage();
                    OWHPObject.WelcomeUsernameLabel.setText(CU1.GetUsername());
                    OWHPObject.WelcomeFullNameLabel.setText("Welcome " + CU1.GetFName() + " " + CU1.GetLName());
                    OWHPObject.ProfilePicLabel.setIcon(new ImageIcon(CU1.GetProfilePic().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    CalendarPanel.setVisible(false);
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
                    CalendarPanel.setVisible(false);
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
                    CalendarPanel.setVisible(false);
                    EasyAccountsMain.EasyAccountsMainFrame.setContentPane(OHPObject.OwnerHomePanel);
                    EasyAccountsMain.EasyAccountsMainFrame.setSize(500,350);
                    EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                    EasyAccountsMain.EasyAccountsMainFrame.setResizable(false);
                }
            }
        });

        removeEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(usernameLabel.getText());
                CU1.GetCUInfo(usernameLabel.getText());
                int selectedRow = Events.getSelectedRow();
                if (selectedRow>=0 && JOptionPane.showConfirmDialog(null,
                        "Are you sure that you wish to delete this event?", "Confirmation",
                        JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION){
                    TableModel events = CU1.RemoveEvent(selectedRow, usernameLabel.getText());
                    Events.setModel(events);
                } else {
                JOptionPane.showMessageDialog(null, "Please Select a Row to Delete.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveAsPDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Events.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
