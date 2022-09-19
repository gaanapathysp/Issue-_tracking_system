package pack1;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;
import java.awt.Color;

public class login_window {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_window window = new login_window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login_window() {
		initialize();
		connect();
		}
	Connection con ;
	PreparedStatement pst;
	ResultSet rs;
	public void connect()
	{
		try {
			 
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/issuetracker","root","spgs552003");
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println(ex);
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
	}
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 128, 128));
		frame.setBounds(100, 100, 1125, 654);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ISSUE TRACKING SYSTEM");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lblNewLabel.setBounds(392, 40, 374, 58);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(297, 126, 590, 393);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("USER NAME : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1.setBounds(43, 77, 154, 47);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD : ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1_1.setBounds(43, 175, 154, 47);
		panel.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Login as Developer\r\n");
		btnNewButton.setBackground(new Color(128, 255, 255));
		btnNewButton.setBounds(36, 294, 239, 42);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				developer jf1 = new developer();
				jf1.setVisible(true);
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JButton btnSystemUser = new JButton("Login as system user");
		btnSystemUser.setBackground(new Color(255, 128, 192));
		btnSystemUser.setBounds(311, 294, 255, 42);
		panel.add(btnSystemUser);
		btnSystemUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				system_user jf2 = new system_user();
				jf2.setVisible(true);
			}
		});
		btnSystemUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(258, 80, 255, 44);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_1.setColumns(10);
		textField_1.setBounds(258, 178, 255, 44);
		panel.add(textField_1);
	}
	
	
	public void table_load()
    {
     try
     {
    pst = con.prepareStatement("select * from issuetrackers");
    rs = pst.executeQuery();
    scrollPane.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
    }
}
