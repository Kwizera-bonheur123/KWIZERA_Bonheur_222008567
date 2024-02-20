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

public class prison extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField district;
	private JTextField sector;
	private JTable table_1;
	private JButton btnNewButton_1;
	private JTextField prison_id;
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
					prison frame = new prison();
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
	

	

	
	public prison() {
		table_1 = new JTable();
		table_1.setBounds(0, 0, 0, 0);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PRISON NAME :");
		lblNewLabel.setBounds(36, 60, 128, 14);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		name = new JTextField();
		name.setBounds(30, 78, 312, 38);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblPrisonDistrict = new JLabel("PRISON DISTRICT :");
		lblPrisonDistrict.setBounds(33, 139, 161, 14);
		lblPrisonDistrict.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblPrisonDistrict);
		
		district = new JTextField();
		district.setBounds(31, 157, 311, 38);
		district.setColumns(10);
		contentPane.add(district);
		
		JLabel lblPrisonDistrict_1 = new JLabel("PRISON SECTOR :");
		lblPrisonDistrict_1.setBounds(31, 220, 161, 14);
		lblPrisonDistrict_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblPrisonDistrict_1);
		
		sector = new JTextField();
		sector.setBounds(31, 247, 311, 38);
		sector.setColumns(10);
		contentPane.add(sector);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBounds(30, 305, 74, 38);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prison inmate = new prison();
				if(e.getSource()==btnNewButton) {
					inmate.setName(name.getText());
//					inmate.setDistrict(district.getText());
//					inmate.setSector(sector.getText());
//					inmate.insertData();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton);
		
		contentPane.add(table_1);

		btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
//			inmates inmate = new inmates();
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnNewButton_1) {
				int id=Integer.parseInt(prison_id.getText());
//				inmate.delete(id);
				}
			}
		});
		btnNewButton_1.setBounds(107, 305, 82, 38);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("UPDATE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				inmates inmate = new inmates();
				if(e.getSource()==btnNewButton_1_1) {
					int id=Integer.parseInt(prison_id.getText());
//					inmate.setName(name.getText());
//					inmate.setDistrict(district.getText());
//					inmate.setSector(sector.getText());
//					inmate.update(id);
				}
			}
		});
		btnNewButton_1_1.setBounds(192, 306, 81, 38);
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(380, 77, 463, 209);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton_1_1_1 = new JButton("VIEW");
		btnNewButton_1_1_1.setBounds(275, 307, 67, 38);
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_1_1_1) {
					table_load();
				}
				
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton_1_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("PRISONS");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(277, 18, 148, 26);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(380, 297, 463, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Prison ID :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 0, 96, 38);
		panel.add(lblNewLabel_2);
		
		prison_id = new JTextField();
		prison_id.setBounds(95, 4, 348, 38);
		panel.add(prison_id);
		prison_id.setColumns(10);
		
		table = new JTable();
		table.setBounds(147, 367, 1, 1);
		contentPane.add(table);
	}
}
