package com.company;

import java.util.Scanner;
import java.sql.*;
public class Driver  extends Person implements Registration,Show_Rating {
    private String National_ID;
    private String Driving_license;
    private String[] FaviorateSource_Areas={};

    //setters
    public void setNational_ID(String national_id){
        this.National_ID =national_id;
    }
    public void  setDriving_license(String drivinglicense){
        this.Driving_license =drivinglicense;
    }

    //getters
    public String setNational_ID(){
        return this.National_ID ;
    }
    public String setDriving_license(){
        return this.Driving_license ;
    }

    //Methods
    public void Register() {

    }
    public void find_request(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            Statement stmt = connection.createStatement();
            ResultSet RS = stmt.executeQuery("select * from favourite_areas where National_ID= '"+ National_ID + "';");
            ResultSet Result = stmt.executeQuery("select * from OnHold_Trips where Source= '"+ RS + "';");
            if (!Result.next()) {
                System.out.println("there is no requests now"); //data not exist
            } else {
                System.out.println("you have a new request from "+Result.getString("Source") +" to "+Result.getString("Destination"));
                Offer(Result.getString("Source"),Result.getString("Destination"),RS.getInt("Driver_ID"));
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }

    }
    int Offer(String source,String destination,int Driver_ID){
        Scanner in = new Scanner(System.in);
        System.out.print("Set your offer here:");
        int price = in.nextInt();

        //insert new row in offers table
        String query = "insert into Offers (Driver_ID,Source,Destination,Price) "
                + "values (?,?,?,?)";

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, Driver_ID);
            ps.setString(2, source );
            ps.setString(3, destination);
            ps.setInt(4, price);
            ps.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return price;
    }

     void Put_FaviorateSource_Areas(){

    }

    @Override
    public void Show() {

    }
}

