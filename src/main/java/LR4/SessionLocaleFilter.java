package LR4;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;


/**
 * Servlet Filter implementation class SessionLocaleFilter
 */


public class SessionLocaleFilter extends HttpFilter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public SessionLocaleFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
		      throws IOException, ServletException {

		        HttpServletRequest req = (HttpServletRequest) request;
		        
		        if (req.getParameter("sessionLocale") != null) {
		            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
		        }
		        chain.doFilter(request, response);
		    }

}
