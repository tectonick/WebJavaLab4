package LR4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * Класс доступа к данным о пользователях.
 * 
 * @author      Николай Киселев <korn9509@gmail.com>
 * @version     1.0
 * @since       1.0
 */
public class UsersDAO{

	private DB db;
	public UsersDAO(DB db){
		this.setDb(db);
		
	}
	

	public User getUserById(int id) {
		User user=null;
		try {
			ResultSet rs=db.query("SELECT * FROM user WHERE id="+id);
			if (rs.next()) {				
				user=new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPasswordHash(rs.getString("passwordhash"));
				boolean isAdmin=rs.getBoolean("role");
				if (isAdmin) {
					user.setRole(Role.admin);
				} else {
					user.setRole(Role.user);
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> getAllUsers() {
		
		User user=null;
		List<User> users=new Vector<User>();
		try {
			ResultSet rs=db.query("SELECT * FROM user");
			while (rs.next()) {				
				user=new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPasswordHash(rs.getString("passwordhash"));
				boolean isAdmin=rs.getBoolean("role");
				if (isAdmin) {
					user.setRole(Role.admin);
				} else {
					user.setRole(Role.user);
				}
				users.add(user);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	
	
	public User getUserByLogin(String login) {
		User user=null;
		try {
			ResultSet rs=db.query("SELECT * FROM user WHERE login="+login);
			if (rs.next()) {				
				user=new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPasswordHash(rs.getString("passwordhash"));
				boolean isAdmin=rs.getBoolean("role");
				if (isAdmin) {
					user.setRole(Role.admin);
				} else {
					user.setRole(Role.user);
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}	
    
	public void updateUser(User user) {	
		
	}
	
	public void addUser(User user) {
		
	}
	
	public void deleteUser(User user) {
		
	}


	public DB getDb() {
		return db;
	}


	public void setDb(DB db) {
		this.db = db;
	}
}
