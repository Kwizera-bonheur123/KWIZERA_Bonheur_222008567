package inmates;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;

import javax.swing.JOptionPane;

public class inmate {

	private int id;
    private String fname;
    private String lname;
    private String id_card;
    private String gender;
    private String marital_status;
    private String DoB;
    private String admission_date;
    private String release_date;
    private int prison_id;

    // Constructors

    public inmate() {
        // Default constructor
    }

    public inmate(int id,String fname, String lname, String id_card,String phone,String gender, String marital_status ,String release_date, String email, String DoB, String admission_date, int prison_id ) {
    	this.id = id;
    	this.fname = fname;
        this.lname = lname;
        this.id_card = id_card;
        this.gender = gender;
        this.marital_status = marital_status;
        this.DoB = DoB;
        this.admission_date = admission_date;
        this.release_date = release_date;
        this.prison_id = prison_id;
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
    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }
    public void setDoB(String DoB) {
        this.DoB = DoB;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setAdmission_date(String admission_date) {
        this.admission_date = admission_date;
    }
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    public void setPrison_id(int prison_id) {
        this.prison_id = prison_id;
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
    
    public String getDoB() {
        return DoB;
    }
    
    public String getMarital_status() {
        return marital_status;
    }
    
    public String getAdmission_date() {
        return admission_date;
    }
    public String getRelease_date() {
        return release_date;
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

        String sql = "INSERT INTO prisoners (fname,lname,id_number,gender,DoB,martial_status,admission_date,release_date,prison_id) VALUES (?, ?, ?,?,?,?,?,?,?)";

        try (
        		
            Connection con = DriverManager.getConnection(host, user, password);

            // Create a prepared statement
        		
            PreparedStatement stm= con.prepareStatement(sql);
        ) {
            // Set the values for the prepared statement
        	
            stm.setString(1, this.fname);
            stm.setString(2, this.lname);
            stm.setString(3, this.id_card);
            stm.setString(4, this.gender);
            stm.setString(5, this.DoB);
            stm.setString(6, this.marital_status);
            stm.setString(7, this.admission_date);
            stm.setString(8, this.release_date);
            stm.setInt(9, this.prison_id);
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
    String sql = "DELETE FROM prisoners WHERE prisoner_id = ?";

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
    String sql = "UPDATE prisoners SET fname = ?, lname = ?, id_number = ?, gender = ?, martial_status = ?, DoB = ?, admission_date = ?, release_date = ?, prison_id = ? WHERE prisoner_id = ?";

    try (
        // Establish the connection
        Connection con = DriverManager.getConnection(url, user, password);

        // Create a prepared statement
        PreparedStatement stm = con.prepareStatement(sql);
    ) {
        // Set the new values for the update
        stm.setString(1, this.fname);
        stm.setString(2, this.lname);
        stm.setString(3, this.id_card);
        stm.setString(4, this.gender);
        stm.setString(5, this.marital_status);
        stm.setString(6, this.DoB);
        stm.setString(7, this.admission_date);
        stm.setString(8, this.release_date);
        stm.setInt(9, this.prison_id);
        stm.setInt(10, id);

        // Execute the update
        int rowsAffected = stm.executeUpdate();

        // Check the result
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Data updated successfully!", "After update", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Data updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update data. No matching record found.", "After update", JOptionPane.ERROR_MESSAGE);
            System.out.println("Failed to update data. No matching record found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to update data. Error: " + e.getMessage(), "After update", JOptionPane.ERROR_MESSAGE);
        System.out.println("Failed to update data. Error: " + e.getMessage());
    }
}


}
