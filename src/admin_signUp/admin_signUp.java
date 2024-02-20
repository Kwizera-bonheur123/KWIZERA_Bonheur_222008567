package admin_signUp;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.admin;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class admin_signUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fnametext;
	private JTextField lnametext;
	private JTextField idNumbertext;
	private JTextField phonetext;
	private JTextField martialStatustext;
	private JTextField dobtxt;
	private JTextField emailtext;
	private JPasswordField passwordtext;
	private JComboBox gendertxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_signUp frame = new admin_signUp();
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
	public admin_signUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblfnmae = new JLabel("FIRST NAME :");
		lblfnmae.setBounds(10, 25, 77, 23);
		contentPane.add(lblfnmae);
		
		JLabel lbllname = new JLabel("LAST NAME :");
		lbllname.setBounds(10, 69, 77, 23);
		contentPane.add(lbllname);
		
		fnametext = new JTextField();
		fnametext.setBounds(97, 20, 180, 33);
		contentPane.add(fnametext);
		fnametext.setColumns(10);
		
		lnametext = new JTextField();
		lnametext.setBounds(97, 64, 180, 33);
		lnametext.setColumns(10);
		contentPane.add(lnametext);
		
		JLabel lblIdNumber = new JLabel("ID NUMBER :");
		lblIdNumber.setBounds(10, 119, 77, 23);
		contentPane.add(lblIdNumber);
		
		idNumbertext = new JTextField();
		idNumbertext.setBounds(97, 112, 180, 33);
		idNumbertext.setColumns(10);
		contentPane.add(idNumbertext);
		
		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setBounds(10, 170, 77, 23);
		contentPane.add(lblPhone);
		
		phonetext = new JTextField();
		phonetext.setBounds(97, 160, 180, 33);
		phonetext.setColumns(10);
		contentPane.add(phonetext);
		
		JLabel lblGender = new JLabel(" Gender :");
		lblGender.setBounds(10, 210, 77, 23);
		contentPane.add(lblGender);
		
		JLabel lblMartialStatus = new JLabel("Martial status :");
		lblMartialStatus.setBounds(348, 30, 77, 23);
		contentPane.add(lblMartialStatus);
		
		martialStatustext = new JTextField();
		martialStatustext.setBounds(425, 27, 180, 33);
		martialStatustext.setColumns(10);
		contentPane.add(martialStatustext);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth :");
		lblDateOfBirth.setBounds(348, 77, 77, 23);
		contentPane.add(lblDateOfBirth);
		
		dobtxt = new JTextField();
		dobtxt.setBounds(425, 73, 180, 33);
		dobtxt.setColumns(10);
		contentPane.add(dobtxt);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(348, 124, 77, 23);
		contentPane.add(lblEmail);
		
		emailtext = new JTextField();
		emailtext.setBounds(425, 123, 180, 33);
		emailtext.setColumns(10);
		contentPane.add(emailtext);
		
		JLabel lblPhone_1_1 = new JLabel("Password :");
		lblPhone_1_1.setBounds(348, 183, 77, 23);
		contentPane.add(lblPhone_1_1);
		
		passwordtext = new JPasswordField();
		passwordtext.setBounds(423, 179, 182, 33);
		contentPane.add(passwordtext);
		
		JButton btnNewButton = new JButton("CANCEL");
		btnNewButton.setBounds(209, 285, 126, 33);
		contentPane.add(btnNewButton);
		
		JButton signUp = new JButton("SIGN UP");
		signUp.setBounds(380, 285, 132, 33);
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin ad = new admin();
				if(e.getSource()==signUp) {
					ad.setFname(fnametext.getText());
					ad.setLname(lnametext.getText());
					ad.setIdNumber(idNumbertext.getText());
					ad.setPhone(phonetext.getText());
					ad.setMartialStatus(martialStatustext.getText());
					String selectedOption = gendertxt.getSelectedItem().toString();
					ad.setGender(selectedOption);
					ad.setDoB(dobtxt.getText());
					ad.setEmail(emailtext.getText());
					char[] passwordChars = passwordtext.getPassword();
					String passwordString = new String(passwordChars);
					ad.setPassword(passwordString);
					ad.insertData();
					System.out.print(ad.getFname() + " " + ad.getLname() + " " + ad.getIdNumber() + " " + ad.getGender() + " " + ad.getPhone() + " " + ad.getMartialStatus() + " " + ad.getDoB() + " " + ad.getEmail() + " " + ad.getPassword() );
				}
			}
		});
		contentPane.add(signUp);
		String[] gender = {"Male", "Female"};
		gendertxt = new JComboBox(gender);
		gendertxt.setBounds(97, 210, 180, 23);
		contentPane.add(gendertxt);
	}
}
