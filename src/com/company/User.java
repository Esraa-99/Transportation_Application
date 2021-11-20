package com.company;

import java.sql.*;
import java.util.Scanner;

public class User extends Person implements Registration,Show_Rating {
    private String Source;
    private String Destination;

    //setters
    void setSource(String source){
        this.Source = source;
    }
    private void setDestination(String destination){
        this.Destination = destination;
    }
    //getters
    String getSource(){
        return this.Source;
    }
    private String getDestination(){
        return this.Destination ;
    }
    //Methods
    @Override
    public void Register() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String userName = in.nextLine();
        System.out.print("Enter your mobile number: ");
        String mobileNum = in.nextLine();
        System.out.print("Enter your email: ");
        String email = in.nextLine();
        System.out.print("Enter your password: ");
        String password = in.nextLine();

        //insert new row in table user
        String query = "insert into users (Username,Mobile,Email,Password) "
                + "values (?,?,?,?)";

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, mobileNum);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.executeUpdate();
            System.out.println("the recent user has registered");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    void Requset(){

    }
    void Rate_Driver(){}
    @Override
    public void Show() {

    }
}
