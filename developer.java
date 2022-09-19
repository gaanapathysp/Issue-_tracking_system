package pack1;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class developer extends JFrame {

	private JPanel contentPane;
	private JTextField dev_issueid;
	private JTextField dev_projectname;
	private JTextField c_date;
	private JTextField txtcleared;
	private JTextField sid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					developer frame = new developer();
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
	private JTable table1;
	public developer() {
		connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1373, 685);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DEVELOPER PORTAL\r\n");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setBounds(471, 41, 386, 53);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(51, 141, 510, 507);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ISSUE NO :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(34, 47, 118, 37);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PROJECT NAME :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1_1.setBounds(34, 120, 212, 37);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ISSUE STATUS :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1_1_1.setBounds(34, 259, 212, 37);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("DATE OF CLEARANCE :");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1_1_2.setBounds(34, 184, 212, 37);
		panel.add(lblNewLabel_1_1_2);
		
		dev_issueid = new JTextField();
		dev_issueid.setFont(new Font("Tahoma", Font.BOLD, 17));
		dev_issueid.setBounds(267, 51, 191, 37);
		panel.add(dev_issueid);
		dev_issueid.setColumns(10);
		
		dev_projectname = new JTextField();
		dev_projectname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dev_projectname.setColumns(10);
		dev_projectname.setBounds(267, 120, 191, 37);
		panel.add(dev_projectname);
		
		c_date = new JTextField();
		c_date.setFont(new Font("Tahoma", Font.PLAIN, 15));
		c_date.setColumns(10);
		c_date.setBounds(267, 184, 191, 37);
		panel.add(c_date);
		
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String clearance,istatus,id;
				clearance = c_date.getText();
				istatus = txtcleared.getText();
				id = dev_issueid.getText();
								try {
				pst = con.prepareStatement("update issuetrackers set clearance= ?,issuestatus=? where issueid =?");
				            pst.setString(1, clearance);
				            pst.setString(2, istatus);
				            pst.setString(3, id);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
				            table_load();
				          
				           dev_issueid.setText("");
				       dev_projectname .setText("");
				            c_date.setText("");
				            c_date.requestFocus();
				}
				 
				            catch (SQLException e1) {
				e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(new Color(255, 128, 64));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton.setBounds(34, 332, 191, 52);
		panel.add(btnNewButton);
		
		JButton btnSearch = new JButton("SEARCH(issue no)");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnSearch.setBackground(new Color(128, 255, 0));
		btnSearch.setBounds(10, 433, 222, 52);
		panel.add(btnSearch);
		
		txtcleared = new JTextField();
		txtcleared.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtcleared.setText("Cleared");
		txtcleared.setColumns(10);
		txtcleared.setBounds(267, 259, 191, 37);
		panel.add(txtcleared);
		
		sid = new JTextField();
		sid.setFont(new Font("Tahoma", Font.PLAIN, 16));
	sid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
		            String id = sid.getText();
		 
		                pst = con.prepareStatement("select issueid,projectname from issuetrackers where issueid = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String issueid = rs.getString(1);
		                String projectname = rs.getString(2);
		                
		                
		                dev_issueid.setText(issueid);
		                dev_projectname.setText(projectname);
		            
		            }  
		            else
		            {
		             dev_issueid.setText("");
		             dev_projectname.setText("");
		          
		                
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          System.out.println(ex);
		        }
		}
			}
		);
		sid.setColumns(10);
		sid.setBounds(289, 434, 191, 51);
		panel.add(sid);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                                
                String id;
id  = dev_issueid.getText();
try {
pst = con.prepareStatement("delete from issuetrackers where issueid=?");
    pst.setString(1, id);
    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
    table_load();
  
    dev_issueid.setText("");
    dev_projectname.setText("");
    c_date.setText("");
    dev_issueid.requestFocus();
}

    catch (SQLException e1) {
e1.printStackTrace();
}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.setBounds(267, 332, 191, 52);
		panel.add(btnDelete);
		
		JLabel lblNewLabel_2 = new JLabel(" ---------- CLEARED RECORDS ----------");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_2.setBounds(761, 123, 360, 37);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("REFRESH");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			  table_load();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(62, 41, 157, 45);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(582, 179, 750, 402);
		contentPane.add(scrollPane);
		
		table1 = new JTable();
		scrollPane.setViewportView(table1);
	}
	
	public void table_load ()
    {
     try
     {
    pst = con.prepareStatement("select * from issuetrackers");
    rs = pst.executeQuery();
    table1.setModel(DbUtils.resultSetToTableModel(rs));
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
			System.out.println(ex);
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
	}
}
