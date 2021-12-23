package com.company;

import java.sql.*;
import java.util.Scanner;

public class User extends Person implements Registration {
    private String Source;
    private String Destination;
    Driver driver = new Driver();

    //setters
   public void setSource(String source){
        this.Source = source;
    }

    public void setDestination(String destination) {
        this.Destination = destination;
    }

    //getters
   String getSource(){
        return this.Source;
    }
    public String getDestination(){
        return this.Destination ;
    }
  
    //Methods
    @Override
    public String Register() {
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
        String query = "insert into users (Username,Mobile,Email,Password,status) "
                + "values (?,?,?,?,?)";

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, mobileNum);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, "active");
            ps.executeUpdate();
            System.out.println("the recent user has registered");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
return  email;
    }

    public void Requset(String email1) {

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
               /* System.out.println("enter the email you wont to use in this trip");
                String email1=in.nextLine();*/

                ps.setString(1, email1);
                ps.setString(2, this.Source);
                ps.setString(3, this.Destination);
                ps.executeUpdate();


            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public String[] Show_Offer(String email1) {

        //arr = new String[];

        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            Statement stmt = connection.createStatement();
            ResultSet RS = stmt.executeQuery("select * from Offers where User ='"+ email1+"';");
            if (!RS.next()) {
                System.out.println("No offers yet"); //data not exist
            } else {
                String id = RS.getString("Driver_ID");
                int Price = RS.getInt("Price");
              // String price1= String.valueOf((Price));
                this.Source = RS.getString("Source");
                this.Destination = RS.getString("Destination");
                String[]  arr={id,email1,this.Source,this.Destination};
                ResultSet Result = stmt.executeQuery("select * from drivers where National_ID='" + id + "';");
                if (!Result.next()) {
                    System.out.println("No data"); //data not exist
                } else {
                    driver.setNational_ID(Result.getString("National_ID"));
                    ShowAverageRate h=new ShowAverageRate();
                    h.Show(id);
                    System.out.println("Driver(" + Result.getString("Username") + ") Offers you '" + Price + "LE' from "
                            + this.Source + " to " + this.Destination + "\nIf you accept this offer press 1 else press 0");


                    int Choice = scanner.nextInt();
                    if (Choice == 1) {
                        System.out.println("Trip will be start in a few minutes");


                        try {

                            // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            //     "root", "");
                            Statement Stmt = null;
                            PreparedStatement ps;

                            Stmt = connection.createStatement();
                            String query = "delete from Offers where Source= '"+ this.Source +"' and Destination= '"+this.Destination+ "' and User='"+email1+"';";
                            ps = connection.prepareStatement(query);
                            ps.executeUpdate();

                        } catch (Exception e) {
                            System.out.println(e);
                        }

                        try {

                            // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            //     "root", "");
                            Statement Stmt = null;
                            PreparedStatement ps;

                            Stmt = connection.createStatement();
                            String query = "delete from onhold_trips where Source= '"+ this.Source +"' and Destination= '"+this.Destination+ "' and User='"+email1+"';";
                            ps = connection.prepareStatement(query);
                            ps.executeUpdate();

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        return arr;
                    } else if (Choice == 0) {

                        try {

                           // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                               //     "root", "");
                            Statement Stmt = null;
                            PreparedStatement ps;

                            Stmt = connection.createStatement();
                            String query = "delete from Offers where Source= '"+ this.Source +"' and Destination= '"+this.Destination+ "'";
                            ps = connection.prepareStatement(query);
                            ps.executeUpdate();

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        System.out.println("Wait,we'll find another driver closer...");
                        return null;
                    }
                }
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
        }
        public void Rate_Driver () {
        }


}