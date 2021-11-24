package com.company;
import java.util.Scanner;
import java.sql.*;
public class Main {

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean end=false;
    String email1 ="";
    do {

        boolean isuser = false;
        boolean isdriver = false;
        User user = new User();
        Driver driver = new Driver();
        System.out.println(" enter 1 to register");
        System.out.println("enter 2 to login ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println(" enter 1 to register as user");
            System.out.println(" enter 2 to register as driver");
            System.out.println(" enter 3 to end ");
            int choice1 = scanner.nextInt();
            if (choice1 == 1) {


                user.Register();
                isuser = true;

            } else if (choice1 == 2) {

                driver.Register();
                isdriver = true;

            }
            else if(choice1==3)
            {
                end= false;
            }

        } else if (choice == 2) {
            System.out.println("enter email");
             email1 = scanner.nextLine();
            email1 = scanner.nextLine();
            System.out.println("enter passward");
            String passward1 = scanner.nextLine();
            Person person = new Person();

            if (person.Login(email1, passward1) == 1) {
                isuser = true;

            } else if (person.Login(email1, passward1) == 2) {
                isdriver = true;
            } else if (person.Login(email1, passward1) == 3) {
                int choice3 = 0;
            /*Admin admin=new Admin();
            System.out.println("enter 1 to verify");
            System.out.println("enter 2 to Suspend");
            int choice2=scanner.nextInt();
            if(choice2==1) {
                //admin.Verify();
            }


            else if (choice2==2) {
                System.out.println("enter national id");
                int national_id1=scanner.nextInt();
                admin.Suspend(national_id1);
            }*/


                boolean flag = true;
                int national;

                Admin admin = new Admin();
                while (flag) {
                    System.out.println("Admin Panel");
                    System.out.println("===========");
                    System.out.println("1-Verify Account");
                    System.out.println("2-Suspend Account");
                    System.out.println("3-Exit");

                    System.out.print("Enter Your Choice: ");
                    choice = scanner.nextInt();

                    switch (choice3) {
                        case 1:
                            admin.ShowPendingAccounts();
                            System.out.println("Enter The NationalID Of the Account To be verified:");
                            national = scanner.nextInt();
                            admin.Verify(national);
                            break;
                        case 2:
                            System.out.println("Enter The NationalID Of the Account To be Suspended:");
                            national = scanner.nextInt();
                            admin.Suspend(national);
                            break;
                        case 3:
                            flag = false;
                            break;
                    }
                }


            }

        }

    if (isuser) {
        user.Requset();
        if (user.Show_Offer() == 1) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                        "root", "");

                Statement stmt = connection.createStatement();
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation",
                        "root", "");

                PreparedStatement ps = connect.prepareStatement("insert into trip (ID,source,destination,rate,user_id,driver_id) "
                        + "values (?,?,?,?,?,?)");
                Trip trip = new Trip();
                ps.setInt(1, trip.getTrip_ID());
                ps.setString(2, trip.getSource());
                ps.setString(3, trip.getDestination());
                ps.setString(4, trip.getRatingOfTrip());
                ps.setInt(5, trip.getUser_id());
                ps.setInt(6, trip.getDriver_id());
                ps.executeUpdate();
                stmt.executeQuery("delete from Offers where Source= '" + user.getSource() +
                        "' and Destination= '" + user.getDestination() + "';");

                stmt.executeQuery("delete * from OnHold_Trips where Source= '" + user.getSource() +
                        "' and Destination= '" + user.getDestination() + "';");

                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    } else if (isdriver) {
        int num2=0;
        do {
            System.out.println("enter one if you wont enter fav source ");
            System.out.println("enter two to find request ");
            System.out.println("enter three to exit ");
             num2 = scanner.nextInt();

            if (num2 == 1) {
                System.out.println("enter fav area");
                String area = scanner.nextLine();
                area= scanner.nextLine();
                driver.Put_FaviorateSource_Areas(driver.getNationIDByUsername(email1), area);

            } else if (num2 == 2) {
                driver.find_request(driver.getNationIDByUsername(email1));
            }

        }while (num2!=3);
    }


    }while (end);
    }
    }


