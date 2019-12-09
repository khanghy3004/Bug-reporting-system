package main;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author ASIA
 */
public class ConnectionDB {
    Connection cnn = null;
    /*ACCOUNT*/
    public String username, password, fullname, mail, phoneNumber, address, admin;
    /*BILL*/
    public String billID, productID, productName,dateBuy;
    public int warranty;
    /*REPORT*/
    public String reportID, reportContent, dateReport;
    int status;

    ArrayList<ConnectionDB> listBillDB = new ArrayList<>();
    ArrayList<ConnectionDB> listReportDB = new ArrayList<>();
    SortedSet<String> sortedSetUsername = new TreeSet<>(); 
    
    String sql;
    public ConnectionDB() {
        this.username = "";
        this.password = "";
        this.fullname = "";
        this.mail = "";
        this.phoneNumber = "";
        this.address = "No info";
    }
    // Constructor contain Bill details
    public ConnectionDB(String billID, String username, String productID, String productName, int warranty, String dateBuy) {
        this.username = username;
        this.billID = billID;
        this.productID = productID;
        this.productName = productName;
        this.warranty = warranty;
        this.dateBuy = dateBuy; 
    }
    // Constructor contain Report details
    public ConnectionDB(String reportID, String username, String billID, String productName, String reportContent, String dateReport, int status) {
        this.reportID = reportID;
        this.username = username;
        this.billID = billID;
        this.productName = productName;
        this.reportContent = reportContent;
        this.dateReport = dateReport;
        this.status = status;
    }
    
    public String getBilltID(Connection cn) {
        sql =  "Select MAX(BillID) from Bill";
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);   
            if(rs.next()) {
                return rs.getString(1).charAt(0)+String.valueOf(Integer.parseInt(rs.getString(1).substring(1))+1);
            }          
        } catch (SQLException e) {
            System.out.println("Bill ID error. "+e.getMessage());
        }
        return "";
    }
    
    public boolean checkInsertBill(Connection cn, String username, String productID, String productName, String warranty) {
        sql = String.format("Insert into Bill values ('%s', '%s', '%s', '%s', '%s', '%s')", this.getBilltID(cn), username, productID, productName, warranty, LocalDate.now().toString());
        //System.out.println(sql);
        try {
            Statement stm = cnn.createStatement();
            stm.executeUpdate(sql);  
            System.out.println("Insert success!");
            return true;
        } catch (SQLException e) {
            System.out.println("Bill error 1: "+e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Bill error 2: "+e.getMessage());    
        } catch (Exception e) {
            System.out.println("Bill error 3: "+e.getMessage());
        }
        return false;
    }
    
    public boolean checkDisplayAllReport(Connection cn) {
        sql = "Select * from Report";
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            this.listReportDB.clear();
            while (rs.next()) {
                this.listReportDB.add(new ConnectionDB(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), Integer.parseInt(rs.getString(7))));
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Report error 1: "+e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Report error 2: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("Report error 3: "+e.getMessage());
        }
        return false;
    }
    
    public boolean checkUsernameOnBill(Connection cn) {
        sql = "Select username from Bill";
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            this.sortedSetUsername.clear();
            while (rs.next()) {
                this.sortedSetUsername.add(rs.getString(1));
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error 1: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("Error 2: "+e.getMessage());
        }
        return false;
    }
    
    public String getReportID(Connection cn) {
        sql =  "Select MAX(ReportID) from Report";
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);   
            if(rs.next()) {
                return rs.getString(1).charAt(0)+String.valueOf(Integer.parseInt(rs.getString(1).substring(1))+1);
            }          
        } catch (SQLException e) {
            System.out.println("Report ID error. "+e.getMessage());
        }
        return "";
    }

    public void changStatusReport(Connection cn, String reportID) {
        sql = "Update Report SET status='1' WHERE ReportID = '"+reportID+"'";
        try {
            Statement stm = cnn.createStatement();
            stm.executeUpdate(sql);  
            System.out.println("Update success!");
        } catch (SQLException e) {
            System.out.println("Report error 1: "+e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Report error 2: "+e.getMessage());    
        } catch (Exception e) {
            System.out.println("Report error 3: "+e.getMessage());
        }

    }
    public boolean checkInsertReport(Connection cn, String username, String billID, String productName, String reportContent) {
        sql = String.format("Insert into Report values ('%s', '%s', '%s', '%s', '%s', '%s', '0')", this.getReportID(cn), username, billID, productName, reportContent, LocalDate.now().toString());
        //System.out.println(sql);
        try {
            Statement stm = cnn.createStatement();
            stm.executeUpdate(sql);  
            System.out.println("Report success!");
            return true;
        } catch (SQLException e) {
            System.out.println("Report error 1: "+e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Report error 2: "+e.getMessage());    
        } catch (Exception e) {
            System.out.println("Report error 3: "+e.getMessage());
        }
        return false;
    }
    
    public boolean checDisplayReport(Connection cn, String username) {
        sql = "Select * from Report where username='"+username+"'";
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            this.listReportDB.clear();
            while (rs.next() && rs.getString(2).equals(username)) {
                this.listReportDB.add(new ConnectionDB(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), Integer.parseInt(rs.getString(7))));
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Report error 1: "+e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Report error 2: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("Report error 3: "+e.getMessage());
        }
        return false;
    }
    
    public boolean checkBill(Connection cn, String username) {
        sql = "Select * from Bill where username='"+username+"'";
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            this.listBillDB.clear();
            while (rs.next() && rs.getString(2).equals(username)) {
                this.listBillDB.add(new ConnectionDB(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), Integer.parseInt(rs.getString(5)), rs.getString(6)));
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Bill error 1: "+e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Bill error 2: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("Bill error 3: "+e.getMessage());
        }
        return false;
    }
    
    public boolean checkRegister(Connection cn, String username, String password, String fullname, String mail, String phoneNumber) {
        sql = String.format("Insert into Account(Username, Password, Fullname, Mail, PhoneNumber)  values ('%s', '%s', '%s', '%s', '%s')", username, password, fullname, mail, phoneNumber);
        //System.out.println(sql);
        try {
            Statement stm = cnn.createStatement();
            stm.executeUpdate(sql);  
            System.out.println("Register success");
            return true;
        } catch (SQLException e) {
            System.out.println("Register Fail. Username has exist");
        } catch (NullPointerException e) {
            System.out.println("Register Fail. NULL");
        } catch (Exception e) {
            System.out.println("Register error: "+e.getMessage());
        }
        return false;
    }
    
    public boolean checkLogin(Connection cn, String username, String password) {
        sql = "Select * from Account where username='"+username+"'";
        //System.out.println(sql);
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            // Check username and password is correct
            
            if(rs.next() && rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
                this.fullname = rs.getString(3);
                this.mail = rs.getString(4);
                this.phoneNumber = rs.getString(5);
                this.address = rs.getString(6);
                this.admin = rs.getString(7);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Login Fail. "+e.getMessage());
        } catch (Exception e) {
            System.out.println("Login error: "+e.getMessage());
        }
        return false; // if not correct
    }
    
    public Connection getConnectDB() {
        try {
            String uRL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Bug";
            String user = "sa";
            String pass = "123456";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnn = DriverManager.getConnection(uRL, user, pass);
            System.out.println("Success!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Cannot connect. "+e.getMessage());
        }
        return cnn;
    }
}
