package com.codebuilding;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class NewUser {
    String Username;

    public NewUser(String NUName){
        this.Username = NUName;
    } // set new user name

    public void SetNUInfo(String NUName, String NFName, String NLName, String NPassword, String NGender, String NEmail,
                          String NSQ1, String NSQ1A, String NSQ2, String NSQ2A, String NDesignation, Date NDOB) {
        try {
            String host = "jdbc:mysql://localhost:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass); //make connection to database

            Statement stmt1 = con.createStatement();
            String SQL1 = "SELECT * FROM ea_users WHERE Username = '" + NUName + "'";
            ResultSet rs1 = stmt1.executeQuery(SQL1);  //check if user exists

            if (rs1.next()) { //error if user exists
                JOptionPane.showMessageDialog(null, NUName + " has already been taken. Please choose another username.");
            }
            else {
                Statement stmt2 = con.createStatement();
                String SQL2 = "INSERT INTO easyaccounts_db.ea_users (Username,First_Name,Last_Name,Designation," +
                        "Password,Gender,Date_Of_Birth,Email_Address,Security_Question_1,Security_Answer_1," +
                        "Security_Question_2,Security_Answer_2) VALUES ('" + NUName + "','" + NFName + "','" +
                        "" + NLName + "','" + NDesignation + "','" + NPassword + "','" + NGender + "','" + NDOB + "'," +
                        "'" + NEmail + "','" + NSQ1 + "','" + NSQ1A + "','" + NSQ2 + "','" + NSQ2A + "');";
                stmt2.executeUpdate(SQL2);//Inserting new user into database

                File file = new File("C:\\Users\\Jay V. Chandiramani\\IdeaProjects\\EasyAccounts\\src\\com\\codebuilding\\Icons\\Account.png"); //getting default profile picture
                FileInputStream fis = new FileInputStream(file);
                PreparedStatement ps = con.prepareStatement("UPDATE easyaccounts_db.ea_users " +
                        "SET Profile_Pic = ? WHERE Username='" + NUName + "';"); //saving default profile pic in database
                ps.setBinaryStream(1, fis, (int) file.length());
                ps.executeUpdate();

                ps.close();
                fis.close();
                con.close();
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // add new user

    public void SetTables(String Username){
        try {
            String host = "jdbc:mysql://Jay-PC:3306/easyaccounts_db";
            String uName = "User";
            String uPass = "User1234";
            Connection con = DriverManager.getConnection(host, uName, uPass);

            CurrentUser CU1 = new CurrentUser(Username);
            CU1.GetCUInfo(Username);

            if(CU1.GetDesignation().equals("Office Worker") || CU1.GetDesignation().equals("Office Manager")) {
                Statement stmt1 = con.createStatement();
                String SQL1 = "INSERT INTO user_tables (Username, ElementName, ElementValue, ElementType, ElementTable) VALUES ('" + Username + "', 'Example', '0000', 'Current Assets', 'Balance Sheet');";
                stmt1.executeUpdate(SQL1);

                Statement stmt2 = con.createStatement();
                String SQL2 = "INSERT INTO user_tables (Username, ElementName, ElementValue, ElementType, ElementTable) VALUES ('" + Username + "', 'Example', '0000', 'Fixed Assets', 'Balance Sheet');";
                stmt2.executeUpdate(SQL2);

                Statement stmt3 = con.createStatement();
                String SQL3 = "INSERT INTO user_tables (Username, ElementName, ElementValue, ElementType, ElementTable) VALUES ('" + Username + "', 'Example', '0000', 'Current Liabilities', 'Balance Sheet');";
                stmt3.executeUpdate(SQL3);

                Statement stmt4 = con.createStatement();
                String SQL4 = "INSERT INTO user_tables (Username, ElementName, ElementValue, ElementType, ElementTable) VALUES ('" + Username + "', 'Example', '0000', 'Long Term Liabilities', 'Balance Sheet');";
                stmt4.executeUpdate(SQL4);

                Statement stmt5 = con.createStatement();
                String SQL5 = "INSERT INTO user_tables (Username, ElementName, ElementValue, ElementType, ElementTable) VALUES ('" + Username + "', 'Example', '0000', 'Equity', 'Balance Sheet');";
                stmt5.executeUpdate(SQL5);

                Statement stmt6 = con.createStatement();
                String SQL6 = "INSERT INTO user_tables (Username, ElementName, ElementValue, ElementType, ElementTable) VALUES ('" + Username + "', 'Example', '0000', 'Revenue', 'Profit and Loss Statement');";
                stmt6.executeUpdate(SQL6);

                Statement stmt7 = con.createStatement();
                String SQL7 = "INSERT INTO user_tables (Username, ElementName, ElementValue, ElementType, ElementTable) VALUES ('" + Username + "', 'Example', '0000', 'Cost of Goods Sold', 'Profit and Loss Statement');";
                stmt7.executeUpdate(SQL7);

                Statement stmt8 = con.createStatement();
                String SQL8 = "INSERT INTO user_tables (Username, ElementName, ElementValue, ElementType, ElementTable) VALUES ('" + Username + "', 'Example', '0000', 'Expenses', 'Profit and Loss Statement');";
                stmt8.executeUpdate(SQL8);
            }

            con.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

    } // set default tables for new user
}
