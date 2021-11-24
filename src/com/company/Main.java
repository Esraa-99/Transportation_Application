package com.company;
import java.util.Scanner;

public class Main {

public static void main(String[] args) {
        Scanner int_scanner = new Scanner(System.in);
        Scanner string_scanner = new Scanner(System.in);
        int choice;boolean flag=true;
        System.out.println("1-Register");
        System.out.println("2-Login");

        System.out.println("Enter Your Choice:");
        choice = int_scanner.nextInt();

        switch (choice){
            case 1:
                System.out.println("Register as User(1) or Driver(2)");
                choice = int_scanner.nextInt();
                switch (choice){
                    case 1:
                        User user = new User();
                        user.Register();
                        break;
                    case 2:
                        Driver driver = new Driver();
                        driver.Register();
                        break;
                }
                break;
            case 2:
                Person person = new Person();
                System.out.println("Enter Email");
                String email = string_scanner.nextLine();
                System.out.println("Enter Password");
                String pass = string_scanner.nextLine();

                int userType = person.Login(email,pass);

                switch (userType){
                    case 1:
                        //User
                        User user= new User();
                        while (flag){
                            System.out.println("User Panel");
                            System.out.println("==========");
                            System.out.println("1-Request");
                            System.out.println("2-Show Offers");
                            System.out.println("3-Exit");

                            System.out.println("Enter Choice: ");
                            choice = int_scanner.nextInt();

                            switch (choice){
                                case 1:
                                    user.Requset();
                                    break;
                                case 2:
                                    user.Show_Offer();
                                    break;
                                case 3:
                                    flag=false;
                                    break;
                            }
                        }
                        break;
                    case 2:
                        //Driver
                        Driver driver = new Driver();
                        while (flag) {
                            System.out.println("Driver Panel");
                            System.out.println("==========");
                            System.out.println("1-Add Favourite Location");
                            System.out.println("2-Find Offer");
                            System.out.println("3-Exit");

                            System.out.println("Enter Choice: ");
                            choice = int_scanner.nextInt();

                            switch (choice) {
                                case 1:
                                    System.out.println("enter fav area");
                                    String area = string_scanner.nextLine();
                                    driver.Put_FaviorateSource_Areas(driver.getNationIDByEmail(email), area);
                                    break;
                                case 2:
                                    driver.find_request(driver.getNationIDByEmail(email));
                                    break;
                                case 3:
                                    flag = false;
                                    break;
                            }
                        }
                    case 3:
                        //Admin
                        Admin admin = new Admin();
                        int national;
                        while(flag){
                            System.out.println("Admin Panel");
                            System.out.println("===========");
                            System.out.println("1-Verify Account");
                            System.out.println("2-Suspend Account");
                            System.out.println("3-Exit");

                            System.out.print("Enter Your Choice: ");
                            choice = int_scanner.nextInt();

                            switch (choice){
                                case 1:
                                    admin.ShowPendingAccounts();
                                    System.out.println("Enter The NationalID Of the Account To be verified:");
                                    national = int_scanner.nextInt();
                                    admin.Verify(national);
                                    break;
                                case 2:
                                    System.out.println("Enter The NationalID Of the Account To be Suspended:");
                                    national = int_scanner.nextInt();
                                    admin.Suspend(national);
                                    break;
                                case 3:
                                    flag=false;
                                    break;
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
        }
    }
}


