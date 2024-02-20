package prisons;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;

import javax.swing.JOptionPane;

public class prisons {

    private String name;
    private String district;
    private String sector;
    private int prison_id;

    // Constructors

    public prisons() {
        // Default constructor
    }

    public prisons(String name, String district, String sector) {
        this.name = name;
        this.district = district;
        this.sector = sector;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
    public void setPrison_id(int prison_id) {
        this.prison_id = prison_id;
    }

    // Getters

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public String getSector() {
        return sector;
    }
    public int getPrison_id() {
        return prison_id;
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

        String sql = "INSERT INTO prisons (prison_name,prison_district,prison_sector) VALUES (?, ?, ?)";

        try (
        		
            Connection con = DriverManager.getConnection(host, user, password);

            // Create a prepared statement
        		
            PreparedStatement stm= con.prepareStatement(sql);
        ) {
            // Set the values for the prepared statement
        	
           stm.setString(1, this.name);
            stm.setString(2, this.district);
            stm.setString(3, this.sector);
            // Execute the query
            int rowsAffected = stm.executeUpdate();

            // Check the result
            if (rowsAffected > 0) {
            	System.out.println("Data inserted successfully!");
                JOptionPane.showMessageDialog(null, "Data inserted successfully!","After insert",JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed to insert data.");
                JOptionPane.showMessageDialog(null, "Failed to insert data.!","After insert",JOptionPane.ERROR_MESSAGE);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }	
    }	

public void delete(int prison_id) {
	// JDBC URL, username, and password of MySQL server
    String url = "jdbc:mysql://localhost/jail_management_system";
    String user = "root";
    String password = "";

    // SQL query to delete data
    String sql = "DELETE FROM prisons WHERE prison_id = ?";

    try (
        // Establish the con
        Connection con = DriverManager.getConnection(url, user, password);

        // Create a prepared statement
        PreparedStatement stm = con.prepareStatement(sql);
    ) {
        // Set the value for the WHERE clause
        stm.setInt(1, prison_id); // Assuming there is a column named 'id' for the WHERE clause

        // Execute the delete
        int rowsAffected = stm.executeUpdate();

        // Check the result
        if (rowsAffected > 0) {
        	JOptionPane.showMessageDialog(null, "Data delete successfully!","After insert",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Data deleted successfully!");
        } else {
        	JOptionPane.showMessageDialog(null, "Failed to delete data.!","After insert",JOptionPane.ERROR_MESSAGE);
            System.out.println("Failed to delete data. No matching record found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void update(int prison_id) {
	// JDBC URL, username, and password of MySQL server
    String url = "jdbc:mysql://localhost/jail_management_system";
    String user = "root";
    String password = "";

    // SQL query to update data
    String sql = "UPDATE prisons SET prison_name = ?, prison_district = ?,prison_sector = ? WHERE prison_id = ?";

    try (
        // Establish the con
        Connection con = DriverManager.getConnection(url, user, password);

        // Create a prepared statement
        PreparedStatement stm = con.prepareStatement(sql);
    ) {
        // Set the new values for the update
        stm.setString(1, this.getName());
        stm.setString(2, this.getDistrict());
        stm.setString(3, this.getSector());
        stm.setInt(4, prison_id);
        // Execute the update
        int rowsAffected = stm.executeUpdate();

        // Check the result
        if (rowsAffected > 0) {
        	JOptionPane.showMessageDialog(null, "Data updated successfully!","After insert",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Data updated successfully!");
        } else {
        	JOptionPane.showMessageDialog(null, "Failed to update data.!","After insert",JOptionPane.ERROR_MESSAGE);
            System.out.println("Failed to update data. No matching record found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }   
}
}
