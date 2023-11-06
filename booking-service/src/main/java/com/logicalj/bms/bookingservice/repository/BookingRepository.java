package com.logicalj.bms.bookingservice.repository;

import com.logicalj.bms.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    /*Optional<Show> findByNameAndCityId(String name, Integer cityId);*/

}
