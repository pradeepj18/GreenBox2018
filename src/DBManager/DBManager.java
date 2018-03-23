package DBManager;
import java.sql.*;


public class DBManager
{
	public static Connection con;
	static Statement stmt;
	static ResultSet rs;
	
	
	public static void loadDriver() throws SQLException 
	{   
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenbox2018","root", "thompson");
			stmt=con.createStatement();
		
		}
		catch(Exception e)
		{
			System.out.println("Exception in DBManager....for loadDriver.."+e.getMessage());
		}
	}

	public static ResultSet fetchQuery(String query)
	{
		try
		{
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			return rs;
		}
		catch(Exception e)
		{
			System.out.println("Exception in DBManager....for ResultSet.."+e.getMessage());
		}
		return null;
	}
	
	public int insert(String q)
	{
		try
		{
			int rowAffected=stmt.executeUpdate(q);
			if(rowAffected >0)
				/*JOptionPane.showMessageDialog(null,"Insert Successfully...");*/
			/*else*/
				/*JOptionPane.showMessageDialog(null,"Not Inserted...");*/
				//System.out.println("insert successfully...");
			return rowAffected;
		}
		
			
		catch(Exception e)
		{
			System.out.println("Exception in DBManager....for insert.."+e.getMessage());
		}
		return 0;
		
	}
	
	public static int delete(String query)
	{
		try
		{
			int rowAffected=stmt.executeUpdate(query);
			if(rowAffected >0)
				System.out.println("delete successfully...");
			return rowAffected;
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in DBManager....for insert.."+e.getMessage());
		}
		return 0;
	}
	
	public static int getMaxId(String tablename,String columnname)
	{
		try
		{
			int Id=0;
			String query="select max("+columnname+") from "+tablename;
			ResultSet rs1=stmt.executeQuery(query);
			if(rs1.next())				
				Id=rs1.getInt(1)+1;
			else
				Id=1;
			return Id;
		}
		catch(Exception e)
		{
			System.out.println("Exception in DBManager....for getMaxId.."+e.getMessage());
		}
		return 1;
	}
	
	public static int getMaxId(String tablename,String columnname,String columnname1)
	{
		try
		{
			int Id=0;
			String query="select max("+columnname+") from "+tablename+" where tourId="+columnname1+";";
			ResultSet rs1=stmt.executeQuery(query);
			if(rs1.next())				
				Id=rs1.getInt(1)+1;
			else
				Id=1;
			return Id;
		}
		catch(Exception e)
		{
			System.out.println("Exception in DBManager....for getMaxId.."+e.getMessage());
		}
		return 1;
	}
	
	/*public static void close()
	{
		try
		{
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in CLose....");
e.printStackTrace();
		}
		
	}
*/
}