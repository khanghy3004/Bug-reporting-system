package main;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author ASIA
 */
public class ConnectionDB {
    Connection cnn = null;
    public String customerID, username, password, fullname, mail, phoneNumber, address;
    String sql;
    public ConnectionDB() {
        this.customerID = "";
        this.username = "";
        this.password = "";
        this.fullname = "";
        this.mail = "";
        this.phoneNumber = "";
        this.address = "";
    }
    
    public ConnectionDB(String customerID, String username, String password, String fullname, String mail, String phoneNumber, String address) {
        this.customerID = customerID;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public String getCusID() {
        sql =  "Select MAX(CustomerID) from Customer";
        try {
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);   
            if(rs.next()) {
                String number = rs.getString(1).substring(1);
                int result = Integer.parseInt(number);
                result++;
                String ID = rs.getString(1).charAt(0)+String.valueOf(result);
                return ID;
            }          
        } catch (SQLException e) {
            System.out.println("Customer ID error");
        }
        return "";
    }
    public boolean checkRegister(Connection cn, String username, String password, String fullname, String mail, String phoneNumber) {
        sql = String.format("Insert into Customer(CustomerID, Username, Password, Fullname, Mail, PhoneNumber, Address)  values ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", getCusID(), username, password, fullname, mail, phoneNumber, address);
        System.out.println(sql);
        try {
            Statement stm = cn.createStatement();
            stm.executeUpdate(sql);  
            System.out.println("Register success");
            return true;
        } catch (SQLException e) {
            System.out.println("Register Fail");
        }
        return false;
    }
    public boolean checkLogin(Connection cn, String username, String password) {
        sql = "Select * from Customer where username='"+username+"'";
        System.out.println(sql);
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);   
            if(rs.next() && rs.getString(2).equals(username) && rs.getString(3).equals(password)) {
                System.out.println("Login success");
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Login Fail");
        }
        return false;
    }
    
    public Connection getConnectDB() {
        try {
            String uRL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Bug";
            String user = "sa";
            String pass = "123456";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnn = DriverManager.getConnection(uRL, user, pass);
            System.out.println("Success!");
        } catch (Exception e) {
            System.out.println("Cannot connect");
        }
        return cnn;
    }
}
