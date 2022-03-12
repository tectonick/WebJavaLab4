package LR4;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
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
	private UsersDAO usersDAO;
	private static final Logger logger = LogManager.getLogger("web");
	
	public UserManager(UsersDAO usersDAO){
		this.usersDAO=usersDAO;
		
	}
	
	
		static final String HEXES = "0123456789abcdef";
		public static String getHex( byte [] raw ) {
		  if ( raw == null ) {
		    return null;
		  }
		  final StringBuffer hex = new StringBuffer( 2 * raw.length );
		  for ( final byte b : raw ) {
		    hex.append(HEXES.charAt((b & 0xF0) >> 4))
		     .append(HEXES.charAt((b & 0x0F)));
		  }
		  return hex.toString();
		}
	
	
	
	
	
	public User auth(String login, String password) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes("UTF-8"));
			String passHash = getHex(messageDigest.digest());
			User user=usersDAO.getUserByLogin(login);			
			
			if (user==null){
				throw new NullPointerException("No such user");
			}
				 if (user.getPasswordHash().equals(passHash)) {
					 
					 return user;
				 }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e){
			logger.info("User "+login+" is not found");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	
	@SuppressWarnings("unused")
	private void initAdmin() {
		String login="admin";
		String password="12345";
		Role role=Role.admin;

			MessageDigest messageDigest;
			try {
				messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.update(password.getBytes("UTF-8"));
				String passHash = getHex(messageDigest.digest());
				User newUser=new User(login, passHash, role);
				usersDAO.addUser(newUser);	
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public boolean registerUser(String login, String password, Role role) throws Exception {
			User existingUser=usersDAO.getUserByLogin(login);
			if(existingUser!=null) throw new Exception("Пользователь с таким именем уже существует");
			
			MessageDigest messageDigest;
			try {
				messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.update(password.getBytes("UTF-8"));
				String passHash = getHex(messageDigest.digest());
				User newUser=new User(login, passHash, role);
				usersDAO.addUser(newUser);
				return true;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
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
