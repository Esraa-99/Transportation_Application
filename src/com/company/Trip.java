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
    private String Trip_ID;

    //setters
    private void setRatingOfTrip(String rateoftrip){
        this.RatingOfTrip = rateoftrip;
    }
    private void setSource(String source){
        this.Source = source;
    }
    private void setDestination(String destination){
        this.Destination = destination;
    }
    private void setTrip_ID(String trip_id){
        this.Trip_ID = trip_id;
    }
    //getters
    private String getRatingOfTrip(){
        return this.RatingOfTrip ;
    }
    private String getSource(){
        return this.Source;
    }
    private String getDestination(){
        return this.Destination ;
    }
    private String getTrip_ID(){
        return this.Trip_ID;
    }
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
