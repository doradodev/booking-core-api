package com.doradodev.digital.factory.core.entity.model.booking;

import com.doradodev.digital.factory.core.entity.model.room.Room;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.omg.CORBA.ServerRequest;


import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "BOOKING")
public class Booking {

    @Id
    @Column(name = "ID_BOOKING")
    @GeneratedValue
    private Long idBooking;    

    @Column(name = "START_DATE")
    private Date start;

    @Column(name = "END_DATE")
    private Date end;

    @Column(name = "STATE")
    private String state;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ROOM")
    private Room room;
    
    @Builder
    public static Booking target(Long idBooking, Date start, Date end, Room room, String state){
        
        Booking booking = new Booking();
        booking.setIdBooking(idBooking);        
        booking.setStart(start);
        booking.setEnd(end);
        booking.setRoom(room);
        booking.setState(state);
        
        return booking;
    }
}
