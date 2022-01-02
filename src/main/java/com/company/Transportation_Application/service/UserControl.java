package com.company.Transportation_Application.service;

import com.company.Transportation_Application.dao.ShowAverageRate;
import com.company.Transportation_Application.dao.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;
import java.util.Scanner;
@Service
public class UserControl {
    User user;
    public String birthday;
    DriverControl driver = new DriverControl();
    @PostMapping("service/UserControl/Register")
    public String Register(@JsonProperty String userName,@JsonProperty String mobileNum,
                           @JsonProperty String email,@JsonProperty String password,@JsonProperty String birthdate)  {

        //insert new row in table user
        String query = "insert into users (Username,Mobile,Email,Password,status,birthdate) "
                + "values (?,?,?,?,?,?)";

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, mobileNum);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, "active");
            ps.setString(6, birthdate);
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  email;
    }
    @GetMapping("service/UserControl/isFirstTripforUser")
    public boolean isFirtTripforUser(@JsonProperty String email){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            Statement stmt = connection.createStatement();
            ResultSet RS = stmt.executeQuery("select * from trip where User ='"+ email+"';");
            if (!RS.next()){
                return true;
            }else {
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @PostMapping
    @RequestMapping("service/UserControl/Request")
    public String Requset(@JsonProperty String email1,@JsonProperty String source,
                          @JsonProperty String destination,@JsonProperty String share_trip) {

        if (!source.equals(destination)) {
            user.Source = source;
            user.Destination = destination;

            //insert new row in on hold trips table
            String query = "insert into onhold_trips (User,Source,Destination,shareTrip) "
                    + "values (?,?,?,?)";

            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                        "root", "");

                PreparedStatement ps = connect.prepareStatement(query);

                ps.setString(1, email1);
                ps.setString(2, user.Source);
                ps.setString(3, user.Destination);
                ps.setString(4,share_trip);
                ps.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  user.Source;
    }
    @GetMapping("service/UserControl/Show_Offer")
    public String[] Show_Offer(@JsonProperty String email1,@JsonProperty int Choice) {

        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            Statement stmt = connection.createStatement();
            ResultSet RS = stmt.executeQuery("select * from offers where User ='"+ email1+"';");
            if (!RS.next()) {
                System.out.println("No offers yet"); //data not exist
            } else {
                String id = RS.getString("Driver_ID");
                String id_user = RS.getString("User");
                String date = RS.getString("time");
                int Price = RS.getInt("Price");

                user.Source = RS.getString("Source");
                user.Destination = RS.getString("Destination");
                String[]  arr={/*0*/id,/*1*/email1,/*2*/user.Source,/*3*/user.Destination,/*4*/id_user,/*5*/date, /*6*/String.valueOf(Price)};
                ResultSet Result = stmt.executeQuery("select * from drivers where National_ID='" + id + "';");
                if (!Result.next()) {
                    System.out.println("No data"); //data not exist
                } else {
                    driver.setNational_ID(Result.getString("National_ID"));
                    ShowAverageRate h=new ShowAverageRate();
                    h.Show(id);
                    System.out.println("Driver(" + Result.getString("Username") + ") Offers you '" + Price + "LE' from "
                            + user.Source + " to " + user.Destination );

                    if (Choice == 1) {
                        try {

                            // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            //     "root", "");
                            Statement Stmt = null;
                            PreparedStatement ps;

                            Stmt = connection.createStatement();
                            String query = "delete from offers where Source= '"+ user.Source +"' and Destination= '"+user.Destination+ "' and User='"+email1+"';";
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
                            String query = "delete from onhold_trips where Source= '"+ user.Source +"' and Destination= '"+user.Destination+ "' and User='"+email1+"';";
                            ps = connection.prepareStatement(query);
                            ps.executeUpdate();

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        return arr;

                    } else if (Choice == 0) {

                        try {

                            Statement Stmt = null;
                            PreparedStatement ps;

                            Stmt = connection.createStatement();
                            String query = "delete from offers where Source= '"+ user.Source +"' and Destination= '"+user.Destination+ "'";
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

}
