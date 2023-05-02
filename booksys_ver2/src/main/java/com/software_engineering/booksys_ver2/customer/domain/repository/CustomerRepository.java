package com.software_engineering.booksys_ver2.customer.domain.repository;

import com.software_engineering.booksys_ver2.booking.domain.Booking;
import com.software_engineering.booksys_ver2.customer.domain.Customer;

import java.util.List;

public interface CustomerRepository {

  void save(Customer customer);

  Customer findById(Long id);

  Customer findByName(String name);

  Customer findByPhoneNumber(String phoneNumber);

  List<Customer> findAll();

  void deleteById(Long id);
}
