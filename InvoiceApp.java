import java.util.Scanner;

public class InvoiceApp {
    public static void main(String[] args) {
        InvoiceDatabase dbManager = new InvoiceDatabase();
        Scanner scanner = new Scanner(System.in);
        Invoice invoice = new Invoice(dbManager);

        while (true) {
            System.out.println("\n--- Invoice Menu ---");
            System.out.println("1. Add Invoice");
            System.out.println("2. Display Invoices with Balance");
            System.out.println("3. Pay Invoice");
            System.out.println("4. Delete Invoice");
            System.out.println("5. Exit Program");
            System.out.print("Select an option (1-5): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    invoice.addInv();
                    break;
                case "2":
                    break;
                case "3":
                    
                    break;
                case "4":
                    invoice.deleteInvoice();
                    break;
                case "5":
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1 to 5.");
             }
        }
    }
}