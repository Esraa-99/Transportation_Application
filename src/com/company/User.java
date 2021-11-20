package com.company;

import java.sql.*;
import java.util.Scanner;

public class User extends Person implements Registration,Show_Rating {
    private String Source;
    private String Destination;
    Driver driver = new Driver();

    //setters
    public void setSource(String source) {
        this.Source = source;
    }

    public void setDestination(String destination) {
        this.Destination = destination;
    }

    //getters
    public String getSource() {
        return this.Source;
    }

    public String getDestination() {
        return this.Destination;
    }

    //Methods
    @Override
    public void Register() {

    }

    public void Requset() {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter your Source:");
        String source = in.nextLine();
        System.out.print("Enter your Destination:");
        String destination = in.nextLine();

        if (source.equals(destination)) {
            System.out.println("error! Please enter the correct path");
        } else {
            this.Source = source;
            this.Destination = destination;

            //insert new row in on hold trips table
            String query = "insert into OnHold_Trips (User,Source,Destination) "
                    + "values (?,?,?)";

            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                        "root", "");

                PreparedStatement ps = connect.prepareStatement(query);
                ps.setString(1, this.getPassword());
                ps.setString(2, this.Source);
                ps.setString(3, this.Destination);
                ps.executeUpdate();


            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public int Show_Offer() {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            Statement stmt = connection.createStatement();
            ResultSet RS = stmt.executeQuery("select * from Offers where Source= '"+ this.Source +"' and Destination= '"+this.Destination+ "';");
            if (!RS.next()) {
                System.out.println("No offers yet"); //data not exist
            } else {
                int id = RS.getInt("Driver_ID");
                int Price = RS.getInt("Price");
                ResultSet Result = stmt.executeQuery("select * from drivers where ID= '" + id + "';");
                if (!Result.next()) {
                    System.out.println("No data"); //data not exist
                } else {
                    driver.setNational_ID(Result.getString("National_ID"));
                    System.out.println("Driver(" + Result.getString("Username") + ") Offers you '" + Price + "LE' from "
                            + this.Source + " to " + this.Destination + "\nIf you accept this offer press 1 else press 0");
                    int Choice = scanner.nextInt();
                    if (Choice == 1) {
                        System.out.println("Trip will be start in a few minutes");
                        return 1;
                    } else if (Choice == 0) {
                        System.out.println("Wait,we'll find another driver closer...");
                        return 0;
                    }
                }
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return 3;
        }
        public void Rate_Driver () {
        }
        @Override
        public void Show () {

        }

}