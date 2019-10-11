package Student;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {
	private Connection cn;
	private Statement s;
	public Model()
	{
		Connect();
	}
	public void Connect()
	{
		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost/student_info"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			//System.out.println("Connection Successfull!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ResultSet Retrieve_Date(String query)
	{
		ResultSet rs = null;
		try {
			s = cn.createStatement();
			//System.out.println("Statement Created Succesfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			s.execute(query);
			//System.out.println("Statement executed Succesfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = s.executeQuery(query);
			//System.out.println("execute query worked Succesfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
		
	}
	

}
