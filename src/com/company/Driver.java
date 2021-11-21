package com.company;

import java.sql.*;

public class Driver  extends Person implements Registration,Show_Rating {
    private String National_ID;
    private String Driving_license;


    //setters
    private void  setNational_ID(String national_id){
        this.National_ID =national_id;
    }
    private void  setDriving_license(String drivinglicense){
        this.Driving_license =drivinglicense;
    }

    //getters
    private String setNational_ID(){
        return this.National_ID ;
    }
    private String setDriving_license(){
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

