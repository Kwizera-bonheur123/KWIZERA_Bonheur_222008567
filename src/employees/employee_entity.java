package employees;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;

import javax.swing.JOptionPane;

public class employee_entity {

	private int id;
    private String fname;
    private String lname;
    private String id_card;
    private String phone;
    private String gender;
    private String marital_status;
    private String date;
    private String email;
    private String password;
    private String role;

    // Constructors

    public employee_entity() {
        // Default constructor
    }

    public employee_entity(int id,String fname, String lname, String id_card,String phone,String gender, String marital_status ,String date, String email, String password, String role ) {
    	this.id = id;
    	this.fname = fname;
        this.lname = lname;
        this.id_card = id_card;
        this.phone = phone;
        this.gender = gender;
        this.marital_status = marital_status;
        this.date = date;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    public void setId_card(String id_card) {
        this.id_card = id_card;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }

    // Getters
    
    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getId_card() {
        return id_card;
    }
    public String getPhone() {
        return phone;
    }
    
    public String getGender() {
        return gender;
    }
    
    public String getMarital_status() {
        return marital_status;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return role;
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

        String sql = "INSERT INTO admin (  fname,lname,id_number,phone,gender,martial_status,DoB,email,password) VALUES (?, ?, ?,?,?,?,?,?,?)";

        try (
        		
            Connection con = DriverManager.getConnection(host, user, password);

            // Create a prepared statement
        		
            PreparedStatement stm= con.prepareStatement(sql);
        ) {
            // Set the values for the prepared statement
        	
            stm.setString(1, this.fname);
            stm.setString(2, this.lname);
            stm.setString(3, this.id_card); 
            stm.setString(4, this.phone);
            stm.setString(5, this.gender);
            stm.setString(6, this.marital_status);
            stm.setString(7, this.date);
            stm.setString(8, this.email);
            stm.setString(9, this.password);
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

public void delete(int id) {
	// JDBC URL, username, and password of MySQL server
    String url = "jdbc:mysql://localhost/jail_management_system";
    String user = "root";
    String password = "";

    // SQL query to delete data
    String sql = "DELETE FROM admin WHERE admin_id = ?";

    try (
        // Establish the con
        Connection con = DriverManager.getConnection(url, user, password);

        // Create a prepared statement
        PreparedStatement stm = con.prepareStatement(sql);
    ) {
        // Set the value for the WHERE clause
        stm.setInt(1, id); // Assuming there is a column named 'id' for the WHERE clause

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


public void update(int id) {
	// JDBC URL, username, and password of MySQL server
    String url = "jdbc:mysql://localhost/jail_management_system";
    String user = "root";
    String password = "";

    // SQL query to update data
    String sql = "UPDATE admin SET fname = ?, lname = ?, id_number = ?, phone = ?, gender = ?, martial_status = ?, DoB = ?, email = ?, role = ? WHERE admin_id = ?";

    try ( 
        // Establish the con
        Connection con = DriverManager.getConnection(url, user, password);

        // Create a prepared statement
        PreparedStatement stm = con.prepareStatement(sql);
    ) {
        // Set the new values for the update
    	stm.setString(1, this.fname);
        stm.setString(2, this.lname);
        stm.setString(3, this.id_card); 
        stm.setString(4, this.phone);
        stm.setString(5, this.gender);
        stm.setString(6, this.marital_status);
        stm.setString(7, this.date);
        stm.setString(8, this.email);
        stm.setString(9, this.role);
        stm.setInt(10, id);
        // Execute the update
        int rowsAffected = stm.executeUpdate();

        // Check the result
        if (rowsAffected > 0) {
        	JOptionPane.showMessageDialog(null, "Data updated successfully!","After update",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Data updated successfully!");
        } else {
        	JOptionPane.showMessageDialog(null, "Failed to update data.!","After update",JOptionPane.ERROR_MESSAGE);
            System.out.println("Failed to update data. No matching record found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to update data. Error: " + e.getMessage(), "After update", JOptionPane.ERROR_MESSAGE);
        System.out.println("Failed to update data. Error: " + e.getMessage());
    }
}
}
