package schedule_visit;

import java.sql.*;
import javax.swing.JOptionPane;

public class schedule_visit_entity {

    private int visitor_id;
    private int prisoner_id;
    private int visit_id;
    private String reason;

    // Constructors

    public schedule_visit_entity() {
        // Default constructor
    }

    public schedule_visit_entity(int visitor_id, int prisoner_id, int visit_id, String reason) {
        this.visitor_id = visitor_id;
        this.prisoner_id = prisoner_id;
        this.visit_id = visit_id;
        this.reason = reason;
    }

    // Setters

    public void setVisitor_id(int visitor_id) {
        this.visitor_id = visitor_id;
    }

    public void setPrisoner_id(int prisoner_id) {
        this.prisoner_id = prisoner_id;
    }

    public void setVisit_id(int visit_id) {
        this.visit_id = visit_id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // Getters

    public int getVisitor_id() {
        return visitor_id;
    }

    public int getPrisoner_id() {
        return prisoner_id;
    }

    public int getVisit_id() {
        return visit_id;
    }

    public String getReason() {
        return reason;
    }

    public void makeconnection() {
        // JDBC URL, username, and password of MySQL server
        String host = "jdbc:mysql://localhost/jail_management_system";
        String user = "root";
        String password = "";
    }

    public void insertData() {
        String host = "jdbc:mysql://localhost/jail_management_system";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO schedule_visit_date (visitor_id, prisoner_id, visit_id, reason) VALUES (?, ?, ?, ?)";

        try (
            Connection con = DriverManager.getConnection(host, user, password);
            PreparedStatement stm= con.prepareStatement(sql);
        ) {
            // Set the values for the prepared statement
            stm.setInt(1, this.visitor_id);
            stm.setInt(2, this.prisoner_id);
            stm.setInt(3, this.visit_id);
            stm.setString(4, this.reason);

            // Execute the query
            int rowsAffected = stm.executeUpdate();

            // Check the result
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
                JOptionPane.showMessageDialog(null, "Data inserted successfully!", "After insert", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed to insert data.");
                JOptionPane.showMessageDialog(null, "Failed to insert data!", "After insert", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }   
}
