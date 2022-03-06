package LR4;
import java.sql.*;

public class DB {
	
	Connection con = null;
	
	public void init(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/booking", username,
					password);			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	public ResultSet query(String sql) throws SQLException {
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		return rs;
	}
	public int update(String sql) throws SQLException {
		Statement st=con.createStatement();
		return st.executeUpdate(sql);
	}
}
