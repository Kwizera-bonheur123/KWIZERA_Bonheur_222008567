package visitor_home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.admin;
import employees.employees;
import prisons.prison;
import prisons.viewPrisons;
import scheduled_visits.visits;
import visitors.visitors;
import inmates.inmates;
import inmates.viewInmates;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class visitor_home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visitor_home frame = new visitor_home();
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
	public visitor_home() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 927, 368);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("HOME");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 128));
		btnNewButton.setBounds(57, 21, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnPrisoners = new JButton("INMATES");
		btnPrisoners.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewInmates ad = new viewInmates();
				if(e.getSource()==btnPrisoners) {
					ad.main(new String[0]);
				}
			}
		});
		btnPrisoners.setForeground(new Color(0, 0, 128));
		btnPrisoners.setBackground(new Color(255, 255, 255));
		btnPrisoners.setBounds(180, 21, 89, 23);
		contentPane.add(btnPrisoners);
		
		JButton btnPrisons = new JButton("PRISONS");
		btnPrisons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewPrisons ad = new viewPrisons();
				if(e.getSource()==btnPrisons) {
					ad.main(new String[0]);
				}
			}
		});
		btnPrisons.setForeground(new Color(0, 0, 128));
		btnPrisons.setBackground(new Color(255, 255, 255));
		btnPrisons.setBounds(312, 21, 89, 23);
		contentPane.add(btnPrisons);
		
		JButton btnPrisons_1_1 = new JButton("SCHEDULE VISIT");
		btnPrisons_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visits ad = new visits();
				if(e.getSource()==btnPrisons_1_1) {
					ad.main(new String[0]);
				}
			}
		});
		btnPrisons_1_1.setForeground(new Color(0, 0, 128));
		btnPrisons_1_1.setBackground(Color.WHITE);
		btnPrisons_1_1.setBounds(427, 21, 150, 23);
		contentPane.add(btnPrisons_1_1);
	}
}
