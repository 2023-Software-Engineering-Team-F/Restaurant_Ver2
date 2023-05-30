package com.software_engineering.booksys_ver2.booking.domain.repository;

import com.software_engineering.booksys_ver2.booking.domain.Booking;

import java.util.List;

public interface BookingRepository {

  void save(Booking booking);

  Booking findById(Long id);

  List<Booking> findByCustomerName(String customerName);

  List<Booking> findAll();

  void deleteById(Long id);
}
