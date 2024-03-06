package com.codebuilding;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class CurrentUser {
    String Username, FName, LName, Password, Gender, Email, SQ1, SQ1A, SQ2, SQ2A, Designation;
    Date DOB;
    public Blob ProfilePic;
    BufferedImage bufferedImage;
    private DefaultTableModel tableModel;

    public CurrentUser(String CUName) {
        this.Username = CUName;
    } // set CU

    public void GetCUInfo(String CUName) {
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass); //make connection to database

            Statement stmt1 = con.createStatement();
            String SQL1 = "SELECT * FROM ea_users WHERE Username = '" + CUName + "'";
            ResultSet rs1 = stmt1.executeQuery(SQL1); //get info for specific user

            if (rs1.next()) { //get user info
                Username = rs1.getString("Username");
                FName = rs1.getString("First_Name");
                LName = rs1.getString("Last_Name");
                Designation = rs1.getString("Designation");
                Password = rs1.getString("Password");
                Gender = rs1.getString("Gender");
                Email = rs1.getString("Email_Address");
                SQ1 = rs1.getString("Security_Question_1");
                SQ1A = rs1.getString("Security_Answer_1");
                SQ2 = rs1.getString("Security_Question_2");
                SQ2A = rs1.getString("Security_Answer_2");
                DOB = rs1.getDate("Date_Of_Birth");
                ProfilePic = rs1.getBlob("Profile_Pic");

                try { // get profile pic from database
                    int blobLength = (int) ProfilePic.length(); //length of blob from database
                    byte[] blobAsBytes = ProfilePic.getBytes(1, blobLength); //blob as byte array
                    bufferedImage = ImageIO.read(new ByteArrayInputStream(blobAsBytes)); //saving blob as buffered image
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else{
                JOptionPane.showMessageDialog(null, CUName + " is not a user.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
                Username = null;
            }

            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    } // get user info

    public String GetUsername() {
        return Username;
    } //return username

    public String GetFName() {
        return FName;
    } // return user first name

    public String GetLName() {
        return LName;
    } // return user last name

    public String GetDesignation() {
        return Designation;
    } // return user designation or role

    public String GetPassword() {
        return Password;
    } // return user password

    public String GetGender() {
        return Gender;
    } // return user gender

    public String GetEmail() {
        return Email;
    } // return user email

    public String GetSQ1() {
        return SQ1;
    } // return user security question 1

    public String GetSQ1A() {
        return SQ1A;
    } // return user security question answer 1

    public String GetSQ2() {
        return SQ2;
    } // return user security question 2

    public String GetSQ2A() {
        return SQ2A;
    } // return user security question answer 2

    public Date GetDOB() {
        return DOB;
    } // return user date of birth

    public void SetNewPassword(String CUsername, String NPassword) {
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass); //make connection to database

            Statement stmt1 = con.createStatement();
            String SQL1 = "UPDATE easyaccounts_db.ea_users SET Password='" + NPassword + "' " +
                    "WHERE Username='" + CUsername + "';"; //set new password
            stmt1.executeUpdate(SQL1);

            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    } // set new password for registered user

    public void SaveImage(String path, String Username) {
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass); // create connection to database

            File file = new File(path); // create new file using path of selected image
            FileInputStream fis = new FileInputStream(file); // new file inputstream to add image to database
            PreparedStatement ps = con.prepareStatement("UPDATE easyaccounts_db.ea_users SET Profile_Pic = ? WHERE Username='" +
                    "" + Username + "';");
            ps.setBinaryStream(1, fis, (int) file.length()); // convert to blob
            ps.executeUpdate(); // save image

            ps.close();
            fis.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // set new profile pic for registered user

    public BufferedImage GetProfilePic() {
        return bufferedImage;
    } // return user profile pic

    public void SaveUpdate(String FName, String LName, String OUsername, String Gender, String DOB,
                           String Email, String Password, String SQ1,
                           String SQ1A, String SQ2, String SQ2A) {
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass);

            java.sql.Date DateOfBirth = java.sql.Date.valueOf(DOB);

            Statement stmt1 = con.createStatement();
            String SQL1 = "SELECT * FROM ea_users WHERE Username = '" + Username + "'";
            ResultSet rs1 = stmt1.executeQuery(SQL1);

            if (rs1.next() && rs1.getString("Username").equals(OUsername)) {

                Statement stmt3 = con.createStatement();
                String SQL3 = "UPDATE easyaccounts_db.ea_users SET First_Name='" + FName + "' WHERE Username='" + Username + "';";
                stmt3.executeUpdate(SQL3);

                Statement stmt4 = con.createStatement();
                String SQL4 = "UPDATE easyaccounts_db.ea_users SET Last_Name='" + LName + "' WHERE Username='" + Username + "';";
                stmt4.executeUpdate(SQL4);

                Statement stmt6 = con.createStatement();
                String SQL6 = "UPDATE easyaccounts_db.ea_users SET Password='" + Password + "' WHERE Username='" + Username + "';";
                stmt6.executeUpdate(SQL6);

                Statement stmt7 = con.createStatement();
                String SQL7 = "UPDATE easyaccounts_db.ea_users SET Gender='" + Gender + "' WHERE Username='" + Username + "';";
                stmt7.executeUpdate(SQL7);

                Statement stmt8 = con.createStatement();
                String SQL8 = "UPDATE easyaccounts_db.ea_users SET Date_Of_Birth='" + DateOfBirth + "' WHERE Username='" + Username + "';";
                stmt8.executeUpdate(SQL8);

                Statement stmt9 = con.createStatement();
                String SQL9 = "UPDATE easyaccounts_db.ea_users SET Email_Address='" + Email + "' WHERE Username='" + Username + "';";
                stmt9.executeUpdate(SQL9);

                Statement stmt5 = con.createStatement();
                String SQL5 = "UPDATE easyaccounts_db.ea_users SET Security_Question_1='" + SQ1 + "' WHERE Username='" + Username + "';";
                stmt5.executeUpdate(SQL5);

                Statement stmt10 = con.createStatement();
                String SQL10 = "UPDATE easyaccounts_db.ea_users SET Security_Answer_1='" + SQ1A + "' WHERE Username='" + Username + "';";
                stmt10.executeUpdate(SQL10);

                Statement stmt11 = con.createStatement();
                String SQL11 = "UPDATE easyaccounts_db.ea_users SET Security_Question_2='" + SQ2 + "' WHERE Username='" + Username + "';";
                stmt11.executeUpdate(SQL11);

                Statement stmt12 = con.createStatement();
                String SQL12 = "UPDATE easyaccounts_db.ea_users SET Security_Answer_2='" + SQ2A + "' WHERE Username='" + Username + "';";
                stmt12.executeUpdate(SQL12);
            }

            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    } // save changes made to user profile

    public void AddEvent(String Username, String EventName, String EventDate, String EventDescription,
                         String EventType) {
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass);

            java.sql.Date eventDate = java.sql.Date.valueOf(EventDate);

            Statement stmt1 = con.createStatement();
            String SQL1 = "INSERT INTO userevents (Username, EventName, EventDate, EventDescription, EventType) VALUES ('" + Username + "', '" + EventName + "', '" + eventDate + "', '" + EventDescription + "', '" + EventType + "');";
            stmt1.executeUpdate(SQL1);

            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    } // add event to calendar

    public TableModel RemoveEvent(int selectedRow, String username) {
        String EventName = GetEvents(username).getValueAt(selectedRow, 0).toString(); //getting event names
        String EventType = GetEvents(username).getValueAt(selectedRow, 1).toString(); //getting event types
        java.sql.Date EventDate = java.sql.Date.valueOf(GetEvents(username).getValueAt(selectedRow, 2)
                .toString()); //getting event types
        String EventDescription = GetEvents(username).getValueAt(selectedRow, 3).toString(); //getting event description

        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass); // making connection to database

            Statement stmt1 = con.createStatement();
            String SQL1 = "DELETE FROM userevents WHERE Username = '" + username + "'" +
                    "AND EventName = '" + EventName + "' " + "AND EventDate = '" + EventDate + "' " +
                    "AND EventDescription = '" + EventDescription + "' AND EventType = '" + EventType + "'";
            stmt1.executeUpdate(SQL1); //delete selected event from database

            Statement stmt2 = con.createStatement();
            String SQL2 = "SELECT * FROM userevents WHERE Username = '" + Username + "'";
            ResultSet rs1 = stmt2.executeQuery(SQL2); //updated events

            String[] columnNames = {"Event Name", "Event Type", "Event Date", "Event Description"};
            this.tableModel = new DefaultTableModel(columnNames, 0); //set column names

            while(rs1.next()){ //get event info
                String EventName1 = rs1.getString("EventName");
                String EventType1 = rs1.getString("EventType");
                String EventDate1 = rs1.getDate("EventDate").toString();
                String EventDescription1 = rs1.getString("EventDescription");

                String[] data = {EventName1,EventType1,EventDate1, EventDescription1};

                tableModel.addRow(data); //add each event to update
            }

            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return tableModel;
    } // remove event from calendar

    public TableModel GetEvents(String Username) {
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass);

            Statement stmt1 = con.createStatement();
            String SQL1 = "SELECT * FROM userevents WHERE Username = '" + Username + "'";
            ResultSet rs1 = stmt1.executeQuery(SQL1);

            String[] columnNames = {"Event Name", "Event Type", "Event Date", "Event Description"};
            this.tableModel = new DefaultTableModel(columnNames, 0);

            while(rs1.next()){
                String EventName = rs1.getString("EventName");
                String EventType = rs1.getString("EventType");
                String EventDate = rs1.getDate("EventDate").toString();
                String EventDescription = rs1.getString("EventDescription");

                String[] data = {EventName,EventType,EventDate, EventDescription};

                tableModel.addRow(data);
            }

            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return tableModel;
    } // return user events

    public TableModel GetTable (String Username, String type, String Table){
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass);

            Statement stmt1 = con.createStatement();
            String SQL1 = "SELECT * FROM user_tables WHERE Username = '" + Username + "' AND ElementType = '" + type + "' AND ElementTable = '" + Table + "'";
            ResultSet rs1 = stmt1.executeQuery(SQL1);

            String[] columnNames = {type, "Value"};
            this.tableModel = new DefaultTableModel(columnNames, 0);

            while(rs1.next()){
                String ElementName = rs1.getString("ElementName");
                String ElementValue = rs1.getString("ElementValue");

                String[] data = {ElementName,ElementValue};

                tableModel.addRow(data);
            }

            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return tableModel;
    } // return accounts in each financial table

    public void UpdateTable (String Username, TableModel currentTable, String type, String Table){
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass);

            Statement stmt1 = con.createStatement();
            String SQL1 = "DELETE FROM user_tables WHERE Username = '" + Username + "' AND ElementType = '" + type + "' AND ElementTable = '" + Table + "'";
            stmt1.executeUpdate(SQL1);

            Statement stmt2 = con.createStatement();

            for (int i = 0; i < currentTable.getRowCount(); i++){
                String ElementValue1 = currentTable.getValueAt(i,1).toString();
                double ElementValue = Double.parseDouble(ElementValue1);
                String SQL2 = "INSERT INTO user_tables (Username, ElementName, ElementValue, ElementType, ElementTable) VALUES ('" + Username + "', '" + currentTable.getValueAt(i,0).toString() + "', '" + ElementValue + "', '" + type + "', '" + Table + "');";
                stmt2.executeUpdate(SQL2);
            }

            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    } //save changes made to tables

    public TableModel GetBS (String Username){
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass);

            Statement stmt1 = con.createStatement();
            String SQL1 = "SELECT * FROM user_tables WHERE Username = '" + Username + "' AND ElementType = 'Current Assets' AND ElementTable = 'Balance Sheet'";
            ResultSet rs1 = stmt1.executeQuery(SQL1);

            Statement stmt2 = con.createStatement();
            String SQL2 = "SELECT * FROM user_tables WHERE Username = '" + Username + "' AND ElementType = 'Fixed Assets' AND ElementTable = 'Balance Sheet'";
            ResultSet rs2 = stmt2.executeQuery(SQL2);

            Statement stmt3 = con.createStatement();
            String SQL3 = "SELECT * FROM user_tables WHERE Username = '" + Username + "' AND ElementType = 'Current Liabilities' AND ElementTable = 'Balance Sheet'";
            ResultSet rs3 = stmt3.executeQuery(SQL3);

            Statement stmt4 = con.createStatement();
            String SQL4 = "SELECT * FROM user_tables WHERE Username = '" + Username + "' AND ElementType = 'Long Term Liabilities' AND ElementTable = 'Balance Sheet'";
            ResultSet rs4 = stmt4.executeQuery(SQL4);

            Statement stmt5 = con.createStatement();
            String SQL5 = "SELECT * FROM user_tables WHERE Username = '" + Username + "' AND ElementType = 'Equity' AND ElementTable = 'Balance Sheet'";
            ResultSet rs5 = stmt5.executeQuery(SQL5);

            String[] columnNames = {"Real Account", "Value"};
            this.tableModel = new DefaultTableModel(columnNames, 0);

            String[] Space = {null, null};

            String[] title = {"Current Assets", null};
            tableModel.addRow(title);
            tableModel.addRow(Space);

            double CAtotal = 0;

            while(rs1.next()){
                String CAsset = rs1.getString("ElementName");
                String CAssetValue = rs1.getString("ElementValue");
                CAtotal = CAtotal + rs1.getDouble("ElementValue");

                String[] data = {CAsset,CAssetValue};

                tableModel.addRow(data);
            }

            tableModel.addRow(Space);
            String[] title1 = {"Total Current Assets", String.valueOf(CAtotal)};
            tableModel.addRow(title1);

            tableModel.addRow(Space);
            String[] title2 = {"Fixed Assets", null};
            tableModel.addRow(title2);
            tableModel.addRow(Space);

            double FAtotal = 0;

            while(rs2.next()){
                String FAsset = rs2.getString("ElementName");
                String FAssetValue = rs2.getString("ElementValue");
                FAtotal = FAtotal + rs2.getDouble("ElementValue");

                String[] data = {FAsset,FAssetValue};

                tableModel.addRow(data);
            }

            tableModel.addRow(Space);
            String [] title3 = {"Total Fixed Assets", String.valueOf(FAtotal)};
            tableModel.addRow(title3);
            tableModel.addRow(Space);

            String [] titletotalA = {"Total Assets", String.valueOf(FAtotal+CAtotal)};
            tableModel.addRow(titletotalA);
            tableModel.addRow(Space);

            String[] title4 = {"Current Liabilities", null};
            tableModel.addRow(title4);
            tableModel.addRow(Space);

            double CLtotal = 0;

            while(rs3.next()){
                String CL = rs3.getString("ElementName");
                String CLValue = rs3.getString("ElementValue");
                CLtotal = CLtotal + rs3.getDouble("ElementValue");

                String[] data = {CL,CLValue};

                tableModel.addRow(data);
            }

            tableModel.addRow(Space);
            String [] title5 = {"Total Current Liabilities", String.valueOf(CLtotal)};
            tableModel.addRow(title5);
            tableModel.addRow(Space);

            String[] title6 = {"Long Term Liabilities", null};
            tableModel.addRow(title6);
            tableModel.addRow(Space);

            double LTLtotal = 0;

            while(rs4.next()){
                String LTL = rs4.getString("ElementName");
                String LTLValue = rs4.getString("ElementValue");
                LTLtotal = LTLtotal + rs4.getDouble("ElementValue");

                String[] data = {LTL,LTLValue};

                tableModel.addRow(data);
            }

            tableModel.addRow(Space);
            String [] title7 = {"Total Long Term Liabilities", String.valueOf(LTLtotal)};
            tableModel.addRow(title7);
            tableModel.addRow(Space);

            String[] title8 = {"Total Liabilities", String.valueOf(CLtotal + LTLtotal)};
            tableModel.addRow(title8);
            tableModel.addRow(Space);

            String[] title9 = {"Total Assets Less Total Liabilities", String.valueOf((CAtotal + FAtotal)-(CLtotal + LTLtotal))};
            tableModel.addRow(title9);
            tableModel.addRow(Space);

            String[] title10 = {"Equity", null};
            tableModel.addRow(title10);
            tableModel.addRow(Space);

            double Etotal = 0;

            while(rs5.next()){
                String Equity = rs5.getString("ElementName");
                String EquityValue = rs5.getString("ElementValue");
                Etotal = Etotal + rs5.getDouble("ElementValue");

                String[] data = {Equity,EquityValue};

                tableModel.addRow(data);
            }

            tableModel.addRow(Space);
            String [] title11 = {"Total Equity", String.valueOf(Etotal)};
            tableModel.addRow(title11);


            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return tableModel;
    } // format of balance sheet and totals

    public TableModel GetPNL (String Username){
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass); // make connection to database

            Statement stmt1 = con.createStatement();
            String SQL1 = "SELECT * FROM user_tables WHERE Username = '" + Username + "' " +
                    "AND ElementType = 'Revenue' AND ElementTable = 'Profit and Loss Statement'";
            ResultSet rs1 = stmt1.executeQuery(SQL1); //get accounts labelled as revenue

            Statement stmt2 = con.createStatement();
            String SQL2 = "SELECT * FROM user_tables WHERE Username = '" + Username + "' " +
                    "AND ElementType = 'Cost of Goods Sold' AND ElementTable = 'Profit and Loss Statement'";
            ResultSet rs2 = stmt2.executeQuery(SQL2); //get accounts labelled as COGS

            Statement stmt3 = con.createStatement();
            String SQL3 = "SELECT * FROM user_tables WHERE Username = '" + Username + "' " +
                    "AND ElementType = 'Expenses' AND ElementTable = 'Profit and Loss Statement'";
            ResultSet rs3 = stmt3.executeQuery(SQL3); //get accounts labelled as Expenses

            String[] columnNames = {"Nominal Account", "Value"};
            this.tableModel = new DefaultTableModel(columnNames, 0); //add column names

            String[] Space = {null, null}; //space

            String[] title = {"Revenue", null};
            tableModel.addRow(title); //heading for revenue
            tableModel.addRow(Space); //space

            double Rtotal = 0; //revenue total

            while(rs1.next()){ //get and add revenue accounts
                String RName = rs1.getString("ElementName");
                String RValue = rs1.getString("ElementValue");
                Rtotal = Rtotal + rs1.getDouble("ElementValue");

                String[] data = {RName,RValue};

                tableModel.addRow(data);
            }

            tableModel.addRow(Space); //space
            String[] title1 = {"Total Revenue", String.valueOf(Rtotal)}; // row with revenue total
            tableModel.addRow(title1);
            tableModel.addRow(Space); //space

            String[] title2 = {"Cost of Goods Sold", null}; //COGS title
            tableModel.addRow(title2);
            tableModel.addRow(Space); //space

            double COGStotal = 0; //COGS total

            while(rs2.next()){ //get and add COGS accounts
                String COGSName = rs2.getString("ElementName");
                String COGSValue = rs2.getString("ElementValue");
                COGStotal = COGStotal + rs2.getDouble("ElementValue");

                String[] data = {COGSName,COGSValue};

                tableModel.addRow(data);
            }

            tableModel.addRow(Space); //space
            String[] title3 = {"Total Cost of Goods Sold", String.valueOf(COGStotal)}; //row with COGS total
            tableModel.addRow(title3);
            tableModel.addRow(Space); //space

            String[] title4 = {"Gross Profit", String.valueOf(Rtotal-COGStotal)}; //row with gross profit
            tableModel.addRow(title4);
            tableModel.addRow(Space); //space

            String[] title5 = {"Expenses", null}; //expenses title
            tableModel.addRow(title5);
            tableModel.addRow(Space); //space

            double Exptotal = 0; //expenses total

            while(rs3.next()){ //get and add expense accounts
                String ExpName = rs3.getString("ElementName");
                String ExpValue = rs3.getString("ElementValue");
                Exptotal = Exptotal + rs3.getDouble("ElementValue");

                String[] data = {ExpName,ExpValue};

                tableModel.addRow(data);
            }

            tableModel.addRow(Space);//space
            String[] title6 = {"Total Expenses", String.valueOf(Exptotal)}; //row with expenses total
            tableModel.addRow(title6);
            tableModel.addRow(Space);//space

            String[] title7 = {"Net Profit", String.valueOf((Rtotal-COGStotal)-Exptotal)}; //net profit row
            tableModel.addRow(title7);

            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return tableModel;
    } // format of profit and loss statement and totals

    public TableModel GetEmployees(){
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass); // make connection to database

            Statement stmt1 = con.createStatement();
            String SQL1 = "SELECT * FROM ea_users";
            ResultSet rs1 = stmt1.executeQuery(SQL1); // get employees

            String[] columnNames = {"Username", "First Name", "Last Name", "Designation"};
            this.tableModel = new DefaultTableModel(columnNames, 0); // set column names

            while(rs1.next()){ //get employee info
                if (!rs1.getString("Designation").equals("Owner")) {
                    String Username = rs1.getString("Username");
                    String FirstName = rs1.getString("First_Name");
                    String LastName = rs1.getString("Last_Name");
                    String Designation = rs1.getString("Designation");

                    String[] data = {Username, FirstName, LastName, Designation};

                    tableModel.addRow(data); // add each employee info
                }
            }
            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return tableModel;
    } // return employees in business

    public TableModel RemoveEmployee(int selectedRow) {
        String Username = GetEmployees().getValueAt(selectedRow, 0).toString(); //getting employee username
        String FirstName = GetEmployees().getValueAt(selectedRow, 1).toString(); //getting employee first name
        String LastName = GetEmployees().getValueAt(selectedRow, 2).toString(); //getting employee last
        String Designation = GetEmployees().getValueAt(selectedRow, 3).toString(); //getting employee username

        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass); //making connection to database

            Statement stmt1 = con.createStatement();
            String SQL1 = "DELETE FROM ea_users WHERE Username = '" + Username + "'AND First_Name = '" +
                    "" + FirstName + "' AND Last_Name = '" + LastName + "' AND Designation = '" + Designation + "'";
            stmt1.executeUpdate(SQL1); //deleting employee

            GetEmployees(); // updating table

            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return tableModel;
    } //remove selected employees
}