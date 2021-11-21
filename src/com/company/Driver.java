package com.company;

import java.sql.*;
import java.util.Scanner;
public class Driver  extends Person implements Registration,Show_Rating {
    private String National_ID;
    private String Driving_license;


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
    }
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
           

    int getNationIDByUsername(String username){
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation", "root", "");
            String query = "SELECT National_ID FROM drivers where Username='" + username + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int national=-1;
            while (rs.next())
            {
               national = rs.getInt("National_ID");
            }
            return national;
        }catch(Exception e){ return -1;}}

    void Put_FaviorateSource_Areas(int National_ID,String Location){
         try{
             Class.forName("com.mysql.jdbc.Driver");
             Connection con= DriverManager.getConnection(
                     "jdbc:mysql://localhost:3306/transportation","root","");
             PreparedStatement preparedStmt = con.prepareStatement("INSERT into favourite_areas(`Location`, `National`) VALUES (?,?) ");
             preparedStmt.setString(1,Location);
             preparedStmt.setInt(2, National_ID);
             preparedStmt.executeUpdate();
             con.close();
         }
         catch(Exception e){ System.out.println(e);}
     }
  
}

