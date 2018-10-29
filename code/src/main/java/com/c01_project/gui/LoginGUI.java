package c01_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("TEQ Login Page (Team 26)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 377);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(153, 100, 137, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(153, 143, 137, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblLoginPage = new JLabel("TEQ Login Page");
		lblLoginPage.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblLoginPage.setBounds(135, 26, 186, 48);
		contentPane.add(lblLoginPage);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(79, 103, 72, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(79, 146, 69, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			// This is when the user has clicked the "login button"
			public void mouseClicked(MouseEvent arg0) {
				// textField.getText() for the username field
				// textField_1.getText() for the password field
				// I think we want to check if the data base has this account
				System.out.println("Login clicked! User name is: " + textField.getText() + " password given is: " + textField_1.getText());
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogin.setBounds(182, 193, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("User has pressed Register button!");
			}
		});
		btnRegister.setBounds(375, 304, 89, 23);
		contentPane.add(btnRegister);
	}
}
