package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trip {
   private User user = new User();
    private Driver driver = new Driver();
    private String RatingOfTrip;
    private String Source;
    private String Destination;
    private int Trip_ID;
    private int user_id;
    private int driver_id;

    //setters
   public void setRatingOfTrip(String rateoftrip){
        this.RatingOfTrip = rateoftrip;
    }
    public void setSource(String source){
        this.Source = source;
    }
    public void setDestination(String destination){
        this.Destination = destination;
    }
    public void setTrip_ID(int trip_id){
        this.Trip_ID = trip_id;
    }
    public void setUser_id(int user_id) {this.user_id = user_id;}
    public void setDriver_id(int driver_id) {this.driver_id = driver_id;}

    //getters
    public String getRatingOfTrip(){
        return this.RatingOfTrip ;
    }
    public String getSource(){
        return this.Source;
    }
    public String getDestination(){
        return this.Destination ;
    }
    public int getTrip_ID(){
        return this.Trip_ID;
    }
    public int getUser_id() {return user_id;}
    public int getDriver_id() {return driver_id;}
    //Methods
    public void Start(){ }

   public void End(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter your rating for the trip between 1 to 5 ");
        RatingOfTrip =sc.nextLine();
        String url="jdbc:mysql://localhost:3306/transportation";
        String username = "root";
        String password = "";
        String driver_id1="1";
        List<String> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("connected to the database ");

            String query = " insert into trip (source, destination,rate,user_id , driver_id)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, "helwan");
            preparedStmt.setString (2, "giza");
            preparedStmt.setString   (3, RatingOfTrip);
            preparedStmt.setString(4, "1");
            preparedStmt.setString(5, "1");

            // execute the preparedstatement
            preparedStmt.execute();




        } catch (SQLException e) {
            System.out.println("Opps,error!");
            e.printStackTrace();
        }

    }
}


