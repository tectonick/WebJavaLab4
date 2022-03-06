import java.sql.*;

public class db {
	
	Connection con = null;
	
	public void init(String username, String password) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test", username,
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
}
