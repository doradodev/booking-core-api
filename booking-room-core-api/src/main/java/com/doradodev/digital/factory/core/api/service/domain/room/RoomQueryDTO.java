package com.doradodev.digital.factory.core.api.service.domain.room;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.doradodev.digital.factory.core.entity.model.booking.Booking;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * @author cristiandorado
 *
 */
@Data
@ApiModel(value = "RoomQuery", description = "The Room Query Representation")
public class RoomQueryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5133105154344638154L;
    
	private String name;
	private long capacity;
    private Set<Booking> bookings = new HashSet<Booking>();

	@Builder
	public static RoomQueryDTO target(String name, long capacity, Set<Booking> bookings) {

		RoomQueryDTO dto = new RoomQueryDTO();

		dto.setName(name);
		dto.setCapacity(capacity);
		dto.setBookings(bookings);
		

		return dto;

	}

}
