package LR4;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
	private static final Logger logger = LogManager.getLogger("web");
	
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
		try {
			int wifi=room.isWifi()?1:0;
			int freezer=room.isFreezer()?1:0;
			int tv=room.isTv()?1:0;
			int teapot=room.isTeapot()?1:0;
			int smoking=room.isSmoking()?1:0;
			db.update("UPDATE room SET wifi="+wifi+", freezer="+freezer+", tv="+tv+", teapot="+teapot+", smoking="+smoking+ " WHERE id="+room.getId());
			logger.info("Updated new room with id "+room.getId());
		} catch (SQLException e) {
			logger.error("Error updating room with id "+room.getId());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addRoom(Room room) {
		try {
			int wifi=room.isWifi()?1:0;
			int freezer=room.isFreezer()?1:0;
			int tv=room.isTv()?1:0;
			int teapot=room.isTeapot()?1:0;
			int smoking=room.isSmoking()?1:0;
			db.update("INSERT INTO room VALUES("+room.getId()+","+room.getBeds()+","+wifi+","+freezer+","+tv+","+teapot+","+smoking+")");
			logger.info("Added new room to db");
		} catch (SQLException e) {
			logger.error("Error adding room");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteRoom(Room room) {
		try {
			db.update("DELETE FROM room WHERE id="+room.getId());
			logger.info("Deleted room with id "+room.getId());
		} catch (SQLException e) {
			logger.error("Error deleting room with id "+room.getId());
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
