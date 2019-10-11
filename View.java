package Student;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import Student.Controller;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class View extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTable table;
	private JScrollPane scrollPane;
	private static Controller cnt;
	private int count;
//	private String arr;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		cnt = new Controller();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
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
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(62, 38, 48, 14);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(168, 35, 96, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Search(table,txtName.getText());
			}
		});
		btnSave.setBounds(168, 66, 89, 23);
		contentPane.add(btnSave);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 150, 583, 202);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Roll No", "Name", "Father Name", "Gender", "Email", "Address"
			}
		));
		
		JButton btnShowall = new JButton("ShowAll");
		btnShowall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Populate_Table(table);
			}
		});
		btnShowall.setBounds(363, 34, 89, 23);
		contentPane.add(btnShowall);
	}
	void Populate_Table(JTable Table)
	{ 
		ResultSet rs = cnt.Populate_Table();
		try {
			if(rs.next())
			{
				rs.beforeFirst();
				String[][] arr = toarr(rs);
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				mdl.getDataVector().removeAllElements();
				revalidate();
				for(int i =0; i<count; i++)
				{
					mdl.addRow(arr[i]);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(contentPane, "Database is Empty!");
				return;
				
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Database data retrieved Succesfull");
	}
	public void Search(JTable table,String Name)
	{
		ResultSet rs = null; 
		rs = cnt.Search(Name);
		
		try {
			if(rs.next()) //if Database returns something
			{
				rs.beforeFirst();
				
				String[][] arr = toarr(rs); //pulling out data data in array arr from rs resultset	
				
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();//getting table model
				mdl.getDataVector().removeAllElements();	//Removing everything from Table
				revalidate();
				for(int i =0; i<count; i++)
				{
					mdl.addRow(arr[i]);
				}
			}
			else //if Data base returns nothing or result is empty
			{
				JOptionPane.showMessageDialog(contentPane, "No one found by Name "+Name+"!");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String[][] toarr(ResultSet rs)
	{
		count = 0;
		ArrayList<String> array2 = new ArrayList<String>();
		int temp = 0;
		
		try 
		{
			while(rs.next())
			{
				array2.add(rs.getString("st_RollNo"));
				array2.add(rs.getString("st_Name"));
				array2.add(rs.getString("st_FName"));
				array2.add(rs.getString("st_Gender"));
				array2.add(rs.getString("st_Email"));
				array2.add(rs.getString("Address"));
				count++;
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[][] arr = new String[count][6];
		for (int i =0; i<count; i++)
		{
			for(int j=0; j<6; j++)
			{
				arr[i][j] = array2.get(temp);
				temp++;
			}
		}
		return arr;
		
	}
}
