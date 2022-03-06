package LR4;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * Класс доступа к данным о комнатах.
 * 
 * @author      Николай Киселев <korn9509@gmail.com>
 * @version     1.0
 * @since       1.0
 */
public class RoomsDAO{
	private DB db;
	public RoomsDAO(DB db){
		this.setDb(db);
	}
	

	public Room getRoomById(int id) {
		Room room=null;
		try {
			ResultSet rs=db.query("SELECT * FROM room WHERE id="+id);
			if (rs.next()) {				
				room=new Room();
				room.setId(rs.getInt("id"));
				room.setBeds(rs.getInt("beds"));
				room.setWifi(rs.getBoolean("wifi"));
				room.setFreezer(rs.getBoolean("freezer"));
				room.setTv(rs.getBoolean("tv"));
				room.setTeapot(rs.getBoolean("teapot"));
				room.setSmoking(rs.getBoolean("smoking"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room;
	}
	
	public List<Room> getAllRooms(){
		List<Room> rooms=new Vector<Room>();
		try {
			ResultSet rs=db.query("SELECT * FROM room");
			while(rs.next()) {
				Room room=new Room();
				room.setId(rs.getInt("id"));
				room.setBeds(rs.getInt("beds"));
				room.setWifi(rs.getBoolean("wifi"));
				room.setFreezer(rs.getBoolean("freezer"));
				room.setTv(rs.getBoolean("tv"));
				room.setTeapot(rs.getBoolean("teapot"));
				room.setSmoking(rs.getBoolean("smoking"));
				rooms.add(room);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rooms;		
	}
    
	public void updateRoom(Room room) {	
	}
	
	public void addRoom(Room room) {
		
	}
	
	public void deleteRoom(Room room) {
		
	}


	public DB getDb() {
		return db;
	}


	public void setDb(DB db) {
		this.db = db;
	}
}
