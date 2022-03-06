import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Класс управления пользователями, создания, удаления, а также авторизации.
 * 
 * @author      Николай Киселев <korn9509@gmail.com>
 * @version     1.0
 * @since       1.0
 */
public class UserManager {
	UsersDAO usersDAO;
	
	
	public UserManager(UsersDAO usersDAO){
		this.usersDAO=usersDAO;
		
	}

	
	public boolean auth(String login, String password) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			String passHash = new String(messageDigest.digest());
			User user=usersDAO.getUserByLogin(login);
			
				 if (user.getPasswordHash().equals(passHash)) {
					 return true;
				 }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
	
	
	@SuppressWarnings("unused")
	private void initAdmin() {
		String login="admin";
		String password="12345";
		Role role=Role.admin;

			MessageDigest messageDigest;
			try {
				messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.update(password.getBytes());
				String passHash = new String(messageDigest.digest());
				User newUser=new User(login, passHash, role);
				usersDAO.addUser(newUser);	
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void registerUser(String login, String password, Role role) throws Exception {
			User existingUser=usersDAO.getUserByLogin(login);
			if(existingUser!=null) throw new Exception("Пользователь с таким именем уже существует");
			
			MessageDigest messageDigest;
			try {
				messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.update(password.getBytes());
				String passHash = new String(messageDigest.digest());
				User newUser=new User(login, passHash, role);
				usersDAO.addUser(newUser);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void deleteUser(String login) throws Exception {
			User userToDelete=usersDAO.getUserByLogin(login);
			if (userToDelete!=null) {
				usersDAO.deleteUser(userToDelete);
			}
			else {
				throw new Exception("Не найден такой пользователь");
			}					
	}
}
