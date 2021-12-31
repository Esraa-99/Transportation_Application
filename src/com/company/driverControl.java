package com.company;

import java.sql.*;
import java.util.Scanner;

public class driverControl {
    Driver d;
    //setters
    public void setNational_ID(String national_id){
        d.National_ID =national_id;
    }
    public void  setDriving_license(String drivinglicense){
        d.Driving_license =drivinglicense;
    }

    //getters
    public String setNational_ID(){
        return d.National_ID ;
    }
    public String setDriving_license(){
        return d.Driving_license ;
    }

    //Methods
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
        System.out.print("Enter your national ID: ");
        String nationalID = in.nextLine();
        System.out.print("Enter your driving licence: ");
        String drivingLicence = in.nextLine();
        //verified by admin
        //insert new row in table driver
        //PreparedStatement ps = null;
        String query = "insert into drivers (Username,Mobile,Email,Password,National_ID,driving_licence,Status) "
                + "values (?,?,?,?,?,?,?)";
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, mobileNum);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, nationalID);
            ps.setString(6, drivingLicence);
            ps.setString(7, "Pending");
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

        }
        return  email;
    }


    public void find_request(String national){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            Statement stmt = connection.createStatement();
            ResultSet RS = stmt.executeQuery("select Location from favourite_areas where Driver_ID= '"+ national + "';");
            //ResultSet Result;

            System.out.println("hhhhhhhhhhhhhh");
            d.National_ID=national;
            System.out.println(d.National_ID);

            if (RS.next())
            {
                System.out.println("location: "+RS.getString(1));
                ResultSet   Result = stmt.executeQuery("select * from OnHold_Trips where Source= '"+ RS.getString(1) + "';");
                if (!Result.next()) {
                    System.out.println("there is no requests now"); //data not exist
                } else {
                    System.out.println("you have a new request from "+Result.getString("Source") +" to "+Result.getString("Destination"));
                    Offer(Result.getString("Source"),Result.getString("Destination"),Result.getString("User"));
                }
                // list.add(result.getString(1));

            }
            //Result.close();
            //RS.close();
            //connection.close();
        }catch(Exception e){
            System.out.println(e);
        }

    }


    int Offer(String source,String destination,String User_ID) {


        Scanner in = new Scanner(System.in);
        System.out.print("Set your offer here:");
        int price = in.nextInt();

        //insert new row in offers table
        String query = "insert into Offers (Driver_ID,Source,Destination,Price,User) "
                + "values (?,?,?,?,?)";

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, d.National_ID);
            ps.setString(2, source );
            ps.setString(3, destination);
            ps.setInt(4, price);
            ps.setString(5, User_ID);
            ps.executeUpdate();

            // connect.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return price;
    }


    String getNationIDByEmail(String mail){
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation", "root", "");
            String query = "SELECT National_ID FROM drivers where Email='" + mail + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            String national="";
            while (rs.next())
            {
                national = rs.getString("National_ID");
            }
            return national;
        }catch(Exception e){ return "";}}

    void Put_FaviorateSource_Areas(String National_ID,String Location){
        try{
            // Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation","root","");
            PreparedStatement preparedStmt = con.prepareStatement("INSERT into favourite_areas(`Location`, `Driver_ID`) VALUES (?,?) ");
            preparedStmt.setString(1,Location);
            preparedStmt.setString(2, National_ID);
            preparedStmt.executeUpdate();
            con.close();

        }

        catch(Exception e){ System.out.println(e);}
    }
}
