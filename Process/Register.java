/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.util.Scanner;

/**
 *
 * @author Asus
 */
class Register {

    public Register() {
    }
    
    int check = 0;
    public void register()
    {
        String userName,passWord,confPass,Name,Mail,Phone;
        String reuserName,repassWord,reName,reMail,rePhone;
        Scanner input = new Scanner(System.in);
        System.out.println("-----Registration-----");
        
        
       reuserName = "([a-z A-Z])\\w{3,19}";
       repassWord = "([a-z A-Z])\\w{5,19}";
       reName = "\\w{5,30}";
       reMail = "[a-z A-Z]{5,}@\\w+(\\.\\w+)+";
       rePhone = "([0])([0-9]{9})";
       // username Least 4 character,most 20 character
      do{
        System.out.print("Username: ");
        userName = input.nextLine();
        check(userName,reuserName);
        
       //Length: least 6, most 20, allow all case
        System.out.print("Password: ");
        passWord = input.nextLine();
         check(passWord,repassWord);
       
        System.out.print("Confirm Password: ");
        confPass = input.nextLine();
        
        if( passWord.compareTo(confPass) == 0)
            System.out.println("Successful!!!");
        else System.out.println("Wrong PassWord!!!");
        
        //Least 5 character, most 30 character   
        System.out.print("Name: ");
        Name = input.nextLine();
        check(Name, reName);
        
        //regex: example@example.com.vn
        System.out.print("Mail: ");
        Mail = input.nextLine();
        check(Mail, reMail);
        
        //regex: 10-11 digits must be start with 0
        System.out.print("Phone: ");
        Phone = input.nextLine();
        check(Phone, rePhone);
      
      }while(check!=0);
        
    }
public void check(String s,String s1){
    
    
    if(!s.matches(s1)){
        check++;    
        System.out.println("Fail!");
    }
    else System.out.println("Ok");
}
}
