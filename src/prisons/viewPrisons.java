package prisons;
import java.sql.*;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class viewPrisons extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	private JTable table;
	
	
	
	public void table_load() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jail_management_system","root","");
			Statement st = con.createStatement();
			String query= "SELECT prison_id as ID, prison_name as NAME, prison_district as district, prison_sector as sector FROM prisons";
			ResultSet rs= st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();	
			
			model.setRowCount(0);
			
			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i = 0; i < cols; i++)
				colName[i] = rsmd.getColumnName(i + 1);
			model.setColumnIdentifiers(colName);
			String id,name,district,sector;
			while(rs.next()) {
				id = rs.getString(1);
				name = rs.getString(2);
				district = rs.getString(3);
				sector = rs.getString(4);
				String[] row = {id,name,district,sector};
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
					viewPrisons frame = new viewPrisons();
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
	

	

	
	public viewPrisons() {
		table_1 = new JTable();
		table_1.setBounds(0, 0, 0, 0);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		contentPane.add(table_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 123, 463, 209);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton_1_1_1 = new JButton("VIEW ALL ");
		btnNewButton_1_1_1.setBounds(83, 65, 128, 38);
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_1_1_1) {
					table_load();
				}
				
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton_1_1_1);
		
		table = new JTable();
		table.setBounds(147, 367, 1, 1);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("ALL PRISONS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(83, 27, 179, 27);
		contentPane.add(lblNewLabel);
	}
}
