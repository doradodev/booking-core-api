package com.doradodev.digital.factory.core.api.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.doradodev.digital.factory.core.api.service.domain.room.RoomQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.doradodev.digital.factory.core.entity.model.room.Room;
import com.doradodev.digital.factory.core.service.model.RoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author cristiandorado
 *
 */
@RestController
@CrossOrigin
@Api(value = "api-booking", description = "booking Mvc Endpoint", produces = APPLICATION_JSON_VALUE)
@RequestMapping(value = "/api/booking", produces = APPLICATION_JSON_VALUE)
public class RoomController {

	RoomService service;

	@Autowired
	public RoomController(RoomService service) {

		this.service = service;
	}

	@ApiOperation(value = "Get All booking", notes = "Find all booking according to a query search parameters", response = Room.class, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "All booking according to a query search parameters"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized Request"),
			@ApiResponse(code = 404, message = "Room Not Found"),
			@ApiResponse(code = 500, message = "Unexpected Internal Server Error") })

	@RequestMapping(value = "/findAll", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Room>> booking() {

		return new ResponseEntity<>(service.findAll(), OK);
	}	

	@ApiOperation(value = "Get Room", notes = "Find an Room from given Id", response = Room.class, produces = APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "An Room from given Id"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized Request"),
			@ApiResponse(code = 404, message = "Room Not Found"),
			@ApiResponse(code = 500, message = "Unexpected Internal Server Error") })

	@RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> room(
			@ApiParam(value = "The room Id", required = true) @PathVariable final String id) {
		
		Optional<Room> room = service.findByid(Long.parseLong(id));

		if (room.isPresent()) {

			return new ResponseEntity<>(room.get(), OK);
		}

		throw new EntityNotFoundException("Room with Id [" + id + "] was not found!");
	}

	@ApiOperation(value = "Create Room", notes = "Create an Room from given Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "An Room from given Id"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized Request"),
			@ApiResponse(code = 404, message = "Room Not Found"),
			@ApiResponse(code = 500, message = "Unexpected Internal Server Error") })

	@RequestMapping(value = "/{id}", method = POST, produces = APPLICATION_JSON_VALUE)
	public void insertRoom(
			@ApiParam(value = "The Room Id", required = true) @RequestBody(required = true) RoomQueryDTO query) {
		Room room = Room.builder().name(query.getName()).capacity(query.getCapacity()).build();
		service.save(room);
	}

	@ApiOperation(value = "Update Room", notes = "Update an Room from given Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "An Room from given Id"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized Request"),
			@ApiResponse(code = 404, message = "Room Not Found"),
			@ApiResponse(code = 500, message = "Unexpected Internal Server Error") })

	@RequestMapping(value = "/{id}", method = PUT, produces = APPLICATION_JSON_VALUE)
	public void updateRoom(@ApiParam(value = "The Room Id", required = true) @PathVariable final String id,
			@RequestBody(required = true) RoomQueryDTO query) {
		Room room = Room.builder().idRoom(Long.parseLong(id))
                                  .name(query.getName())
                                  .capacity(query.getCapacity())
                                  .bookings(query.getBookings()).build();
		                                
				
		service.update(room);

	}

	@ApiOperation(value = "Delete Room", notes = "Delete an Room from given Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "An Room from given Id"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized Request"),
			@ApiResponse(code = 404, message = "Room Not Found"),
			@ApiResponse(code = 500, message = "Unexpected Internal Server Error") })

	@RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
	public void deleteRoom(@ApiParam(value = "The Room Id", required = true) @PathVariable final String id) {

		service.delete(Long.parseLong(id));
	}

}
