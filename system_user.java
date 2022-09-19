package pack1;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Dimension;

public class system_user extends JFrame {

	private JPanel contentPane;
	private JTextField issueno;
	private JTextField prj_name;
	private JTextField idates;
	private JTextField d_date;
	private JTextField discriptions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					system_user frame = new system_user();
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
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	
	
	public system_user() {
		connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1452, 739);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  SYSTEM USER PORTAL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel.setBounds(511, 20, 396, 78);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(59, 108, 515, 582);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ISSUE NO :");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(33, 52, 114, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PROJECT NAME :");
		lblNewLabel_1_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(33, 113, 173, 36);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("DATE : (dd/mm/yyyy)");
		lblNewLabel_1_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(33, 178, 223, 36);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("DUE DATE :(dd/mm/yyyy)");
		lblNewLabel_1_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(33, 242, 239, 36);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("ISSUE DISCRIPTION :");
		lblNewLabel_1_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel_1_4.setBounds(33, 313, 189, 36);
		panel.add(lblNewLabel_1_4);
		
		issueno = new JTextField();
		issueno.setFont(new Font("Tahoma", Font.BOLD, 17));
		issueno.setBounds(282, 53, 223, 36);
		panel.add(issueno);
		issueno.setColumns(10);
		
		prj_name = new JTextField();
		prj_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		prj_name.setColumns(10);
		prj_name.setBounds(282, 117, 223, 36);
		panel.add(prj_name);
		
		idates = new JTextField();
		idates.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idates.setColumns(10);
		idates.setBounds(282, 182, 223, 36);
		panel.add(idates);
		
		d_date = new JTextField();
		d_date.setFont(new Font("Tahoma", Font.PLAIN, 15));
		d_date.setColumns(10);
		d_date.setBounds(282, 246, 223, 36);
		panel.add(d_date);
		
		discriptions = new JTextField();
		discriptions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		discriptions.setColumns(10);
		discriptions.setBounds(282, 313, 223, 130);
		panel.add(discriptions);
		
		JButton btnNewButton = new JButton("REPORT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String pname,issue_dis,status,ino, idate,ddate,clear;
				
				
				pname = prj_name.getText();
				issue_dis = discriptions.getText();
			    status= "active";
			    clear = "Not cleared";
			    ino = issueno.getText();
       	        idate =  idates.getText();
	            ddate = d_date.getText();
				try {
				pst = con.prepareStatement("insert into issuetrackers (projectname,projectdiscription,issuestatus,issueid,clearance,issuedate,duedate)values(?,?,?,?,?,?,?)");
				pst.setString(1, pname);
				pst.setString(2, issue_dis);
				pst.setString(3, status);
				pst.setString(4, ino);
		     	pst.setString(5, clear);
		     	pst.setString(6, idate);
				pst.setString(7, ddate);
				
				
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
				table_load();
				
				          
				   }
				 
				catch (SQLException e1)
				        {
				e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(new Color(255, 128, 64));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnNewButton.setBounds(159, 494, 201, 55);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("---------- ISSUE RECORDS ----------");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 21));
		lblNewLabel_2.setBounds(775, 95, 341, 36);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("REFRESH");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
              table_load();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(10, 20, 108, 36);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(597, 148, 831, 482);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}
	public void table_load ()
    {
     try
     {
    pst = con.prepareStatement("select * from issuetrackers");
    rs = pst.executeQuery();
    table.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
    }
	
	
	public void connect()
	{
		try {
			 
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/issuetracker","root","spgs552003");
		}
		catch(ClassNotFoundException ex)
		{
			
		}
		catch(SQLException ex)
		{
			
		}
	}
}
