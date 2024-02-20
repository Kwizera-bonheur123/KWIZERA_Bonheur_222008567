package admin_Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.admin;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

public class AdminLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailtext;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 329);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(216, 10, 2, 2);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(86, 135, 91, 14);
		contentPane.add(lblNewLabel);
		
		emailtext = new JTextField();
		emailtext.setBackground(new Color(245, 245, 245));
		emailtext.setBounds(86, 94, 214, 34);
		contentPane.add(emailtext);
		emailtext.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(87, 69, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("LOGIN");
		lblNewLabel_2.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(133, 10, 116, 29);
		contentPane.add(lblNewLabel_2);
		
		JButton login = new JButton("LOGIN");
		login.setBackground(new Color(25, 25, 112));
		login.setForeground(new Color(255, 255, 255));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin ad = new admin();
				if(e.getSource()==login) {
					ad.setEmail(emailtext.getText());
					char[] passwordChars = passwordField.getPassword();
					String passwordString = new String(passwordChars);
					ad.setPassword(passwordString);
					ad.login();
				}
			}
		});
		login.setBounds(88, 225, 212, 39);
		contentPane.add(login);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setBounds(88, 160, 212, 34);
		contentPane.add(passwordField);
	}
}
