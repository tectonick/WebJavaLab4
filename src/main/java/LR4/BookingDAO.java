package LR4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс доступа к данным о бронях.
 * 
 * @author      Николай Киселев <korn9509@gmail.com>
 * @version     1.0
 * @since       1.0
 */
public class BookingDAO{

	private DB db;
	private UsersDAO usersdao;
	private RoomsDAO roomsdao;
	public BookingDAO(DB db){
		this.setDb(db);
		usersdao=new UsersDAO(db);
		roomsdao=new RoomsDAO(db);
	}

	private static final Logger logger = LogManager.getLogger("web");
	
	public Booking getBookingById(int id) {
		Booking booking=null;
		try {
			ResultSet rs=db.query("SELECT * FROM user WHERE id="+id);
			if (rs.next()) {				
				booking=new Booking();
				booking.setId(rs.getInt("id"));
				booking.setStartDate(rs.getDate("startdate"));
				booking.setEndDate(rs.getDate("enddate"));
				
				User user=usersdao.getUserById(rs.getInt("userid"));
				Room room=roomsdao.getRoomById(rs.getInt("roomid"));
				booking.setUser(user);
				booking.setRoom(room);				
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booking;	
	}
	
	public List<Booking> getBookingsForUser(int userid) {
		List<Booking> books=new Vector<Booking>();
		Booking booking=null;
		try {
			ResultSet rs=db.query("SELECT * FROM user WHERE userid="+userid);
			while (rs.next()) {				
				booking=new Booking();
				booking.setId(rs.getInt("id"));
				booking.setStartDate(rs.getDate("startdate"));
				booking.setEndDate(rs.getDate("enddate"));
				User user=usersdao.getUserById(rs.getInt("userid"));
				Room room=roomsdao.getRoomById(rs.getInt("roomid"));
				booking.setUser(user);
				booking.setRoom(room);
				
				books.add(booking);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;	
	}
	
	
	public List<Booking> getBookingsForRoom(int roomid) {
		List<Booking> books=new Vector<Booking>();
		Booking booking=null;
		try {
			ResultSet rs=db.query("SELECT * FROM user WHERE roomid="+roomid);
			while (rs.next()) {				
				booking=new Booking();
				booking.setId(rs.getInt("id"));
				booking.setStartDate(rs.getDate("startdate"));
				booking.setEndDate(rs.getDate("enddate"));
				User user=usersdao.getUserById(rs.getInt("userid"));
				Room room=roomsdao.getRoomById(rs.getInt("roomid"));
				booking.setUser(user);
				booking.setRoom(room);
				
				books.add(booking);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;	
	}
	
	
	
	
    
	public void updateBooking(Booking book) {	
		try {
			db.update("UPDATE booking SET roomid="+book.getRoom().getId()+", userid="+book.getUser().getId()+", startdate=\""+book.getStartDate()+"\", enddate=\""+book.getEndDate()+ "\" WHERE id="+book.getId());
			logger.info("Updated a booking with id "+book.getId());
		} catch (SQLException e) {
			logger.info("Error updating a booking with id "+book.getId());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addBooking(Booking book) {
		try {
			db.update("INSERT INTO booking VALUES("+book.getId()+","+book.getRoom().getId()+","+book.getUser().getId()+",\""+book.getStartDate()+"\",\""+book.getEndDate()+"\")");
			logger.info("Added new booking");
		} catch (SQLException e) {
			logger.info("Error adding a booking");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteBooking(Booking book) {
		try {
			db.update("DELETE FROM booking WHERE id="+book.getId());
			logger.info("Deleted a booking with id "+book.getId());
		} catch (SQLException e) {
			logger.info("Error deleting a booking with id "+book.getId());
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