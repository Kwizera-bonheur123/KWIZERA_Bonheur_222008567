package visitors;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import inmates.inmates;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class visitors extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	private JTable table;
	
	
	public void table_load() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jail_management_system", "root", "");
	        Statement st = con.createStatement();
	        String query = "SELECT visitor_id, fname, lname, id_number, phone, gender, martial_status, DoB, email FROM visitors";
	        ResultSet rs = st.executeQuery(query);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        DefaultTableModel model = (DefaultTableModel) table_1.getModel();

	        model.setRowCount(0);

	        int cols = rsmd.getColumnCount();
	        String[] colName = new String[cols];
	        for (int i = 0; i < cols; i++)
	            colName[i] = rsmd.getColumnName(i + 1);
	        model.setColumnIdentifiers(colName);

	        while (rs.next()) {
	            String id = rs.getString(1);
	            String fname = rs.getString(2);
	            String lname = rs.getString(3);
	            String id_number = rs.getString(4);
	            String phone = rs.getString(5);
	            String gender = rs.getString(6);
	            String marital_status = rs.getString(7);
	            Date DoB = rs.getDate(8);
	            String email = rs.getString(9);

	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            String formattedDate = dateFormat.format(DoB);

	            String[] row = {id, fname, lname, id_number, phone, gender, marital_status, formattedDate, email};
	            model.addRow(row);
	        }

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visitors frame = new visitors();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	

	

	
	public visitors() {
		table_1 = new JTable();
		table_1.setBounds(0, 0, 0, 0);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1119, 788);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		contentPane.add(table_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 70, 915, 209);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JLabel lblNewLabel_1 = new JLabel("VISITORS");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(433, 33, 148, 26);
		contentPane.add(lblNewLabel_1);
		
		table = new JTable();
		table.setBounds(147, 367, 1, 1);
		contentPane.add(table);
		
		JButton btnNewButton = new JButton("Retrieve visitor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton) {
					table_load();
				}
			}
		});
		btnNewButton.setBounds(134, 11, 132, 45);
		contentPane.add(btnNewButton);
	}
}
