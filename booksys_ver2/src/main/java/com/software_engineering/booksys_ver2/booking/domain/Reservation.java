package com.software_engineering.booksys_ver2.booking.domain;

import com.software_engineering.booksys_ver2.booking.domain.enums.BookingStatus;
import com.software_engineering.booksys_ver2.customer.domain.Customer;
import com.software_engineering.booksys_ver2.restaurant.domain.Restaurant;
import com.software_engineering.booksys_ver2.table.domain.Table;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@javax.persistence.Table(name = "reservations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
@DiscriminatorValue("RESERVATION")
public class Reservation extends Booking {
  @Override
  public String getDetails() {
    StringBuilder details = new StringBuilder(64);
    details.append(getCustomer().getName());
    details.append(" ");
    details.append(getCustomer().getPhoneNumber());
    details.append(" (");
    details.append(getTable().getPlaces());
    details.append("명)");
    if (getArrivalDateTime() != null) {
      details.append(" [");
      details.append(getArrivalDateTime());
      details.append("]");
    }

    return details.toString();
  }

  public static Reservation createReservation(Customer customer, Restaurant restaurant, Table table, LocalDateTime bookingDateTime, int covers) {

    Reservation reservation = new Reservation();
    reservation.setBookingStatus(BookingStatus.BOOK);
    reservation.setBookingDateTime(bookingDateTime);
//  reservation.setArrivalDateTime(); -> 도착 시간은 예약 생성 시점이 아닌 손님 도착 시점에 설정
    reservation.setCustomer(customer);
    reservation.setRestaurant(restaurant);
    reservation.setTable(table);
    reservation.setCovers(covers);

    return reservation;
  }


}
