package com.company;
import java.util.Scanner;
import java.sql.*;
public class Main {

    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
         User user = new User();
         Driver driver = new Driver();

        user.Requset();
        driver.find_request();
        if(user.Show_Offer()==1){
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                        "root", "");

                Statement stmt = connection.createStatement();
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                        "root", "");

                PreparedStatement ps = connect.prepareStatement("insert into trip (ID,source,destination,rate,user_id,driver_id) "
                        + "values (?,?,?,?,?,?)");
                Trip trip = new Trip();
                ps.setInt(1, trip.getTrip_ID());
                ps.setString(2, trip.getSource());
                ps.setString(3, trip.getDestination());
                ps.setFloat(4, trip.getRatingOfTrip());
                ps.setInt(5, trip.getUser_id());
                ps.setInt(6, trip.getDriver_id());
                ps.executeUpdate();
                 stmt.executeQuery("delete from Offers where Source= '" + user.getSource() +
                        "' and Destination= '" + user.getDestination() +"';");

                 stmt.executeQuery("delete * from OnHold_Trips where Source= '" + user.getSource() +
                        "' and Destination= '" + user.getDestination() + "';");

                connection.close();
            }catch(Exception e){
                System.out.println(e);
            }
        }

    }
}
