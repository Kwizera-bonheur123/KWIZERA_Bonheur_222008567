package employees;
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

public class employees extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fnmae;
	private JTextField lname;
	private JTextField identity;
	private JTable table_1;
	private JButton btnNewButton_1;
	private JTextField prison_id;
	private JTable table;
	private JTextField phone;
	private JTextField DoB;
	private JTextField email;
	private JPasswordField password;
	private JComboBox gender;
	private JComboBox martial_status;
	private JTextField role;
	
	
	public void table_load() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jail_management_system","root","");
			Statement st = con.createStatement();
			String query= "select admin_id, fname, lname, id_number, phone, gender, martial_status, DoB, email, role from admin";
			ResultSet rs= st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();	
			
			model.setRowCount(0);
			
			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i = 0; i < cols; i++)
				colName[i] = rsmd.getColumnName(i + 1);
			model.setColumnIdentifiers(colName);
			String id,fname,lname,id_number,phone,gender,marital_status,email,role;
			Date DoB;
			while(rs.next()) {
				id = rs.getString(1);
				fname = rs.getString(2);
				lname = rs.getString(3);
				id_number = rs.getString(4);
				phone = rs.getString(5);
				gender = rs.getString(6);
				marital_status = rs.getString(7);
				DoB = rs.getDate(8);;
				email = rs.getString(9);
				role = rs.getString(10);
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            String formattedDate = dateFormat.format(DoB);
	            
				String[] row = {id,fname,lname,id_number,phone,gender,marital_status,formattedDate,email,role};
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
					employees frame = new employees();
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
	

	

	
	public employees() {
		table_1 = new JTable();
		table_1.setBounds(0, 0, 0, 0);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1119, 788);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First name:");
		lblNewLabel.setBounds(111, 60, 128, 14);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		fnmae = new JTextField();
		fnmae.setBounds(113, 78, 400, 38);
		contentPane.add(fnmae);
		fnmae.setColumns(10);
		
		JLabel lblPrisonDistrict = new JLabel("Last name :");
		lblPrisonDistrict.setBounds(112, 127, 161, 14);
		lblPrisonDistrict.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblPrisonDistrict);
		
		lname = new JTextField();
		lname.setBounds(111, 145, 400, 38);
		lname.setColumns(10);
		contentPane.add(lname);
		
		JLabel lblPrisonDistrict_1 = new JLabel("Identity card :");
		lblPrisonDistrict_1.setBounds(112, 195, 161, 14);
		lblPrisonDistrict_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblPrisonDistrict_1);
		
		identity = new JTextField();
		identity.setBounds(113, 211, 400, 38);
		identity.setColumns(10);
		contentPane.add(identity);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBounds(111, 410, 82, 38);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employee_entity emp = new employee_entity();
				if(e.getSource()==btnNewButton) {
					emp.setFname(fnmae.getText());
					emp.setLname(lname.getText());
					emp.setId_card(identity.getText());
					emp.setPhone(phone.getText());
					
					
					String selectedOption = gender.getSelectedItem().toString();
					emp.setGender(selectedOption);
					
					String selectedOption_1 = martial_status.getSelectedItem().toString();
					emp.setMarital_status(selectedOption_1);
					
					emp.setDate(DoB.getText());
					emp.setEmail(email.getText());
					
					char[] passwordChars = password.getPassword();
					String passwordString = new String(passwordChars);
					emp.setPassword(passwordString);
					
					emp.setRole(role.getText());
					emp.insertData();
					
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton);
		
		contentPane.add(table_1);

		btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
			employee_entity emp = new employee_entity();
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnNewButton_1) {
				int id=Integer.parseInt(prison_id.getText());
				emp.delete(id);
				}
			}
		});
		btnNewButton_1.setBounds(219, 410, 82, 38);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("UPDATE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employee_entity emp = new employee_entity();
				if(e.getSource()==btnNewButton_1_1) {
				int id=Integer.parseInt(prison_id.getText());
					emp.setFname(fnmae.getText());
					emp.setLname(lname.getText());
					emp.setId_card(identity.getText());
					emp.setPhone(phone.getText());
					
					
					String selectedOption = gender.getSelectedItem().toString();
					emp.setGender(selectedOption);
					
					String selectedOption_1 = martial_status.getSelectedItem().toString();
					emp.setMarital_status(selectedOption_1);
					
					emp.setDate(DoB.getText());
					emp.setEmail(email.getText());
					
					char[] passwordChars = password.getPassword();
					String passwordString = new String(passwordChars);
					emp.setPassword(passwordString);
					
					emp.setRole(role.getText());
					emp.update(id);
				}
			}
		});
		btnNewButton_1_1.setBounds(338, 411, 81, 38);
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(111, 463, 915, 209);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton_1_1_1 = new JButton("VIEW");
		btnNewButton_1_1_1.setBounds(440, 410, 73, 38);
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_1_1_1) {
					table_load();
				}
				
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton_1_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("EMPLOYEES");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(433, 0, 148, 26);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(626, 406, 400, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Employee ID :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(4, 1, 115, 38);
		panel.add(lblNewLabel_2);
		
		prison_id = new JTextField();
		prison_id.setBounds(100, 4, 290, 38);
		panel.add(prison_id);
		prison_id.setColumns(10);
		
		table = new JTable();
		table.setBounds(147, 367, 1, 1);
		contentPane.add(table);
		
		JLabel lblPrisonDistrict_1_1 = new JLabel("Phone :");
		lblPrisonDistrict_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPrisonDistrict_1_1.setBounds(116, 267, 161, 14);
		contentPane.add(lblPrisonDistrict_1_1);
		
		phone = new JTextField();
		phone.setBackground(Color.WHITE);
		phone.setColumns(10);
		phone.setBounds(113, 282, 400, 38);
		contentPane.add(phone);
		
		JLabel lblPrisonDistrict_1_1_1 = new JLabel("Gender : ");
		lblPrisonDistrict_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPrisonDistrict_1_1_1.setBounds(110, 332, 161, 14);
		contentPane.add(lblPrisonDistrict_1_1_1);
		
		gender = new JComboBox();
		gender.setModel(new DefaultComboBoxModel(new String[] {"MALE", "FEMALE"}));
		gender.setBackground(new Color(255, 255, 255));
		gender.setBounds(113, 348, 400, 32);
		contentPane.add(gender);
		
		JLabel lblMartialStatus = new JLabel("Marital status :");
		lblMartialStatus.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMartialStatus.setBounds(625, 61, 155, 14);
		contentPane.add(lblMartialStatus);
		
		DoB = new JTextField();
		DoB.setColumns(10);
		DoB.setBounds(626, 142, 400, 38);
		contentPane.add(DoB);
		
		martial_status = new JComboBox();
		martial_status.setModel(new DefaultComboBoxModel(new String[] {"Single", "Married", "Divorced"}));
		martial_status.setBackground(Color.WHITE);
		martial_status.setBounds(626, 81, 400, 32);
		contentPane.add(martial_status);
		
		JLabel lblDateOfBirth = new JLabel("Date of birth :");
		lblDateOfBirth.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDateOfBirth.setBounds(626, 126, 161, 14);
		contentPane.add(lblDateOfBirth);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(626, 210, 400, 38);
		contentPane.add(email);
		
		JLabel lblPrisonDistrict_1_2 = new JLabel("Email :");
		lblPrisonDistrict_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPrisonDistrict_1_2.setBounds(625, 192, 161, 14);
		contentPane.add(lblPrisonDistrict_1_2);
		
		JLabel lblPrisonDistrict_1_1_2 = new JLabel("Password :");
		lblPrisonDistrict_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPrisonDistrict_1_1_2.setBounds(626, 263, 161, 14);
		contentPane.add(lblPrisonDistrict_1_1_2);
		
		password = new JPasswordField();
		password.setBounds(626, 281, 400, 38);
		contentPane.add(password);
		
		JLabel lblPrisonDistrict_1_1_1_1 = new JLabel("Role : ");
		lblPrisonDistrict_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPrisonDistrict_1_1_1_1.setBounds(628, 327, 161, 14);
		contentPane.add(lblPrisonDistrict_1_1_1_1);
		
		role = new JTextField();
		role.setColumns(10);
		role.setBackground(Color.WHITE);
		role.setBounds(626, 342, 400, 38);
		contentPane.add(role);
	}
}
