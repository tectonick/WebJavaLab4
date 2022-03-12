package LR4;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class auth
 */
public class auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger("web");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public auth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login=request.getParameter("login");
		String password=request.getParameter("password");
		DB db=new DB("root","12345678");
		UsersDAO udao=new UsersDAO(db);
		UserManager umanager=new UserManager(udao);
		User user=umanager.auth(login, password);
		
		if (user!=null) {
			HttpSession session=request.getSession(true);
			session.setAttribute("user", user);
			logger.info("User "+login+" has logged into the app");
			response.sendRedirect("index.jsp");
		} else {
			logger.info("User "+login+" has tried to login into the app");
			response.sendRedirect("login.jsp");
		}

	}

}
