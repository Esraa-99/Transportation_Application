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

    }
    int Offer(){
    return 0;
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
        }catch(Exception e){ return -1;}
    }
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
    @Override
    public void Show() {

    }
}

