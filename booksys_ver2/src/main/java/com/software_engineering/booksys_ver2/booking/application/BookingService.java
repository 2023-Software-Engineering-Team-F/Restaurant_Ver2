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
  public Long createReservation(Long customerId, Long restaurantId, Long tableId, LocalDateTime bookingDateTime, int covers) {

    // 고객, 레스토랑, 테이블, 기존 예약 조회
    Customer customer = customerRepository.findById(customerId);
    Restaurant restaurant = restaurantRepository.findById(restaurantId);
    Table table = tableRepository.findById(tableId);
    List<Booking> existingBookings = bookingRepository.findAll();

    // 테이블 좌석 수보다 식사 인원이 더 많은 경우 예외 반환
    if (covers > table.getPlaces()) {
      throw new IllegalStateException("해당 테이블의 좌석 수가 식사 인원보다 더 많습니다.");
    }

    // 예약하려는 시간이 기존 예약 시간과 겹치는 경우 예외 반환
    for (Booking existingBooking : existingBookings) {
      if (isDoubleBooking(bookingDateTime, bookingDateTime.plusHours(2), existingBooking.getBookingDateTime(), existingBooking.getEndDateTime())) {
        throw new IllegalArgumentException("해당 시간에 이미 예약이 존재합니다.");
      }
    }

    // 온라인 예약 생성 후 DB 저장
    Booking reservation = Reservation.createReservation(customer, restaurant, table, bookingDateTime, covers);
    bookingRepository.save(reservation);

    return reservation.getId();
  }

  @Transactional
  public Long createWalkIn(Long customerId, Long restaurantId, Long tableId, int covers) {

    // 고객, 레스토랑, 테이블, 기존 예약 조회
    Customer customer = customerRepository.findById(customerId);
    Restaurant restaurant = restaurantRepository.findById(restaurantId);
    Table table = tableRepository.findById(tableId);
    List<Booking> existingBookings = bookingRepository.findAll();

    // 테이블 좌석 수보다 식사 인원이 더 많은 경우 예외 반환
    if (covers > table.getPlaces()) {
      throw new IllegalStateException("해당 테이블의 좌석 수가 식사 인원보다 더 많습니다.");
    }

    // 예약하려는 시간이 기존 예약 시간과 겹치는 경우 예외 반환
    for (Booking existingBooking : existingBookings) {
      if (isDoubleBooking(LocalDateTime.now(), LocalDateTime.now().plusHours(2), existingBooking.getBookingDateTime(), existingBooking.getEndDateTime())) {
        throw new IllegalArgumentException("해당 시간에 이미 예약이 존재합니다.");
      }
    }

    // 현장 예약 생성 후 DB 저장
    Booking walkIn = WalkIn.createWalkIn(customer, restaurant, table, covers);
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
  public void updateBooking(Long bookingId, int tableNumber, int covers, LocalDateTime bookingDateTime) {

    // 에약, 테이블 조회
    Booking findBooking = bookingRepository.findById(bookingId);
    Table findTable = tableRepository.findByTableNumber(tableNumber);

    // 테이블 좌석 수보다 식사 인원이 더 많은 경우 예외 반환
    if (covers > findTable.getPlaces()) {
      throw new IllegalStateException("해당 테이블의 좌석 수가 식사 인원보다 더 많습니다.");
    }

    // 예약 시간 및 테이블 정보 변경
    findBooking.setCovers(covers);
    findBooking.setBookingDateTime(bookingDateTime);
    findBooking.setTable(findTable);
  }

  @Transactional
  public void deleteBooking(Long bookingId) {

    bookingRepository.deleteById(bookingId);
  }

  public boolean isDoubleBooking(LocalDateTime bookingStart, LocalDateTime bookingEnd, LocalDateTime existingBookingStart, LocalDateTime existingBookingEnd) {

    return bookingStart.isBefore(existingBookingEnd) && existingBookingStart.isBefore(bookingEnd);
  }
}
