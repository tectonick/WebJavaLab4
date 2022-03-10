package LR4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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
	
	private static final Logger logger = LogManager.getLogger("web");
	

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
		int role=(user.getRole()==Role.admin)?1:0;
		try {
			db.update("UPDATE user SET login="+user.getLogin()+", passhash="+user.getPasswordHash()+", role="+role+ " WHERE id="+user.getId());
			logger.info("Updated a user with id "+user.getId());
		} catch (SQLException e) {
			logger.error("Error updating a user with id "+user.getId());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addUser(User user) {
		int role=(user.getRole()==Role.admin)?1:0;
		try {
			db.update("INSERT INTO user VALUES("+user.getId()+","+user.getLogin()+","+user.getPasswordHash()+","+role+")");
			logger.info("Added new user to db with login "+user.getLogin());
		} catch (SQLException e) {
			logger.info("Error adding new user to db with login "+user.getLogin());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteUser(User user) {
		try {
			db.update("DELETE FROM user WHERE id="+user.getId());
			logger.info("User deleted with login "+user.getLogin());
		} catch (SQLException e) {
			logger.info("Error deleting a user with login "+user.getLogin());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public DB getDb() {
		return db;
	}


	public void setDb(DB db) {
		this.db = db;
	}
}
