package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Testmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			String hostname = "jdbc:mysql://3.23.105.133:3306/mousikiqa";
			String username = "mouqaadm";
			String password = "ewg5QxYWQPGnsVWU";
			String query = "Select * from user where first_name = 'indrachith'";
			Connection con=DriverManager.getConnection(  
					hostname,username,password);  
			
			/*Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);  */
			
			PreparedStatement pstat = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstat.executeQuery();
			
			//retrieve row count
			rs.last();
			int rows = rs.getRow();
						
			//retrieve column count
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			
			String data[][] = new String[rows][columns];
			
			int i=0;
			rs.beforeFirst();
			while(rs.next()) {
				for(int j=0;j<columns;j++) {
					data[i][j] = rs.getString(j+1);
					System.out.println(data[i][j]);
				}
				i++;
			}
//			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
			con.close();  
		}catch(Exception e){ System.out.println(e);}
		
		
	}

}
