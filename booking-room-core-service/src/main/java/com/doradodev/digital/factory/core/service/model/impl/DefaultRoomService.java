/**
 * 
 */
package com.doradodev.digital.factory.core.service.model.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.doradodev.digital.factory.core.entity.model.booking.Booking;
import com.doradodev.digital.factory.core.repository.model.BookingRepository;
import com.doradodev.digital.factory.core.service.model.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.doradodev.digital.factory.core.commons.exception.CommonRuntimeException;
import com.doradodev.digital.factory.core.entity.model.room.Room;
import com.doradodev.digital.factory.core.entity.model.room.QRoom;
import com.doradodev.digital.factory.core.repository.model.RoomRepository;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

/**
 * @author cristiandorado
 *
 */
@Service
public class DefaultRoomService implements RoomService {

	private final RoomRepository repository;
	private final BookingRepository repositoryBooking;

	@Autowired
	public DefaultRoomService(RoomRepository repository, BookingRepository repositoryBooking) {
		this.repository = repository;
		this.repositoryBooking = repositoryBooking;
	}

	@Override
	public List<Room> findAll() {

		return repository.findAll();
	}

	@Override
	public Optional<Room> findByid(Long id) {
		checkNotNull(id, "The Room Id is a required argument!");

		try {

			return Optional.ofNullable(repository.findOne(id));
		} catch (Exception ex) {

			throw new CommonRuntimeException("Error finding Room by Id [" + id + "]!", ex);
		}

	}

	@Override
	public void save(Room room) {
        
	    
		repository.save(room);
	}

	@Override
	public void update(Room room) {
	    
        room.getBookings().stream().forEach(booking -> booking.setRoom(room));       
		repository.save(room);
	}

	@Override
	public void delete(Long id) {

		repository.delete(id);

	}

	@Override
	public List<Room> findByQuery(Room query) {
		checkNotNull(query, "The Room Query is a required argument!");
		try {

			QRoom room = QRoom.room;
			Predicate dateRange = null;
			Predicate endDate = null;
			Predicate startDate = null;

			/*if (query.getStart() != null && query.getEnd() == null) {

				startDate = addStartDate(room, query);
			}

			if (query.getStart() == null && query.getEnd() != null) {

				endDate = addEndDate(room, query);
			}

			if (query.getStart() != null && query.getEnd() != null) {

				dateRange = addDateRange(room, query);

			}*/

			Predicate place = addPlace(room, query);
			Predicate name = addName(room, query);

			Predicate finalQuery = ExpressionUtils.allOf(name, startDate, endDate, place, dateRange);

			return Lists.newArrayList(repository.findAll(finalQuery));

		} catch (Exception ex) {

			throw new CommonRuntimeException("Error getting room by Query!", ex);

		}
	}

	private Predicate addDateRange(QRoom room, Room query) {

		Predicate startDate = null;
		Predicate endDate = null;

		/*if (!query.getEnd().before(query.getStart())) {

			startDate = room.start.goe(query.getStart());
			endDate = room.end.loe(query.getEnd());
			return ExpressionUtils.allOf(startDate, endDate);
		}*/
		return null;
	}

	private Predicate addPlace(QRoom room, Room query) {

		if (!StringUtils.isEmpty(query.getCapacity())) {
			return room.capacity.eq(query.getCapacity());
		}
		return null;
	}

	private Predicate addEndDate(QRoom room, Room query) {

		/*if (query.getEnd() != null) {
			return room.end.eq(query.getEnd());
		}*/

		return null;
	}

	private Predicate addStartDate(QRoom room, Room query) {

		/*if (query.getStart() != null) {
			
			return room.start.eq(query.getStart());
		}*/
		return null;
	}

	private Predicate addName(QRoom room, Room query) {

		if (!StringUtils.isEmpty(query.getName())) {

			return room.name.eq(query.getName());
		}

		return null;
	}

}
