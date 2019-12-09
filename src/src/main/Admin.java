package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ASIA
 */
public class Admin extends Customer {
    
    
    public Admin(String username, String fullname, String mail, String phoneNumber, String address) {
        super(username, fullname, mail, phoneNumber, address);
    }
    
    public void customerOnBillandReport() throws ParseException {
        if(this.connect.checkUsernameOnBill(this.cn)) {
            Iterator subSetIt = this.connect.sortedSetUsername.iterator();
            ArrayList<String> sortedListUsername = new ArrayList<>();
            
            while (subSetIt.hasNext()) {
                sortedListUsername.add(subSetIt.next().toString());
            }
            
            System.out.println("----------Customer Bill---------");
            for (int i = 0; i < sortedListUsername.size(); ++i) {
                System.out.println((i+1)+". "+sortedListUsername.get(i));
            }
            System.out.println("--------------------------------");
            
            System.out.println("Enter index number of username:");
            String index = input.nextLine();
            try {
                boolean check = true;
                String user = sortedListUsername.get(Integer.parseInt(index)-1);
                do {
                    System.out.println("Bill and Report of "+user);
                    System.out.println("1. Bill");
                    System.out.println("2. Report");
                    System.out.println("3. Back");
                    System.out.println("Enter option (1-3):");
                    String option = input.nextLine();
                    switch(option) {
                        case "1": this.displayBill(user); break;
                        case "2": this.displayReport(user); break;
                        case "3": check = false ; break;
                    }
                } while(check);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }   
        } 
        else {
            System.out.println("Sorry! You do not have any bill!");
        }
    }
    
    public void reportHandling() {
        this.listReport.clear();
        if (this.connect.checkDisplayAllReport(this.cn)) {
            for (int i = 0; i < this.connect.listReportDB.size(); ++i) {
                ConnectionDB data = this.connect.listReportDB.get(i); 
                this.listReport.add(new Customer(data.reportID, data.username, data.billID, data.productName, data.reportContent, data.dateReport, data.status));
            }
            try {
                boolean check = true;
                do {
                    System.out.println("Total "+this.listReport.size()+" reports");
                    System.out.println("1. Incomplete");
                    System.out.println("2. Completed");
                    System.out.println("3. Back");
                    System.out.println("Enter option (1-3):");
                    String option = input.nextLine();
                    switch(option) {
                        case "1": 
                            for (int i = 0; i < this.listReport.size(); ++i) {
                                Customer data = this.listReport.get(i);
                                if (data.getStatus() == 0) {
                                    System.out.println(data.getReportID()+"\t"+new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(data.getDateReport())));
                                }
                            }
                            System.out.println("Enter Report ID want to display:");
                            String id = input.nextLine();
                            for (int i = 0; i < this.listReport.size(); ++i) {
                                Customer data = this.listReport.get(i);
                                if (data.getReportID().equals(id)) {
                                    System.out.println("- Report ID: "+data.getReportID());
                                    System.out.println("- Bill ID: "+data.getBillID());
                                    System.out.println("- Product Name: "+data.getProductName());
                                    System.out.println("- Content: "+data.getReportContent());
                                    System.out.println("- Date Report: "+new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(data.getDateReport())));
                                    System.out.println("- Do you want to change status to 'completed'? (y/n)");
                                    String ans = input.nextLine();
                                    if (ans.equals("y") || ans.equals("Y")) {
                                        this.connect.changStatusReport(this.cn, id);
                                    }
                                }
                            }
                            System.out.println("Press any key to continue...");
                            input.nextLine();
                            break;
                        case "2": 
                            for (int i = 0; i < this.listReport.size(); ++i) {
                                Customer data = this.listReport.get(i);
                                if (data.getStatus() == 1) {
                                    System.out.println(data.getReportID()+"\t"+data.getDateReport());
                                }
                            }
                            System.out.println("Enter Report ID want to display:");
                            String id2 = input.nextLine();
                            for (int i = 0; i < this.listReport.size(); ++i) {
                                Customer data = this.listReport.get(i);
                                if (data.getReportID().equals(id2)) {
                                    System.out.println("- Report ID: "+data.getReportID());
                                    System.out.println("- Bill ID: "+data.getBillID());
                                    System.out.println("- Product Name: "+data.getProductName());
                                    System.out.println("- Content: "+data.getReportContent());
                                    System.out.println("- Date Report: "+new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(data.getDateReport())));
                                }
                            }
                            System.out.println("Press any key to continue...");
                            input.nextLine();
                            break;
                        case "3": check = false ; break;
                    }
                } while(check);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        } else {
            System.out.println("No report.");
        }
    }
    
    public void insertNewBill() {
        System.out.println("- Enter username: ");
        String user = input.nextLine();
        System.out.println("- Enter Product ID: ");
        String productID = input.nextLine();
        System.out.println("- Enter Product Name: ");
        String productName = input.nextLine();
        System.out.println("- Enter Warranty: ");
        String warranty = input.nextLine();
        this.connect.checkInsertBill(cn, user, productID, productName, warranty);
        System.out.println("Press any key to continue...");
        input.nextLine();
    }
    
}
