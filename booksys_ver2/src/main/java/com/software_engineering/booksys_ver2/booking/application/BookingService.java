package com.software_engineering.booksys_ver2.booking.application;

import com.software_engineering.booksys_ver2.booking.domain.Booking;
import com.software_engineering.booksys_ver2.booking.domain.Reservation;
import com.software_engineering.booksys_ver2.booking.domain.WalkIn;
import com.software_engineering.booksys_ver2.booking.domain.repository.BookingRepository;
import com.software_engineering.booksys_ver2.customer.domain.Customer;
import com.software_engineering.booksys_ver2.customer.domain.repository.CustomerRepository;
import com.software_engineering.booksys_ver2.restaurant.domain.Restaurant;
import com.software_engineering.booksys_ver2.restaurant.domain.repository.RestaurantRepository;
import com.software_engineering.booksys_ver2.table.domain.Table;
import com.software_engineering.booksys_ver2.table.domain.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingService {

  private final BookingRepository bookingRepository;
  private final CustomerRepository customerRepository;
  private final RestaurantRepository restaurantRepository;
  private final TableRepository tableRepository;

  @Transactional
  public Long createReservation(Long customerId, Long restaurantId, Long tableId, LocalDateTime bookingDateTime) {

    // 고객, 레스토랑, 테이블 조회
    Customer customer = customerRepository.findById(customerId);
    Restaurant restaurant = restaurantRepository.findById(restaurantId);
    Table table = tableRepository.findById(tableId);

    // 온라인 예약 생성 후 DB 저장
    Booking reservation = Reservation.createReservation(customer, restaurant, table, bookingDateTime);
    bookingRepository.save(reservation);

    return reservation.getId();
  }

  @Transactional
  public Long createWalkIn(Long customerId, Long restaurantId, Long tableId) {

    // 고객, 레스토랑, 테이블 조회
    Customer customer = customerRepository.findById(customerId);
    Restaurant restaurant = restaurantRepository.findById(restaurantId);
    Table table = tableRepository.findById(tableId);

    // 현장 예약 생성 후 DB 저장
    Booking walkIn = WalkIn.createWalkIn(customer, restaurant, table);
    bookingRepository.save(walkIn);

    return walkIn.getId();
  }

  public Booking findById(Long bookingId) {

    return bookingRepository.findById(bookingId);
  }

  public List<Booking> findByCustomerName(String customerName) {

    return bookingRepository.findByCustomerName(customerName);
  }

  public List<Booking> findAll() {

    return bookingRepository.findAll();
  }

  @Transactional
  public void updateBooking(Long bookingId, int tableNumber, int customerCount, LocalDateTime bookingDateTime) {

    // 에약, 테이블 조회
    Booking findBooking = bookingRepository.findById(bookingId);
    Table findTable = tableRepository.findByTableNumber(tableNumber);

    // 예약 시간 및 테이블 정보 변경
    findTable.setCustomerCount(customerCount);
    findBooking.setBookingDateTime(bookingDateTime);
    findBooking.setTable(findTable);
  }

  @Transactional
  public void deleteBooking(Long bookingId) {

    bookingRepository.deleteById(bookingId);
  }

  /*public void cancelBooking(Long bookingId) {

    // 예약 엔티티 조회
    Booking booking = bookingRepository.findById(bookingId);

    // 예약 취소
    booking.cancel();
  }*/
}
