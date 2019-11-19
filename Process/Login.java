/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginaccount;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class Login {   
    String username;
    String password;
    String pattern;
       
    public void login(){
        Scanner input = new Scanner(System.in);
        System.out.println("Username:");
        pattern = "[a-zA-Z]{1}\\w{3,19}";
        username = input.nextLine();
        username = username.toLowerCase();
            
        
        System.out.println("Password: ");
        String patterndemo = "\\w{6,20}";
        password = input.nextLine();
        password = password.toLowerCase();
        
        if(username.matches(pattern) && password.matches(patterndemo))
            System.out.println("Login Successful");
        else
        {
            System.out.println("Sorry, Username or Password is incorrect");
            System.out.println("Please enter again");
        }
    }
 }

