package main;

import java.sql.Connection;
import java.util.Scanner;

public class Person {
    ConnectionDB connect = new ConnectionDB();
    Connection cn = connect.getConnectDB();
    static Scanner input = new Scanner(System.in);
    
    public String username, password, fullname, mail, phoneNumber, address, admin;

    public Person() {
    }

    public Person(String username, String fullname, String mail, String phoneNumber, String address) {
        this.username = username;
        this.fullname = fullname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Check input string has matches partten
    public boolean check(String s, String pattern){
        if(!s.matches(pattern)) return false;
        return true;
    }
    // Method register
    public void register() {
        System.out.println("-----Registration-----");
        checkUsername();
        checkPassword();
        checkFullName();
        checkMail();
        checkPhone();
        System.out.println("---------------------");
        if(connect.checkRegister(cn, this.getUsername(), this.getPassword(), this.getFullname(), this.getMail(), this.getPhoneNumber())) {
            System.out.println("Register sucess!");
        }
    }
    // Method login
    public void login() throws Exception {  
        System.out.println("--------LOGIN--------");
        System.out.print("Username:");
        setUsername(input.nextLine().toLowerCase());
        System.out.print("Password: ");
        setPassword(input.nextLine());
        System.out.println("---------------------");
        if(connect.checkLogin(cn, this.getUsername(), this.getPassword())) {
            this.setFullname(this.connect.fullname);
            this.setMail(this.connect.mail);
            this.setPhoneNumber(this.connect.phoneNumber);
            this.setAddress(this.connect.address);
            this.setAdmin(this.connect.admin);
            System.out.println("Login success");
        } else {
            throw new Exception("Wrong username or password");
        }
        
    }
    // Display profile
    public void displayProfile() {
        System.out.println("------MY PROFILE------");
        System.out.println("- Username: "+this.getUsername());
        System.out.println("- Name: "+this.getFullname());
        System.out.println("- Mail: "+this.getMail());
        System.out.println("- Phone: "+this.getPhoneNumber());
        System.out.println("- Address: "+this.getAddress());
        System.out.println("----------------------");
        System.out.println("Press any key to continue...");
        input.nextLine();
    }
    // Check username register
    public void checkUsername() {
        // username Least 4 character,most 20 character
        String patternUsername = "[a-zA-Z]{1}\\w{3,19}";
        do {
            System.out.print("Username: ");
            setUsername(input.nextLine());
            if(!check(this.getUsername(), patternUsername)) {
                System.out.println("Please enter your username from 4 to 20 character, must be start with alpha");
            } else {
                break;
            }
        } while(true);
    }
    // Check password register
    public void checkPassword() {
        String patternPassword = "\\w{6,20}";
        //Length: least 6, most 20, allow all case
        do {
            System.out.print("Password: ");
            setPassword(input.nextLine());
            if(!check(this.getPassword(), patternPassword)) {
                System.out.println("Please enter your password from 6 to 20 character");
            }
            else {
                System.out.print("Confirm Password: ");
                String confirm = input.nextLine();
                if (this.password.compareTo(confirm) != 0) {
                    System.out.println("Please enter again, password is not match");
                } else {
                    break;
                }
            }
        } while(true);
    }
    // Check full name register 
    public void checkFullName() {
        //Least 5 character, most 30 character
        String patternName = "[a-zA-Z\\s]{5,30}";
        do {
            System.out.print("Name: ");
            setFullname(input.nextLine());
            if(!check(this.getFullname(), patternName)) {
                System.out.println("Please enter yout name from 5 to 30 alpha character");
            } else {
                break;
            }
        } while(true);
    }
    // Check mail register
    public void checkMail() {
        //regex: example@example.com
        String patternMail = "[a-zA-Z]{1}\\w{1,20}@\\w+(\\.\\w+)+";
        do {
            System.out.print("Mail: ");
            setMail(input.nextLine());
            if(!check(this.getMail(), patternMail)) {
                System.out.println("Wrong email format, e.g: example@example.com");
            } else {
                break;
            }
        } while(true);
    }
    // Check phone number register
    public void checkPhone() {
        //regex: 10-11 digits must be start with 0
        String patternPhone = "([0])([0-9]{9,10})";
        do {
            System.out.print("Phone number: ");
            setPhoneNumber(input.nextLine());
            if(!check(this.getPhoneNumber(), patternPhone)) {
                System.out.println("Please enter your phone number 10-11 digits must be start with 0");
            } else {
                break;
            }
        } while(true);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }


}
