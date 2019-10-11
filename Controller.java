package Student;

import java.sql.ResultSet;

import Student.Model;



public class Controller {
	private Model model;
	
	public Controller()
	{
		model = new Model();	
	}
	public ResultSet Populate_Table()
	{
		//System.out.println("Population Ftn");
		ResultSet rs=null;
		rs = model.Retrieve_Date("SELECT * FROM st_info");
		return rs;
	}
	public ResultSet Search(String Name)
	{
		ResultSet rs = model.Retrieve_Date("SELECT * FROM st_info WHERE st_NAME LIKE  '%"+Name+"%'");
		return rs;
		
	}

}
