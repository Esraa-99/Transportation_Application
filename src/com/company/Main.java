package com.company;
import java.util.Scanner;

public class Main {
    static int ratioOfDiscount=0;
public static void main(String[] args) {
    String email;
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
                        email=user.Register();
                        d(1,email);
                        break;
                    case 2:
                        Driver driver = new Driver();
                        email= driver.Register();
                        d(2,email);
                        break;
                }
                break;
            case 2:
                Person person = new Person();
                System.out.println("Enter Email");
                 email = string_scanner.nextLine();
                System.out.println("Enter Password");
                String pass = string_scanner.nextLine();

                int userType1 = person.Login(email,pass);
                d(userType1,email);


                break;
        }
    }


    static  void d(int userType,String email)
    {    Scanner int_scanner = new Scanner(System.in);
        Scanner string_scanner = new Scanner(System.in);
        int choice;boolean flag=true;
        Admin admin = Admin.getInstance();
        switch (userType){
            case 0:
                System.out.println("you cant login ");
                break;
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
                            if(admin.isfav(user.Requset(email))){
                                ratioOfDiscount+=10;
                                System.out.println("You will have discount 10% when you start this trip :) ");
                            }
                            if(user.isFirtTripforUser(email)){
                                ratioOfDiscount+=10;
                                System.out.println("You will have discount 10% because this is your first trip, Enjoy :) ");
                            }

                            break;
                        case 2:
                            String arr1[]=  user.Show_Offer(email);
                            Trip h=new Trip();
                            if(arr1!=null) {
                                h.Start();
                                h.End(arr1[0], arr1[1], arr1[2], arr1[3]);
                                if(user.isDateoftripisuserbirthday(user.birthday,arr1[4],arr1[2], arr1[3])){
                                    ratioOfDiscount +=10;
                                }
                                if(user.isDateoftripisholiday(arr1[5])){
                                    ratioOfDiscount +=5;
                                }
                            }
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
                    System.out.println("3-show list rate");
                    System.out.println("4-Exit");

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
                            ShowListOfRating h=new ShowListOfRating();
                            h.Show(email);
                            break;
                        case 4:
                            flag = false;
                            break;
                    }
                }
            case 3:
                //Admin
                int national;
                while(flag){
                    System.out.println("Admin Panel");
                    System.out.println("===========");
                    System.out.println("1-Verify Account");
                    System.out.println("2-Suspend Account");
                    System.out.println("3-Add specific areas");
                    System.out.println("4-Exit");

                    System.out.print("Enter Your Choice: ");
                    choice = int_scanner.nextInt();

                    switch (choice){
                        case 1:
                            int h=admin.ShowPendingAccounts();
                            if(h==1)
                            {
                                System.out.println("Enter The NationalID Of the Account To be verified:");
                                national = int_scanner.nextInt();
                                admin.Verify(national);
                            }

                            break;
                        case 2:
                            System.out.println("Enter The Email Of the Account To be Suspended:");

                            String email2=int_scanner.nextLine();
                            email2 = int_scanner.nextLine();
                            System.out.println("1 if he driver,enter 2 if he user  ");
                            int choice5=int_scanner.nextInt();

                            admin.Suspend(email2,choice5);
                            break;
                        case 3:
                            admin.AddspecificAreas();
                            break;
                        case 4:
                            flag=false;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + choice);
                    }
                }
                break;
            default:
                break;
        }
    }
}


