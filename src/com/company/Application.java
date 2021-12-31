package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    Trip t;
    public userControl user = new userControl();
    public driverControl driver = new driverControl();
    String url="jdbc:mysql://localhost:3306/transportation";
    String username = "root";
    String password = "";
    //String driver_id1="1";

    //Methods
    public void Start(){
        System.out.println("trip is start ");
    }

    public void End(String id1,String emali2,String Source3,String destination4){
        System.out.println("trip is end ");
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter your rating for the trip between 1 to 5 ");
        t.RatingOfTrip =sc.nextLine();

        List<String> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("connected to the database ");

            String query = " insert into trip (source, destination,rate,User_id , driver_id)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, Source3);
            preparedStmt.setString (2, destination4);
            preparedStmt.setString   (3, t.RatingOfTrip);
            preparedStmt.setString(4, emali2);
            preparedStmt.setString(5, id1);


            preparedStmt.execute();


        } catch (SQLException e) {
            System.out.println("Opps,error!");
            System.out.println("Error: " + e.getMessage());
        }

    }
    public boolean isDoubleTrip(String userid,String Source,String destination){
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement stmt = connection.createStatement();
            String query = " select * from holiday where User_id = '"+userid+
                    "' and source ='"+Source+"'and destination ='"+destination+"';";
            ResultSet RS = stmt.executeQuery(query);
            if (!RS.next()){
                String State = RS.getString("state");
                if(State.equals("double"))
                    return true;
                else
                    return false;
            }else{
                return false;
            }
        }
        catch (SQLException e) {
            System.out.println("Opps,error!");
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    public boolean tripOnHoliday(String B_date,String userid,String Source,String destination){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");
            Statement stmt = connection.createStatement();
            String query = " select * from trip where date = '"+ B_date+"' and User_id = '"+userid+
                    "' and source ='"+Source+"'and destination ='"+destination+"';";
            ResultSet RS = stmt.executeQuery(query);
            if (!RS.next())
                return true;
            else
                return false;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    public boolean dateOfHoliday(String dateoftrip){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");
            Statement stmt = connection.createStatement();
            String query = " select * from holiday where date = '"+ dateoftrip+"';";
            ResultSet RS = stmt.executeQuery(query);
            if (!RS.next())
                return true;
            else
                return false;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
