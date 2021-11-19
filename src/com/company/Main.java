package com.company;

import java.sql.PreparedStatement;
import java.sql.*;
public class Main {

    public static void main(String[] args)  {

         User user = new User();
        user.setSource("giza");
        String Source =user.getSource();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("in try");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost3303/transportation", "root", "sara@sql1999A");

            String sql = "SELECT * FROM favourite_areas WHERE Location = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, Source);
            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                System.out.println("No Data Found For This Driver"); //data not exist
            } else {
                System.out.println("aaaaaaaaaa");
                String id = result.getString("Driver_ID");
                System.out.println(id);
            }

            connection.close();
        }catch(Exception e){
            System.out.println("here");
        }
    }
}
