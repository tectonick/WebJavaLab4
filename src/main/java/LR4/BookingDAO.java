package LR4;
/**
 * ����� ������� � ������ � ������.
 * 
 * @author      ������� ������� <korn9509@gmail.com>
 * @version     1.0
 * @since       1.0
 */
public class BookingDAO{

	private DB db;
	public BookingDAO(DB db){
		this.setDb(db);
	}

	public Booking getBookingById(int id) {
		return null;
	}
	
    
	public void updateBooking(Booking book) {	
	}
	
	public void addBooking(Booking book) {
		
	}
	
	public void deleteBooking(Booking book) {
		
	}

	public DB getDb() {
		return db;
	}

	public void setDb(DB db) {
		this.db = db;
	}
}