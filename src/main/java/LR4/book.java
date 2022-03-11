package LR4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Servlet implementation class book
 */
public class book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public book() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB db=new DB("root","12345678");
		BookingDAO bdao=new BookingDAO(db);
		RoomsDAO rdao=new RoomsDAO(db);
		
		String startdatestr=request.getParameter("startdate");
		String enddatestr=request.getParameter("enddate");
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		
		Date startdate;
		Date enddate;
		try {
			startdate = format.parse(startdatestr);
			enddate=format.parse(enddatestr);
		
		User user=((User)request.getSession().getAttribute("user"));		
		int roomid=Integer.parseInt(request.getParameter("roomid"));
		int userid=user.getId();		
		Room room=rdao.getRoomById(roomid);
	
		
		List<Booking> bookings=bdao.getBookingsForRoom(roomid);
		if (!checkOverlap(bookings, startdate, enddate)) {
			Booking book=new Booking();
			book.setStartDate(startdate);
			book.setEndDate(enddate);
			book.setId(0);		
			book.setRoom(room);
			book.setUser(user);			
			bdao.addBooking(book);		
		}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("rooms.jsp");
	}

	private boolean checkOverlap(List<Booking> bookings, Date startdate, Date enddate) {
		for (Booking book:bookings) {
			Date startdate1=book.getStartDate();
			Date enddate1=book.getEndDate();
			Date startdate2=startdate;
			Date enddate2=enddate;
			if ((startdate1.before(enddate2)||startdate1.equals(enddate2)) && (startdate2.before(enddate1)||startdate2.equals(enddate1))) {
				return true;
			}
		}
		return false;
	}

}
