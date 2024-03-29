package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;


public class Customer extends Person{
    /*BILL*/
    private String billID, productID, productName, dateBuy;
    private int warranty;
    /*REPORT*/
    private String reportID, reportContent, dateReport;
    int status;
    
    ArrayList<Customer> listBill = new ArrayList<>();
    ArrayList<Customer> listReport = new ArrayList<>();
    
    public Customer() {
    }

    public Customer(String username, String fullname, String mail, String phoneNumber, String address) {
        super(username, fullname, mail, phoneNumber, address);
    }
    // Constructor contain Bill details
    public Customer(String billID, String username, String productID, String productName, int warranty, String dateBuy) {
        this.username = username;
        this.billID = billID;
        this.productID = productID;
        this.productName = productName;
        this.warranty = warranty;
        this.dateBuy = dateBuy;      
    }
    // Constructor contain Report details
    public Customer(String reportID, String username, String billID, String productName, String reportContent, String dateReport, int status) {
        this.reportID = reportID;
        this.username = username;
        this.billID = billID;
        this.productName = productName;
        this.reportContent = reportContent;
        this.dateReport = dateReport;
        this.status = status;
    }
    // Check product is under warranty
    public boolean isWarranty(String dateBuy, int warranty) throws ParseException {
        int month = Period.between(LocalDate.parse(dateBuy), LocalDate.now()).getMonths();
        double year = Period.between(LocalDate.parse(dateBuy), LocalDate.now()).getYears();
        if(month+year*12 >= warranty) {
            return false;
        }
        return true;
    }
    // Get data from Bill and check user has Bill or not
    public boolean updateBill(String username) {
        boolean check = false;
        if(this.connect.checkBill(this.cn, username)) {
            if (this.connect.listBillDB.size() > 0) check = true;
            for (int i = 0; i < this.connect.listBillDB.size(); ++i) {
                ConnectionDB data = this.connect.listBillDB.get(i); 
                this.listBill.add(new Customer(data.billID, data.username, data.productID, data.productName, data.warranty, data.dateBuy));          
            }
        }  
        return check;
    }
    // Dispay data from Bill
    public void displayBill(String username) throws ParseException {
        if (this.updateBill(username)) {
            System.out.println("-----------MY BILL DETAIL-----------");
            for (int i = 0; i < this.listBill.size(); ++i) {
                Customer data = this.listBill.get(i);
                System.out.println("- Bill ID: "+data.getBillID());
                System.out.println("- Product ID: "+data.getProductID());
                System.out.println("- Product Name: "+data.getProductName());
                System.out.println("- Warranty: "+data.getWarranty()+" months");
                System.out.println("- Date Buy: "+new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data.getDateBuy())));
                if (this.isWarranty(data.getDateBuy().substring(0, 10), data.getWarranty())) {
                    System.out.println("Under warranty");
                } else {
                    System.out.println("Over warranty");
                }
                System.out.println("---------------------------------");
            } 
            this.listBill.clear();       
        } else {
            System.out.println("Sorry! You do not have any bill!");
        }
        System.out.println("Press any key to continue...");
        input.nextLine();
    }
    // Create a report
    public void makeReport(String username) {
        System.out.println("---------REPORT---------");
        String id;
        do {
            System.out.println("- Enter Bill ID: ");
            id = input.nextLine();
             // Check Bill ID has exist on Bill
            if(this.connect.checkBillIDExist(this.cn, id, username)) break;
        } while(true);
        this.setBillID(id);
        
        System.out.println("- Enter Product Name: ");
        this.setProductName(input.nextLine());
        System.out.println("- Enter Content");
        this.setReportContent(input.nextLine());
        System.out.println("------------------------");
        if (this.connect.checkInsertReport(cn, username, this.getBillID(), this.getProductName(), this.getReportContent())) {
            System.out.println("Successfully!");
        }
        System.out.println("Press any key to continue...");
        input.nextLine();
    }
    // Get data from Report and check user has Report or not
    public boolean updateReport(String username) {
        boolean check = false;
        if(this.connect.checDisplayReport(this.cn, username)) {
            if (this.connect.listReportDB.size() > 0) check = true;
            for (int i = 0; i < this.connect.listReportDB.size(); ++i) {
                ConnectionDB data = this.connect.listReportDB.get(i); 
                this.listReport.add(new Customer(data.reportID, data.username, data.billID, data.productName, data.reportContent, data.dateReport, data.status));
            }
        } 
        return check;
    }
    // Display data from Report
    public void displayReport(String username) throws ParseException {
        if (this.updateReport(username)) {
            System.out.println("-----MY REPORT DETAIL-----");
            for (int i = 0; i < this.listReport.size(); ++i) {
                Customer data = this.listReport.get(i);
                System.out.println("- Report ID: "+data.getReportID());
                System.out.println("- Bill ID: "+data.getBillID());
                System.out.println("- Product Name: "+data.getProductName());
                System.out.println("- Content: "+data.getReportContent());
                System.out.println("- Date Report: "+new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data.getDateReport())));
                if (data.getStatus() == 1) {
                    System.out.println("Status: Completed");
                } else {
                    System.out.println("Status: Incomplete");
                }
                System.out.println("----------------------");
            } 
            this.listReport.clear();
        } else {
            System.out.println("Sorry! You do not have any report!");
        }
        System.out.println("Press any key to continue...");
        input.nextLine();
    }

     
    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public String getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(String dateBuy) {
        this.dateBuy = dateBuy;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getDateReport() {
        return dateReport;
    }

    public void setDateReport(String dateReport) {
        this.dateReport = dateReport;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
