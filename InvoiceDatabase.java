import java.sql.*;
import java.util.*;

public class InvoiceDatabase {
    private String url = "jdbc:mysql://localhost:3306/javadb";
    private String dbUser = "root";
    private String dbPassword = "";

    public boolean existInvo(String invno) {
        String query = "SELECT * FROM credentials WHERE invno = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, invno);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addUser(String invno, String customer, int amount) {
        String insertQuery = "INSERT INTO receivable (invno, customer, amount) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
                PreparedStatement pst = conn.prepareStatement(insertQuery)) {

            pst.setString(1, invno);
            pst.setString(2, customer);
            pst.setInt(3, amount);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomerBalance(String invno, int amount, int payment) {
        String updateQuery = "UPDATE credentials SET amount = ? SET payment = ? WHERE invno = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(updateQuery)) {

            pst.setInt(1, amount);
            pst.setInt(2, payment);
            pst.setString(3, invno);

            int rowsUpdated = pst.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteInv(String invno) {
        String deleteQuery = "DELETE FROM receivable WHERE invno = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(deleteQuery)) {

            pst.setString(1, invno);

            int rowsDeleted = pst.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Invoice> showInv(String invno, String customer, int amount, int payment){
        List<Invoice> invoices = new ArrayList<Invoice>();
        String selectQuery = "SELECT invno, customer, amount, payment FROM receivable";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(selectQuery)) {

            try (ResultSet rs = pst.executeQuery(selectQuery)) {
                while(rs.next()){
                    Invoice invoice = new Invoice();
                    pst.setString(1, invno);
                    pst.setString(2, customer);
                    pst.setInt(3, amount);
                    pst.setInt(4, payment);
                    
                    invoices.add(invoice);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
}