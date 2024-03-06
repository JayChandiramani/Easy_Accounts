package com.codebuilding;

import com.codebuilding.PreLogin.LoginPage;

import javax.swing.*;

public class EasyAccountsMain {
    public static JFrame EasyAccountsMainFrame; //JFrame to be used by the whole program

    public static void main(String[] args){
        LoginPage LPObject = new LoginPage(); // inheriting login page

        EasyAccountsMainFrame = new JFrame("Easy Accounts");
        EasyAccountsMainFrame.setContentPane(LPObject.LoginPanel); //setting panel in frame
        EasyAccountsMainFrame.setVisible(true);
        EasyAccountsMainFrame.pack();
        EasyAccountsMainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //end program when the x button is clicked
        EasyAccountsMainFrame.setSize(700,500); //setting size of frame
        EasyAccountsMainFrame.setResizable(false);
        EasyAccountsMainFrame.setLocationRelativeTo(null); //setting location to the middle of the screen
    }
}
