package main;

import java.util.Scanner;

/**
 *
 * @author ASIA
 */
public class Person {
    static Scanner input = new Scanner(System.in);
    private String id, username, password, fullname, mail, phoneNumber, address;
    String patternUsername = "[a-zA-Z]{1}\\w{3,19}";
    String patternPassword = "\\w{6,20}";
    String patternName = "[a-zA-Z\\s]{5,30}";
    String patternMail = "[a-zA-Z]{1}\\w{1,20}@\\w+(\\.\\w+)+";
    String patternPhone = "([0])([0-9]{9,10})";
    public Person() {
    }

    public Person(String id, String username, String password, String fullname, String mail, String phoneNumber, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    public boolean check(String s, String pattern){
        if(!s.matches(pattern)) return false;
        return true;
    }
    
    public void login() {
        System.out.println("--------LOGIN--------");
        System.out.print("Username:");
        setUsername(input.nextLine().toLowerCase());
        System.out.print("Password: ");
        setPassword(input.nextLine());
        System.out.println("---------------------");

    }
    public boolean checkUsername() {
        // username Least 4 character,most 20 character
        do {
            System.out.print("Username: ");
            setUsername(input.nextLine());
            if(!check(this.username, this.patternUsername)) {
                System.out.println("Please enter your username from 4 to 20 character, must be start with alpha");
            } else return true;
        } while(true);
    }
    public boolean checkPassword() {
         //Length: least 6, most 20, allow all case
        do {
            System.out.print("Password: ");
            setPassword(input.nextLine());
            if(!check(this.password, this.patternPassword)) {
                System.out.println("Please enter your password from 6 to 20 character");
            }
            else {
                System.out.print("Confirm Password: ");
                String confirm = input.nextLine();
                if (this.password.compareTo(confirm) != 0) {
                    System.out.println("Please enter again, password is not match");
                } else return true;
            }
        } while(true);
    }
    
    public boolean checkFullName() {
        //Least 5 character, most 30 character
        do {
            System.out.print("Name: ");
            setFullname(input.nextLine());
            if(!check(this.getFullname(), this.patternName)) {
                System.out.println("Please enter yout name from 5 to 30 alpha character");
            } else return true;
        } while(true);
    }
    
    public boolean checkMail() {
        //regex: example@example.com
        do {
            System.out.print("Mail: ");
            setMail(input.nextLine());
            if(!check(this.getMail(), this.patternMail)) {
                System.out.println("Wrong email format, e.g: example@example.com");
            } else return true;
        } while(true);
    }
    public boolean checkPhone() {
        //regex: 10-11 digits must be start with 0
        do {
            System.out.print("Phone number: ");
            setPhoneNumber(input.nextLine());
            if(!check(this.getPhoneNumber(), this.patternPhone)) {
                System.out.println("Please enter your phone number 10-11 digits must be start with 0");
            } else return true;
        } while(true);
    }
    
    public boolean register() {
        System.out.println("-----Registration-----");
        if (checkUsername()&& checkPassword() && checkFullName() && checkMail() && checkPhone()) return true;
        return false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getMail() {
        return mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

}
