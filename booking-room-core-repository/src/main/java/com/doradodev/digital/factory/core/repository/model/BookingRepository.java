package com.doradodev.digital.factory.core.repository.model;

import com.doradodev.digital.factory.core.entity.model.booking.Booking;
import com.doradodev.digital.factory.core.entity.model.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>, QueryDslPredicateExecutor<Room>{

    @Override
    List<Booking> findAll();
}
