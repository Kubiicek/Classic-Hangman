package model.entites;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class JLanguage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public JLanguage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(160, 82, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(57, 11, 322, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(121, 11, 94, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("English or Portuguese (Brazil)");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(58, 36, 223, 27);
		panel.add(lblNewLabel);
		
		JButton btnRegister = new JButton("English");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				GameGuiEN JGameGuiEN = new GameGuiEN();
				JGameGuiEN.setLocationRelativeTo(JGameGuiEN);
				JGameGuiEN.setVisible(true);
			}
			
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(128, 64, 64));
		btnRegister.setBounds(10, 106, 105, 39);
		panel.add(btnRegister);
		
		JButton btnLogin = new JButton("Portuguese");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				GameGuiBR JGameGuiBR = new GameGuiBR();
				JGameGuiBR.setLocationRelativeTo(JGameGuiBR);
				JGameGuiBR.setVisible(true);
			}
			
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBackground(new Color(128, 64, 64));
		btnLogin.setBounds(207, 106, 105, 39);
		panel.add(btnLogin);
	}
}
