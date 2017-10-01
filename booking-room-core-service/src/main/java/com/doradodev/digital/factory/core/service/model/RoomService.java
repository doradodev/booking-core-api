/**
 * 
 */
package com.doradodev.digital.factory.core.service.model;

import java.util.List;
import java.util.Optional;

import com.doradodev.digital.factory.core.entity.model.room.Room;

/**
 * @author cristiandorado
 *
 */
public interface RoomService {
	
	List<Room> findAll();
	
	Optional<Room> findByid(Long id);
	
	void save(Room room);
	 
	void update(Room room);
 
    void delete(Long id);
    
    List<Room> findByQuery(Room room);

}
