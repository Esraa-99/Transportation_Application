package com.company;

import java.sql.*;
import java.util.Scanner;

public class Driver  extends Person implements Registration,Show_Rating {
    private String National_ID;
    private String Driving_license;


    //setters
    private void  setNational_ID(String national_id){
        this.National_ID =national_id;
    }
    private void  setDriving_license(String drivinglicense){
        this.Driving_license =drivinglicense;
    }

    //getters
    private String setNational_ID(){
        return this.National_ID ;
    }
    private String setDriving_license(){
        return this.Driving_license ;
    }

    //Methods
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
        System.out.print("Enter your national ID: ");
        String nationalID = in.nextLine();
        System.out.print("Enter your driving licence: ");
        String drivingLicence = in.nextLine();
        System.out.print("Enter your status: ");
        String status = in.nextLine();
        //verified by admin
        Admin a = new Admin();
        if (!a.Verify()) {
            System.out.println("You are not verified :(");
        }
        else
        {
            //insert new row in table driver
            //PreparedStatement ps = null;
            String query = "insert into drivers (Username,Mobile,Email,Password,National_ID,driving_licence,Status) "
                    + "values (?,?,?,?,?,?,?)";

            try {
                //Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                        "root", "");

                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, userName);
                ps.setString(2, mobileNum);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setString(5, nationalID);
                ps.setString(6, drivingLicence);
                ps.setString(7, status);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    int Offer(){
        return 0;
    }

    void Put_FaviorateSource_Areas(){

    }

    @Override
    public void Show() {

    }
}

