package com.company;

import java.sql.*;
import java.util.Scanner;

public class Person {

    private String User_Name;
    private String Mobile_Number;
    public String Email;
    private String Password;
    public   String Birthday;

    //setters
    public void  setUser_name(String username){
        this.User_Name =username;
    }
    public void  setMobile_number(String mobile_number){
        this.Mobile_Number =mobile_number;
    }
    public void  setEmail(String email){
        this.Email =email;
    }
    public void  setPassword(String password){
        this.Password =password;
    }
    public void  setbirthday(String birthday){
        this.Birthday =birthday;
    }
    //getters
    public String getUser_name(){
        return User_Name;
    }
    public String getMobile_number(){
        return this.Mobile_Number;
    }
    public String getEmail(){
        return this.Email;
    }
    public String getbirthday(){
        return this.Birthday;
    }
    public String getPassword(){
        return this.Password;
    }
    //Methods
    //login
    public int Login(String Email, String Pass) {
        int var = check_person(Email, Pass);
        return var;
    }
    public int check_person(String Email, String Pass)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("PLZ, Enter ur user(1) or driver(2) or admin(3) :) ");
        int choice = sc.nextInt();
        //user
        switch(choice) {
            case 1: {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            "root", "");
                    Statement stmt = connection.createStatement();
                    ResultSet RS = stmt.executeQuery("select * from users where Email= '" + Email + "' and password= '" + Pass + "'and status='active';");
                    if (RS.next()) {
                        System.out.println("welcome back :) ");

                        setEmail(Email);
                        this.Email=Email;
                        String hh=RS.getString("birthdate");
                        System.out.println(hh);
                          setbirthday(hh);
                           this.Birthday=hh;

                    } else {
                        System.out.println("Try to login again ^_^");
                        return  0;
                    }
                    RS.close();
                    stmt.close();
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erorr!"); //data not exist
                    e.printStackTrace();
                }
                return 1;

            }
            //driver
            case 2: {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            "root", "");
                    Statement stmt = connection.createStatement();
                    ResultSet RS = stmt.executeQuery("select * from drivers where Email= '" + Email + "' and Password= '" + Pass + "' and status='active';");
                    if (RS.next()) {
                        System.out.println("welcome back :) ");

                    } else {
                        System.out.println("Try to login again ^_^");
                        return  0;
                    }
                    RS.close();
                    stmt.close();
                    connection.close();

                } catch (SQLException e) {
                    System.out.println("Erorr!"); //data not exist
                    e.printStackTrace();
                }
                return 2;

            }
            case 3:
            {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            "root", "");
                    Statement stmt = connection.createStatement();
                    ResultSet RS = stmt.executeQuery("select * from admin where Email= '" + Email + "' and password= '" + Pass + "';");
                    if (RS.next()) {
                        System.out.println("welcome back :) ");
                    } else {
                        System.out.println("Try to login again ^_^");
                        return  0;
                    }
                    RS.close();
                    stmt.close();
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erorr!"); //data not exist
                    e.printStackTrace();
                }
                return 3;
            }
            default: {
                System.out.println("Select from options");
                return 4;
            }
        }
    }
}
