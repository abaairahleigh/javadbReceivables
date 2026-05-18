import java.util.Scanner;

public class Invoice {
    String invno;
    String customer;
    int amount;
    int payment;
    private InvoiceDatabase dbManager;
    public static Scanner scanner = new Scanner (System.in);

    public String getInvno(String invno){
        return invno;
    }

    public String getCustomer(String customer){
        return customer;
    }

    public String getInvno(String customer){
        return customer;
    }
    public Invoice(InvoiceDatabase dbManager) {
        this.dbManager = dbManager;
    }

    public boolean verify(String invno) {
        return dbManager.existInvo(invno);
    }

    public void addInv(Scanner scanner) {
        System.out.print("Enter Invoice Number: ");
        invno = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        customer = scanner.nextLine();
        System.out.print("Enter Invoice Amount: ");
        amount = scanner.nextInt();

        if (dbManager.addUser(invno, customer, amount)) {
            System.out.println("Invoice Added Succesfully.");
        } else {
            System.out.println("Failed to add invoice.");
        }
        
    }

    public void payInvoice(Scanner scanner){
        System.out.print("Enter Invoice Number to pay: ");
        String invno = scanner.nextLine();
        if (verify(invno)) {
                System.out.print("Enter payment amount: ");
                payment = scanner.nextInt();
                System.out.println("Payment Recorded!");
            } else {
                System.out.println("Invoice numner does not exist.");
            }
    
    }
    public int calculateBalance(Scanner scanner){
        int balance = amount - payment;
        dbManager.updateCustomerBalance(invno, amount, payment);
        return balance;
    }

    public void showInvoices(Scanner scanner){
        System.out.println("\n--- Invoices with Balance ---");
        dbManager.showInv(invno, customer, amount, payment);
        for (int i = 0; i < dbManager.Invoice.size(); i++){
            System.out.println("Invoice: " + invno + " | Customer: " + customer + " | Balance: "); calculateBalance();
        }
        
    }

    public void deleteInvoice(Scanner scanner){
        System.out.print("Enter invoice number to delete: ");
        String delInv = scanner.nextLine();

        if (dbManager.deleteInv(delInv)) {
            System.out.println("Invoice deleted successfully.");
        } else {
            System.out.println("Cannot delete invoice");
        }
    
    }
}