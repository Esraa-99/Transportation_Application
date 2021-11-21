package com.company;

import java.sql.*;
import java.util.Scanner;

public class Person {

    private String User_Name;
    private String Mobile_Number;
    private String Email;
    private String Password;

    //setters
    private void  setUser_name(String username){
        this.User_Name =username;
    }
    private void  setMobile_number(String mobile_number){
        this.Mobile_Number =mobile_number;
    }
    private void  setEmail(String email){
        this.Email =email;
    }
    private void  setPassword(String password){
        this.Password =password;
    }
    //getters
    private String getUser_name(){
        return User_Name;
    }
    private String getMobile_number(){
        return this.Mobile_Number;
    }
    private String getEmail(){
        return this.Email;
    }
    private String getPassword(){
        return this.Password;
    }
    //Methods
    //login
    public void Login(String Email, String Pass){
        Scanner sc = new Scanner(System.in);
        System.out.println("PLZ, Enter ur user(1) or driver(2) :) ");
        int choice = sc.nextInt();
        //user
        switch(choice) {
            case 1: {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            "root", "");
                    Statement stmt = connection.createStatement();
                    ResultSet RS = stmt.executeQuery("select * from users where Email= '" + Email + "' and password= '" + Pass + "';");
                    if (RS.next()) {
                        System.out.println("welcome back :) ");
                    } else {
                        System.out.println("Try to login again ^_^");
                    }
                    RS.close();
                    stmt.close();
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erorr!"); //data not exist
                    e.printStackTrace();
                }
                break;
            }
            //driver
            case 2: {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            "root", "");
                    Statement stmt = connection.createStatement();
                    ResultSet RS = stmt.executeQuery("select * from drivers where Email= '" + Email + "' and Password= '" + Pass + "';");
                    if (RS.next()) {
                        System.out.println("welcome back :) ");
                    } else {
                        System.out.println("Try to login again ^_^");
                    }
                    RS.close();
                    stmt.close();
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erorr!"); //data not exist
                    e.printStackTrace();
                }
                break;
            } default: {
                System.out.println("Select from options");
                break;
            }
        }
    }

}
