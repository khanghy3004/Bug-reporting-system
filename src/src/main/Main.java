package main;

import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    // Object of ConnectionDB class
    static ConnectionDB connect = new ConnectionDB();
    static Connection cn = connect.getConnectDB();
    // Object of Person class
    static Person currentPerson = new Person();
    
    // Customer form
    static void customer(Customer KH) throws ParseException, Exception {
        boolean check = true;
        do {
            System.out.println("    HELLO "+KH.getFullname());
            System.out.println("-------------------------");
            System.out.println("| 1. My profile         |");
            System.out.println("| 2. My Bill            |");
            System.out.println("| 3. Make a report      |");
            System.out.println("| 4. Display my report  |");
            System.out.println("| 5. Log out            |");
            System.out.println("-------------------------");
            String choice = input.nextLine();
            switch(choice) {
                case "1": KH.displayProfile(); break;
                case "2": KH.displayBill(KH.getUsername()); break;
                case "3": KH.makeReport(KH.getUsername()); break;
                case "4": KH.displayReport(KH.getUsername());break;
                case "5": check = false; break;
                default: System.out.println("Please enter again (1-5)");
            }
        } while(check);   
    }
    // Frontend form
    static void frontend(Admin frontend) throws ParseException, Exception {
        boolean check = true;
        do {
            System.out.println("    HELLO "+frontend.getFullname());
            System.out.println("-----------------------------------------");
            System.out.println("| 1. My profile                         |");
            System.out.println("| 2. Display Customer Bill and Report   |");
            System.out.println("| 3. Make a report                      |");
            System.out.println("| 4. Log out                            |");
            System.out.println("-----------------------------------------");
            String choice = input.nextLine();
            switch(choice) {
                case "1": 
                    frontend.displayProfile(); 
                    break;
                case "2": 
                    frontend.customerOnBillandReport();   
                    break;
                case "3":
                    System.out.println("Enter Customer Username: ");
                    String user = input.nextLine();
                    frontend.makeReport(user); break;
                case "4": check = false; break;
                default: System.out.println("Please enter again (1-4)");
            }
        } while(check);  
    }
    // Backend form
    static void backend(Admin backend) throws ParseException {
        boolean check = true;
        do {
            System.out.println("    HELLO "+backend.getFullname());
            System.out.println("-----------------------------------------");
            System.out.println("| 1. My profile                         |");
            System.out.println("| 2. Display Customer Bill and Report   |");
            System.out.println("| 3. Report Handling                    |");
            System.out.println("| 4. Log out                            |");
            System.out.println("-----------------------------------------");
            String choice = input.nextLine();
            switch(choice) {
                case "1": 
                    backend.displayProfile(); 
                    break;
                case "2": 
                    backend.customerOnBillandReport();   
                    break;
                case "3":
                    backend.reportHandling(); break;
                case "4": check = false; break;
                default: System.out.println("Please enter again (1-4)");
            }
        } while(check);  
    }
    // Order manager form
    static void order(Admin order) throws Exception {
        boolean check = true;
        do {
            System.out.println("Hello "+order.getFullname());
            System.out.println("1. My profile");
            System.out.println("2. Insert a new customer bill");
            System.out.println("3. Log out");
            String choice = input.nextLine();
            switch(choice) {
                case "1": 
                    order.displayProfile(); 
                    break;
                case "2": 
                    order.insertNewBill();   
                    break;
                case "3": check = false; break;
                default: System.out.println("Please enter again (1-3)");
            }
        } while(check);  
    }
    // Login form
    static void mainLogin() {
        try {
            currentPerson.login();
            // Check permission of account
            switch (currentPerson.getAdmin()) {
                case "2":
                    Admin frontend = new Admin(currentPerson.getUsername(), currentPerson.getFullname(), currentPerson.getMail(), currentPerson.getPhoneNumber(), currentPerson.getAddress());
                    frontend(frontend);
                    break;
                case "3":
                    Admin backend = new Admin(currentPerson.getUsername(), currentPerson.getFullname(), currentPerson.getMail(), currentPerson.getPhoneNumber(), currentPerson.getAddress());
                    backend(backend);
                    break;
                case "4":
                    Admin order = new Admin(currentPerson.getUsername(), currentPerson.getFullname(), currentPerson.getMail(), currentPerson.getPhoneNumber(), currentPerson.getAddress());
                    order(order);
                    break;
                case "0":
                    Customer KH = new Customer(currentPerson.getUsername(), currentPerson.getFullname(), currentPerson.getMail(), currentPerson.getPhoneNumber(), currentPerson.getAddress());
                    customer(KH);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // Register form
    static void mainRegister() {
        Person person = new Person();
        try {
            person.register();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // Start menu
    static void menu() {
        String choice;
        do {
            System.out.println("--------MENU---------");
            System.out.println("| 1. Login          |");
            System.out.println("| 2. Register       |");
            System.out.println("| 3. Exit           |");
            System.out.println("---------------------");
            System.out.print("Enter your option (1-3): ");
            choice = input.nextLine();
            
            switch(choice) {
                case "1": mainLogin(); break;
                case "2": mainRegister(); break;
                case "3": System.exit(0);
                default: System.out.println("Please enter again (1-3)");
            }
        } while(true);
    }

    public static void main(String[] args) {
        // Display start menu
        menu();       
    }
    
}
