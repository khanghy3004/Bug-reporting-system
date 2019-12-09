/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.*;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Scanner;
/**
 *
 * @author ASIA
 */
public class Main {
    static ConnectionDB connect = new ConnectionDB();
    static Connection cn = connect.getConnectDB();
    static Scanner input = new Scanner(System.in);
    
    static Person currentPerson = new Person();
    
    
    static void customer(Customer KH) throws ParseException {
        boolean check = true;
        do {
            System.out.println("Hello "+KH.getFullname());
            System.out.println("1. My profile");
            System.out.println("2. My Bill");
            System.out.println("3. Make a report");
            System.out.println("4. Display my report");
            System.out.println("5. Log out");
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
    static void admin(Customer KH) {
        System.out.println("Hello admin "+KH.getFullname());
        System.out.println("1. My profile");
        System.out.println("2. Logout");
        String choice = input.nextLine();
        switch(choice) {
            case "1": KH.displayProfile(); break;
            case "2": break;
        }
    }
    static void frontend(Admin frontend) throws ParseException {
        boolean check = true;
        do {
            System.out.println("Hello "+frontend.getFullname());
            System.out.println("1. My profile");
            System.out.println("2. Display Customer Bill and Report");
            System.out.println("3. Make a report");
            System.out.println("4. Log out");
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
    
    static void backend(Admin backend) throws ParseException {
        boolean check = true;
        do {
            System.out.println("Hello "+backend.getFullname());
            System.out.println("1. My profile");
            System.out.println("2. Display Customer Bill and Report");
            System.out.println("3. Report Handling");
            System.out.println("4. Log out");
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
    
    static void order(Admin order) {
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
    
    static void mainLogin() {
        try {
            currentPerson.login();
            
            if (currentPerson.getAdmin().equals("1")) { 
                //admin(KH);
            } 
            else if (currentPerson.getAdmin().equals("2")) {
                Admin frontend = new Admin(currentPerson.getUsername(), currentPerson.getFullname(), currentPerson.getMail(), currentPerson.getPhoneNumber(), currentPerson.getAddress());
                frontend(frontend);
            }
            else if (currentPerson.getAdmin().equals("3")) {
                Admin backend = new Admin(currentPerson.getUsername(), currentPerson.getFullname(), currentPerson.getMail(), currentPerson.getPhoneNumber(), currentPerson.getAddress());
                backend(backend);
            }
            else if (currentPerson.getAdmin().equals("4")) {
                Admin order = new Admin(currentPerson.getUsername(), currentPerson.getFullname(), currentPerson.getMail(), currentPerson.getPhoneNumber(), currentPerson.getAddress());
                order(order);
            }
            else if (currentPerson.getAdmin().equals("0")) {
                Customer KH = new Customer(currentPerson.getUsername(), currentPerson.getFullname(), currentPerson.getMail(), currentPerson.getPhoneNumber(), currentPerson.getAddress());
                customer(KH);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void mainRegister() {
        Person person = new Person();
        try {
            person.register();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void menu() {
        String choice;
        do {
            System.out.println("--------MENU---------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.println("---------------------");
            System.out.print("Enter your opetion (1-3): ");
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
        menu();

//        Statement stm = cn.createStatement();
//        ResultSet rs = null;
        
//        id = input.nextLine();
//        username = input.nextLine();
//        password = input.nextLine();
//        fullname = input.nextLine();
//        phoneNumber = input.nextLine();
//        address = input.nextLine();
//        mail = input.nextLine();
//        
//        connect = new ConnectionDB(id, username, password, fullname, mail, phoneNumber, address);
//        sql = connect.insertCustomer();
//        stm.executeUpdate(sql);
        
        
        
        
//        sql = "select * from Customer";
//        try {
//            rs = stm.executeQuery(sql);
//            while(rs.next()) {
//                System.out.println("ID: "+rs.getString(1)+"\tUsername: "+rs.getString(2)+"\tPassword: "+rs.getString(3)+"\tFullname: "+rs.getString(4)+"\tMail: "+rs.getString(5)+"\tPhone Number: "+rs.getString(6)+"\tAddress: "+rs.getString(7));
//            }
//        } catch (SQLException e) {
//            System.out.println("Error");
//        }
//        
    }
    
}
