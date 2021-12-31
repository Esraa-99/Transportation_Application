package com.company;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.sql.*;
import java.util.Scanner;
public class Driver extends Person implements Registration {
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
            String query = "insert into drivers (Username,Mobile,Email,Password,National_ID,driving_licence,Status,location,tripState) "
                    + "values (?,?,?,?,?,?,?,?,?)";
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
                ps.setString(8, "cairo");
                ps.setString(9, "off");
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

           // System.out.println("hhhhhhhhhhhhhh");
            National_ID=national;
           System.out.println(National_ID+"   ");
           int i=1;
           if (RS.next())
            {String place1 =RS.getString(1);
                ResultSet RS1 = stmt.executeQuery("select location and tripState from drivers where National_ID= '"+ national + "';");
                if (RS1.next()) {
                    if(RS1.getString(1).equals(place1) && RS1.getString(2)!="on" )
                    System.out.println("location: " + place1+ RS1.getString(2));
                    ResultSet Result = stmt.executeQuery("select * from OnHold_Trips where Source= '" + place1 + "';");
                    if (!Result.next()) {
                        System.out.println("there is no requests now"); //data not exist
                    } else {
                        System.out.println("you have a new request from " + Result.getString("Source") + " to " + Result.getString("Destination"));
                        Scanner in = new Scanner(System.in);
                        System.out.print("Set your offer here:");
                        int price = in.nextInt();

                        if(Result.getString("shareTrip").equals("yes")){
                            System.out.println("you have a new 2 request share trip  from " + Result.getString("Source") + " to " + Result.getString("Destination"));
                            offer_sharetrip(Result.getString("Source"), Result.getString("Destination"), Result.getString("User"), Result.getString("shareTrip"),price);
                        }
                        else {
                            System.out.println("you have a new request from " + Result.getString("Source") + " to " + Result.getString("Destination"));
                        Offer(Result.getString("Source"), Result.getString("Destination"), Result.getString("User"), Result.getString("shareTrip"),price);
                    }}
                    // list.add(result.getString(1));
                }
                i++;
            }
            //Result.close();
            //RS.close();
            //connection.close();
        }catch(Exception e){
            System.out.println(e);
        }

    }
void offer_sharetrip(String source,String destination,String User_ID,String sharetrip,int price1) {
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                "root", "");
        Statement stmt = connection.createStatement();
        ResultSet Result1 = stmt.executeQuery("select * from OnHold_Trips where Source='" + source + "' and Destination='" + destination + "' and shareTrip='yes' and User !='" +User_ID+"';");
        if (!Result1.next()) {
            System.out.println("there is no requests now from this source to this destination "); //data not exist
        } else {
            String Source1=Result1.getString("Source");
            String destination1=Result1.getString("Destination");
            String user1=Result1.getString("User");
            String sharetrip1=Result1.getString("shareTrip");

            Offer(Source1, destination1, user1, sharetrip1,price1);
            Offer(source, destination, User_ID,sharetrip,price1);
        }


    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    int Offer(String source,String destination,String User_ID,String sharetrip,int price2) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        //insert new row in offers table
        String query = "insert into Offers (Driver_ID,Source,Destination,Price,User,time) "
                + "values (?,?,?,?,?,?)";

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, National_ID);
            ps.setString(2, source );
            ps.setString(3, destination);
            ps.setInt(4, price2);
            ps.setString(5, User_ID);
            ps.setString(6, dtf.format(now));
            ps.executeUpdate();

           // connect.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return price2;
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

