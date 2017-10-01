package com.doradodev.digital.factory.core.entity.model.room;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.doradodev.digital.factory.core.entity.model.booking.Booking;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cristiandorado
 *
 */
@Getter
@Setter
@Entity
@Table(name = "ROOM")
public class Room {

	@Id
	@Column(name = "ID_ROOM")
	@GeneratedValue
	private Long idRoom;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CAPACITY")
    private long capacity;	
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)    
	private Set<Booking> bookings = new HashSet<Booking>();

	@Builder
	public static Room target(Long idRoom, String name, Long capacity, Set<Booking> bookings) {

		Room room = new Room();
		room.setIdRoom(idRoom);
		room.setName(name);
		room.setCapacity(capacity);
		room.setBookings(bookings);

		return room;
	}

}
