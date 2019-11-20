/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author ASIA
 */
public class Main {
    static ConnectionDB connect = new ConnectionDB();
    static Connection cn = connect.getConnectDB();
    static Scanner input = new Scanner(System.in);
    
    static void login() {
        Person person = new Person();
        do {
            person.login();
            if(connect.checkLogin(cn, person.getUsername(), person.getPassword())) {
                System.out.println("New page");
            }
            else {
                System.out.println("Press \"ENTER\" to continue, another key to exit");
                String check = input.nextLine();
                if (!check.equals("")) break;
                
            }
        } while(true);
    }
    static void register() {
        Person person = new Person();
        do {
            person.register();
            if(connect.checkRegister(cn, person.getUsername(), person.getPassword(), person.getFullname(), person.getMail(), person.getPhoneNumber())) {
                System.out.println("New page");
            }
            else {
                System.out.println("Press \"ENTER\" to continue, another key to exit");
                String check = input.nextLine();
                if (!check.equals("")) break;
                
            }
        } while(true);
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
                case "1": login(); break;
                case "2": register(); break;
                case "3": System.exit(0);
                default:
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
