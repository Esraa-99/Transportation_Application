package com.company;
import java.util.Scanner;
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

  public void ShowEventLog1(){
        try{
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation","root","");
            String sql = "SELECT * FROM events";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            System.out.println("Events:");
            System.out.println("=========");
            if (result.next()) {

                System.out.println("Username: " + result.getString("event_name"));
                System.out.println("Mobile: " + result.getString("event_time"));
                System.out.println("Email: " + result.getString("Email"));
                System.out.println("Password: " + result.getString("username"));
                System.out.println("National ID: " + result.getString("type"));
                System.out.println("-----------------------------------");
            }else{System.out.println("No Events has done");}
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }
    /*public void AddDiscount(String area,float discount){
        try{
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation","root","");
            PreparedStatement preparedStmt = con.prepareStatement("Insert into discount(place,discount) values (?,?)");
            preparedStmt.setString   (1, area);
            preparedStmt.setFloat   (2, discount);
            preparedStmt.executeUpdate();
            con.close();
        }
        catch(Exception e){ System.out.println(e);}  }*/

  public void AddspecificAreas(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of areas which you want to add");
        int number = scan.nextInt();
        for(int i=0; i<number ;i++){
            String area = scan.nextLine();
        try {
            String query = "insert into discount (place) "
                    + "values (?)";
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                    "root", "");

            PreparedStatement ps = connect.prepareStatement(query);

            ps.setString(1, area);

            ps.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }}
    public boolean isfav(String destination){
        Connection con= null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation","root","");
            String sql = "SELECT * FROM discount WHERE place = '"+destination+"' ";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            if(!result.next()){
                return false;
            }else{
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }


    ResultSet Select(String query) throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/transportation","root","");
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }
    public void ShowEventLog()  {
        Connection con= null;

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation","root","");
        String sql = "SELECT * FROM offers";
        ResultSet result = Select(sql);

        System.out.println("Events:");
        System.out.println("=========");
        while (result.next()) {
            System.out.println("Event Name: Captin Put price To the ride");
            System.out.println("Event Time: " + result.getString("time"));
            System.out.println("Username: " + result.getString("user"));
            System.out.println("Price: " + result.getString("price"));
            System.out.println("User type: Driver");
            System.out.println("-----------------------------------");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
     //   Connection con= null;

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation","root","");
        //Second Query
         String  sql = "SELECT startTime,users.username FROM `trip` INNER JOIN users ON users.Email = trip.User_id";
            ResultSet result = Select(sql);
        while (result.next()) {
            System.out.println("Event Name: User accept captin price");
            System.out.println("Event Time: " + result.getString("startTime"));
            System.out.println("Username: " + result.getString("username"));
            System.out.println("User type: Client");
            System.out.println("-----------------------------------");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Third Query
        //Connection con= null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation","root","");
        String  sql = "SELECT startTime,users.username as 'user' ,drivers.Username as 'captin' FROM (`trip` INNER JOIN users) INNER JOIN drivers ON (users.Email = trip.User_id) AND (trip.driver_id = drivers.National_ID)";
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet result1 = statement.executeQuery();
        while (result1.next()) {
            System.out.println("Event Name: Captain arrived to user location");
            System.out.println("Event Time: " + result1.getString("startTime"));
            System.out.println("User: " + result1.getString("user"));
            System.out.println("Captin:" + result1.getString("captin") );
            System.out.println("-----------------------------------");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Fourth Query
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transportation","root","");
         String sql = "SELECT endTime,users.username as 'user' ,drivers.Username as 'captin' FROM (`trip` INNER JOIN users) INNER JOIN drivers ON (users.Email = trip.User_id) AND (trip.driver_id = drivers.National_ID)";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result2 = statement.executeQuery();
        while (result2.next()) {
            System.out.println("Event Name: Captain arrived to user destination");
            System.out.println("Event Time: " + result2.getString("endTime"));
            System.out.println("User: " + result2.getString("user"));
            System.out.println("Captin:" + result2.getString("captin") );
            System.out.println("-----------------------------------");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
