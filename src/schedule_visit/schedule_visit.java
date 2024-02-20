package schedule_visit;
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

public class schedule_visit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField visitor_id;
	private JTextField inmate_id;
	private JTextField visit_id;
	private JTable table_1;
	private JTable table;
	private JTextField reason;
	
	
	
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
					schedule_visit frame = new schedule_visit();
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
	

	

	
	public schedule_visit() {
		table_1 = new JTable();
		table_1.setBounds(0, 0, 0, 0);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VISITOR_ID :");
		lblNewLabel.setBounds(36, 60, 128, 14);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		visitor_id = new JTextField();
		visitor_id.setBounds(30, 78, 312, 38);
		contentPane.add(visitor_id);
		visitor_id.setColumns(10);
		
		JLabel lblPrisonDistrict = new JLabel("INMATE_ID :");
		lblPrisonDistrict.setBounds(33, 139, 161, 14);
		lblPrisonDistrict.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblPrisonDistrict);
		
		inmate_id = new JTextField();
		inmate_id.setBounds(31, 157, 311, 38);
		inmate_id.setColumns(10);
		contentPane.add(inmate_id);
		
		JLabel lblPrisonDistrict_1 = new JLabel("VISIT_ID:");
		lblPrisonDistrict_1.setBounds(31, 220, 161, 14);
		lblPrisonDistrict_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblPrisonDistrict_1);
		
		visit_id = new JTextField();
		visit_id.setBounds(31, 247, 311, 38);
		visit_id.setColumns(10);
		contentPane.add(visit_id);
		
		JButton btnNewButton = new JButton("SCHEDULE");
		btnNewButton.setBounds(114, 388, 161, 38);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int visitorId = Integer.parseInt(visitor_id.getText());
		        int inmateId = Integer.parseInt(inmate_id.getText());
		        int visitId = Integer.parseInt(visit_id.getText());
		        String reasonText = reason.getText();
		        schedule_visit_entity entity = new schedule_visit_entity();
		        
		        entity.setVisitor_id(visitorId);
		        entity.setPrisoner_id(inmateId);
		        entity.setVisit_id(visitId);
		        entity.setReason(reasonText);
		        
		        entity.insertData();
		    }
		});

		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton);
		
		contentPane.add(table_1);
		
		JLabel lblNewLabel_1 = new JLabel("SCHEDULE VISIT DATE");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(53, 11, 268, 26);
		contentPane.add(lblNewLabel_1);
		
		table = new JTable();
		table.setBounds(147, 367, 1, 1);
		contentPane.add(table);
		
		reason = new JTextField();
		reason.setColumns(10);
		reason.setBounds(30, 327, 311, 38);
		contentPane.add(reason);
		
		JLabel lblPrisonDistrict_1_1 = new JLabel("REASON:");
		lblPrisonDistrict_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPrisonDistrict_1_1.setBounds(30, 302, 161, 14);
		contentPane.add(lblPrisonDistrict_1_1);
	}
}
