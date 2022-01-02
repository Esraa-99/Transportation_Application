package com.company;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class User extends Person implements Registration {
    private String Source;
    private String Destination;
    public String birthday;
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
    public String Register()  {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String userName = in.nextLine();
        System.out.print("Enter your mobile number: ");
        String mobileNum = in.nextLine();
        System.out.print("Enter your email: ");
        String email = in.nextLine();
        System.out.print("Enter your password: ");
        String password = in.nextLine();

        System.out.print("Enter your birthday: ");
        String birthdate = in.nextLine();
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
            System.out.println("the recent user has registered");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
return  email;
    }
    public boolean isFirtTripforUser(String email){
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
           System.out.println(e);
       }
       return false;
    }
    public String Requset(String email1) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter your Source:");
        String source = in.nextLine();
        System.out.print("Enter your Destination:");
        String destination = in.nextLine();
         System.out.println("if you need share trip enter 'yes' else enter 'no'");
         String share_trip = in.nextLine();

        if (source.equals(destination)) {
            System.out.println("error! Please enter the correct path");
        } else {
            this.Source = source;
            this.Destination = destination;

            //insert new row in on hold trips table
            String query = "insert into onhold_trips (User,Source,Destination,shareTrip) "
                    + "values (?,?,?,?)";

            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                        "root", "");

                PreparedStatement ps = connect.prepareStatement(query);
               /* System.out.println("enter the email you wont to use in this trip");
                String email1=in.nextLine();*/

                ps.setString(1, email1);
                ps.setString(2, this.Source);
                ps.setString(3, this.Destination);
                ps.setString(4,share_trip);
                ps.executeUpdate();


            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        return this.Source ;
    }

    public String[] Show_Offer(String email1) {

        //arr = new String[];

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

              //momen
              //String price1= String.valueOf((Price));
               // String priceoffer=String.valueOf((Price));
                //String[]  arr={id,email1,this.Source,this.Destination,price1};


                this.Source = RS.getString("Source");
                this.Destination = RS.getString("Destination");
                String shareTrip1="no";
                String[]  arr={/*0*/id,/*1*/email1,/*2*/this.Source,/*3*/this.Destination,/*4*/id_user,/*5*/date, /*6*/String.valueOf(Price),shareTrip1};
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
                            String query = "delete from offers where Source= '"+ this.Source +"' and Destination= '"+this.Destination+ "' and User='"+email1+"';";
                            ps = connection.prepareStatement(query);
                            ps.executeUpdate();

                        } catch (Exception e) {
                            System.out.println(e);
                        }

                        try {

                            // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                            //     "root", "");

                            try {
                                Scanner scanner1 = new Scanner(System.in);
                                Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                                        "root", "");

                                Statement stmt1 = connection.createStatement();
                                ResultSet RS1 = stmt.executeQuery("select * from onhold_trips where Source= '"+ this.Source +"' and Destination= '"+this.Destination+ "' and User='"+email1+"';");
                                if (!RS1.next()) {
                                    System.out.println("No offers yet"); //data not exist
                                } else {
                                     shareTrip1 = RS.getString("shareTrip");

                                   // arr[7]=shareTrip1;
                                    System.out.println(arr[7]);

                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }

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

                            Statement Stmt = null;
                            PreparedStatement ps;

                            Stmt = connection.createStatement();
                            String query = "delete from offers where Source= '"+ this.Source +"' and Destination= '"+this.Destination+ "'";
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


       public String getBirthday1(String Email){
           try {
               Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                       "root", "");
               Statement stmt = connection.createStatement();
               ResultSet RS = stmt.executeQuery("select * from users where Email= '" + Email + "';");
               if (RS.next()) {

                   String hh=RS.getString("birthdate");
                   System.out.println(hh);

                      return hh;
               } else {
                   System.out.println("Try to login again ^_^");
                   return  null;
               }

           } catch (SQLException e) {
               System.out.println("Erorr!"); //data not exist
               e.printStackTrace();
           }
           return null;
        }



}