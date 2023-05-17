package com.software_engineering.booksys_ver2.booking.domain;

import com.software_engineering.booksys_ver2.booking.domain.enums.BookingStatus;
import com.software_engineering.booksys_ver2.customer.domain.Customer;
import com.software_engineering.booksys_ver2.restaurant.domain.Restaurant;
import com.software_engineering.booksys_ver2.table.domain.Table;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@javax.persistence.Table(name = "bookings")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
public abstract class Booking {

  @Id @GeneratedValue
  @Column(name="booking_id")
  private Long id;

  private LocalDateTime bookingDateTime; // 예약 날짜 및 시간

  private LocalDateTime arrivalDateTime; // 고객 도착 날짜 및 시간

  private int covers; // 예약 인원

  private String password; // 예약 비밀번호

  @Enumerated(EnumType.STRING)
  private BookingStatus bookingStatus;

  @OneToOne(fetch = LAZY)
  @JoinColumn(name = "table_id")
  private Table table; // 예약한 테이블

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer; // 예약한 고객

  @ManyToOne(fetch = LAZY)
  private Restaurant restaurant; // 예약한 레스토랑

  //== 조회 메소드 ==//

  /**
   * 예약 종료시간 조회
   */
  public LocalDateTime getEndDateTime() {
    LocalDateTime currentDateTime = bookingDateTime;
    return currentDateTime.plusHours(2);
  }

  /**
   * 예약 상세정보 조회
   */
  public abstract String getDetails();

  //== 연관관계 편의 메소드 ==//

  public void setCustomer(Customer customer) {
    this.customer = customer;
    customer.getBookings().add(this);
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
    restaurant.getBookings().add(this);
  }

  //== 비즈니스 로직 ==//

  /**
   * 예약 취소
   */
  public void cancel() {
    LocalDateTime currentDateTime = LocalDateTime.now();
    LocalDateTime reservationDateTime = getBookingDateTime();

    if (currentDateTime.isAfter(reservationDateTime.minusMinutes(30))) { // 예약시간 30분 전까지는 취소 가능, 예약 시간 임박 시에는 취소 불가
      throw new IllegalStateException("예약 시간 임박으로 취소할 수 없습니다.");
    }

    this.setBookingStatus(BookingStatus.CANCEL); // 예약상태 취소로 변경
  }
}
