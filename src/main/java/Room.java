import java.util.List;
import java.util.Vector;

public class Room {
	private int id;
	private int beds;
	private boolean wifi;
	private boolean freezer;
	private boolean tv;
	private boolean teapot;
	private boolean smoking;
	private List<Booking> bookings=new Vector<Booking>();
	
	public void addBooking(Booking book) {
		bookings.add(book);
	}
	
	public void deleteBooking(Booking book) {
		bookings.remove(book);
	}
	
	public List<Booking> getBookingsForUser(User user) {
		
		Vector<Booking> resultList=new Vector<Booking>();
		for (Booking book:bookings) {
			if (book.getUser().getLogin()==user.getLogin()) resultList.add(book);
		}
		return resultList;
	}
	
	public int getBeds() {
		return beds;
	}
	public void setBeds(int beds) {
		this.beds = beds;
	}
	public boolean isWifi() {
		return wifi;
	}
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	public boolean isFreezer() {
		return freezer;
	}
	public void setFreezer(boolean freezer) {
		this.freezer = freezer;
	}
	public boolean isTv() {
		return tv;
	}
	public void setTv(boolean tv) {
		this.tv = tv;
	}
	public boolean isTeapot() {
		return teapot;
	}
	public void setTeapot(boolean teapot) {
		this.teapot = teapot;
	}
	public boolean isSmoking() {
		return smoking;
	}
	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}


