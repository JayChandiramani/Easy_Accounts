package com.codebuilding.CalendarPages;

import com.codebuilding.CurrentUser;
import com.codebuilding.EasyAccountsMain;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEventPage {
    private JFormattedTextField addEventsFormattedTextField;
    private JTextField eventName;
    private JTextField eventDate;
    private JButton backButton;
    private JButton addButton;
    private JTextField eventDescription;
    private JRadioButton paydayRadioButton;
    private JRadioButton billRadioButton;
    private JRadioButton appointmentRadioButton;
    private JRadioButton miscRadioButton;
    public JTextField Username;
    public JPanel AddEventPanel;
    private String eventType;

    public AddEventPage() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(Username.getText());
                CU1.GetCUInfo(Username.getText());

                if(paydayRadioButton.isSelected())
                    eventType = paydayRadioButton.getActionCommand();

                if(billRadioButton.isSelected())
                    eventType = billRadioButton.getActionCommand();

                if(appointmentRadioButton.isSelected())
                    eventType = appointmentRadioButton.getActionCommand();

                if(miscRadioButton.isSelected())
                    eventType = miscRadioButton.getActionCommand();

                CU1.AddEvent(Username.getText(), eventName.getText(), eventDate.getText(), eventDescription.getText(), eventType);

                AddEventPanel.setVisible(false);
                Calendar CObject = new Calendar();
                EasyAccountsMain.EasyAccountsMainFrame.setSize(500, 350);
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(CObject.CalendarPanel);
                CObject.usernameLabel.setText(Username.getText());
                TableModel events = CU1.GetEvents(Username.getText());
                CObject.Events.setModel(events);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser CU1 = new CurrentUser(Username.getText());
                CU1.GetCUInfo(Username.getText());
                AddEventPanel.setVisible(false);
                Calendar CObject = new Calendar();
                EasyAccountsMain.EasyAccountsMainFrame.setSize(500, 350);
                EasyAccountsMain.EasyAccountsMainFrame.setLocationRelativeTo(null);
                EasyAccountsMain.EasyAccountsMainFrame.setContentPane(CObject.CalendarPanel);
                CObject.usernameLabel.setText(Username.getText());
                TableModel events = CU1.GetEvents(Username.getText());
                CObject.Events.setModel(events);
            }
        });
    }
}
