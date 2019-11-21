/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bug_ass;

import java.util.Scanner;

/**
 *
 * @author Nguyen Vu
 */
public class Register {

     /*String UserName;
     String PassWord;
     String RePassWord;
     String Name;
     String Mail;*/
///class register guess
    public static void Info_Guess() {
        Scanner input = new Scanner(System.in);
        
        
        System.out.println("User Name: ");
        String UserName= input.nextLine();//input name 
        String checkUserName = "[a-zA-Z][0-9a-zA-Z]{3,19}";//check format user name
        if(!UserName.matches(checkUserName)){//if wrong => notification => input again => follow format
            System.out.println("User name error! User name must least 4 character, most 20 character");
        }
        
        
        System.out.println("Password: ");
        String Password= input.nextLine();//input password
        
        System.out.println("Re-Enter Password: ");
        String RePassWord= input.nextLine();//input RePassword
        
        while(Password == null ? RePassWord != null : !Password.equals(RePassWord)){//check password == Repassword or not
            System.out.println("The password must be the same as re-entering the password");
            System.out.println("Please enter again!");
        
        System.out.println("Password: ");
        String Password1= input.nextLine();//input password again
        Password = Password1;
        
        System.out.println("Re-Enter Password: ");
        String RePassWord1= input.nextLine();//input Repassword again
        RePassWord = RePassWord1;    
        }
        
        String checkName = "[a-zA-Z][0-9-a-zA-Z]{4,29}";
        System.out.println("Name: ");
        String Name = input.nextLine();//input name
        if(Name.matches(checkName)){//check name format
            
        }
        else{//if wrong => notification => input again => follow format
            System.out.println("Name error! The name must least 5 character, most 30 character");
        }
        
        String checkMail = "[a-zA-Z][0-9-a-zA-Z]{1,25}\\@[0-9-a-zA-Z]{1,25}\\.(com)\\.(vn)";
        System.out.println("E-mail:");
        String Mail = input.nextLine();//input mail
        if(Mail.matches(checkMail)){//check mail format or not
            
        }
        else{//if wrong => notification => input again => follow format
            System.out.println("E-mail error! The E-mail must follow format example@example.com.vn and star with [a-b or A-B]");
        }
        
        String checkNumPhone = "\\0[0-9]{9,10}";
        System.out.println("Phone Number: ");
        String NumPhone = input.nextLine();//input number phone
        if(NumPhone.matches(checkNumPhone)){//check number phone or not
            
        }
        else{//if wrong => notification => input again => follow format
            System.out.println("Number phone error! The number phone must 10-11 digits must be start with 0");
        }
        
        
    }
    /*static void display(){
        System.out.println("User name: "+ UserName);
        System.out.println("Password: "+PassWord);
        System.out.println("Re_Password: "+RePassWord);
        System.out.println("Name: "+Name);
        System.out.println("E-mail: "+ this.Mail);
         String NumPhone = null;
        System.out.println("Number Phone: "+ NumPhone);
    }*/
}





