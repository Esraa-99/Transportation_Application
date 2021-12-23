package com.company;

import java.sql.*;

public class Admin extends Person{
    //Methods
    private static Admin uniqueInstance;
    private Admin(){}
    public static Admin getInstance()
    {
        if (uniqueInstance == null) {
            uniqueInstance = new Admin();
        }
        return uniqueInstance;
    }
    public int ShowPendingAccounts(){
        try{
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation","root","");
            String sql = "SELECT * FROM drivers WHERE Status = 'pending' ";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            System.out.println("Accounts:");
            System.out.println("=========");
            if (result.next()) {
                System.out.println("Username: " + result.getString("Username"));
                System.out.println("Mobile: " + result.getString("Mobile"));
                System.out.println("Email: " + result.getString("Email"));
                System.out.println("Password: " + result.getString("Password"));
                System.out.println("National ID: " + result.getString("National_ID"));
                System.out.println("Licence No: " + result.getString("driving_licence"));
                System.out.println("-----------------------------------");
                return  1;
            }else{System.out.println("No Accounts Need to be Verified");}

            con.close();
            return 2;
        }
        catch(Exception e){ System.out.println(e);}
        return  3;
    }
    public void Verify(int National_ID){
        try{
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation","root","");
            PreparedStatement preparedStmt = con.prepareStatement("Update drivers set status='active' where National_ID=?");
            preparedStmt.setInt   (1, National_ID);
            preparedStmt.executeUpdate();
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }
   public void Suspend(String Email1,int choice){
       try{

           if(choice==1)
           {  Connection con= DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/transportation","root","");
               PreparedStatement preparedStmt = con.prepareStatement("Update drivers set status='suspended' where Email=?");
               preparedStmt.setString   (1, Email1);
               preparedStmt.executeUpdate();
               con.close();

           }
           else if(choice==2)
           {
               Connection con= DriverManager.getConnection(
                       "jdbc:mysql://localhost:3306/transportation","root","");
               PreparedStatement preparedStmt = con.prepareStatement("Update users set status='suspended' where Email=?");
               preparedStmt.setString   (1, Email1);
               preparedStmt.executeUpdate();
               con.close();
           }

       }
       catch(Exception e){ System.out.println(e);}
    }
}
