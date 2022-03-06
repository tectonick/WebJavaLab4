package LR4;
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
		return null;
	}
	
	public User getUserByLogin(String login) {
		return null;
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
