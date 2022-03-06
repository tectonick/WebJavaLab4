package LR4;
import java.io.Serializable;


/**
 * Класс пользователя.
 * 
 * @author      Николай Киселев <korn9509@gmail.com>
 * @version     1.0
 * @since       1.0
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	/**
	* Имя пользователя.       
	*/
	private int id;
	private String login;
	/**
	* SHA хэш пароля.
	*/
	private String passwordHash;
	/**
	* Роль(администратор или обычный пользователь).       
	*/
	private Role role;
	
	
	   /**
     * Конструктор пользователя
     * 
     * @param  login Имя пользователя. 
     * @param  passwordHash SHA хэш пароля.
     * @param  role Роль(администратор или обычный пользователь).       
     */
	public User(String login, String passwordHash, Role role) {
		this.setLogin(login);
		this.setPasswordHash(passwordHash);
		this.setRole(role);
	}
	public User() {
		
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}


/**
* Роль(администратор или обычный пользователь).       
*/
enum Role{admin, user}