package com.company;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    String url="jdbc:mysql://localhost:3306/transportation";
    String username = "root";
    String password = "";
    String driver_id1="1";
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
    public void Start(){
       System.out.println("trip is start ");
    }

   public void End(String id1,String emali2,String Source3,String destination4,String price,String offerprice){
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
       LocalDateTime now = LocalDateTime.now();
       System.out.println(dtf.format(now));
       System.out.println("trip is end ");
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter your rating for the trip between 1 to 5 ");
        RatingOfTrip =sc.nextLine();

        List<String> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("connected to the database ");

            String query = " insert into trip (source, destination,rate,User_id ,price,driver_id,startTime,endTime,driverPrice)"
                    + " values (?, ?, ?, ?, ?,?,?,?,?)";
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now1 = LocalDateTime.now();
            System.out.println(dtf1.format(now1));
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, Source3);
            preparedStmt.setString (2, destination4);
            preparedStmt.setString (3, RatingOfTrip);
            preparedStmt.setString(4, emali2);

            preparedStmt.setString(5, price);
            preparedStmt.setString(6, id1);
            preparedStmt.setString(7, dtf1.format(now));
            preparedStmt.setString(8, dtf1.format(now1));
            preparedStmt.setString(9, offerprice);
            // execute the preparedstatement

            preparedStmt.execute();


        } catch (SQLException e) {
            System.out.println("Opps,error!");
            System.out.println("Error: " + e.getMessage());
        }
       String jdbcUrl = "jdbc:mysql://localhost:3306/transportation";
       String username1 = "root";
       String password1 = "";
       String sql = "update drivers set location='"+ destination4+"', tripState='off' where National_ID='"+id1+"';";

       try (Connection conn = DriverManager.getConnection(jdbcUrl, username1, password1);
            PreparedStatement stmt = conn.prepareStatement(sql);) {

           stmt.executeUpdate();

           System.out.println("Database updated successfully ");
       } catch (SQLException e) {
           e.printStackTrace();
       }


    }

    public void AddPricewithDiscount(String id1,String emali2,String Source3,String destination4,String priceoffer){
           String jdbcUrl = "jdbc:mysql://localhost:3306/transportation";
           String username1 = "root";
           String password1 = "";
            System.out.println("connected to the database ");

            String query = " update trip set driverPrice ='"+priceoffer+"where source ='"+Source3+"' and destination ='"+destination4+"' and User_id ='"+emali2+"' and driver_id ='"+id1+"';";


        try (Connection conn = DriverManager.getConnection(jdbcUrl, username1, password1);
             PreparedStatement stmt = conn.prepareStatement(query);) {

            stmt.executeUpdate();

            System.out.println("Database updated successfully ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public boolean isdoubletrip(String State){

       /* try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement stmt = connection.createStatement();

            String query = " select * from trip where User = '"+userid+
                    "' and Source ='"+Source+"'and Destination ='"+destination+"';";
            ResultSet RS = stmt.executeQuery(query);
            if (!RS.next()){
                String State = RS.getString("shareTrip");*/
                if(State.equals("yes"))
                {
                    return true;}
                else{
                    return false;}
            /*}else{
                return false;
            }*/
        /*}
        catch (SQLException e) {
            System.out.println("Opps,error!");
            System.out.println("Error: " + e.getMessage());
        }
        return false;*/
    }
    public boolean isDateoftripisuserbirthday(String B_date,String userid,String Source,String destination){

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            Statement stmt = connection.createStatement();
            String query = " select * from trip where date = '"+ B_date+"' and User_id = '"+userid+
                    "' and source ='"+Source+"'and destination ='"+destination+"';";
            ResultSet RS = stmt.executeQuery(query);
            if (RS.next())
            {return true;}
            else
            { return false;}


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    public boolean isDateoftripisholiday(String dateoftrip){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");
            Statement stmt = connection.createStatement();
            String query = " select * from holiday where date = '"+ dateoftrip+"';";
            ResultSet RS = stmt.executeQuery(query);
            if (RS.next())
            { return true;}
            else
            {return false;}

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;

    }

}


