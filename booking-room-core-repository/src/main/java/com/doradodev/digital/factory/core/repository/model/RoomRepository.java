/**
 * 
 */
package com.doradodev.digital.factory.core.repository.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.doradodev.digital.factory.core.entity.model.room.Room;
import com.querydsl.core.types.Predicate;

/**
 * @author cristiandorado
 *
 */
public interface RoomRepository extends JpaRepository<Room, Long>, QueryDslPredicateExecutor<Room>{
	
	Iterable<Room> findAll(Predicate predicate);

}
