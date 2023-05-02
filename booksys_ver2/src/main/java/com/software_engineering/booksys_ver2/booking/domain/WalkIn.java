package com.software_engineering.booksys_ver2.booking.domain;

import com.software_engineering.booksys_ver2.booking.domain.enums.BookingStatus;
import com.software_engineering.booksys_ver2.customer.domain.Customer;
import com.software_engineering.booksys_ver2.restaurant.domain.Restaurant;
import com.software_engineering.booksys_ver2.table.domain.Table;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
@DiscriminatorValue("WALKIN")
public class WalkIn extends Booking {

  public String getDetails() {

    return "Walk-in (" + getTable().getCustomerCount() + "명)" ;
  }

  //==생성 메소드==//
  public static WalkIn createWalkIn(Customer customer, Restaurant restaurant, Table table) {

    WalkIn walkIn = new WalkIn();
    walkIn.setBookingStatus(BookingStatus.BOOK);
    walkIn.setBookingDateTime(LocalDateTime.now());
    walkIn.setArrivalDateTime(LocalDateTime.now());
    walkIn.setCustomer(customer);
    walkIn.setRestaurant(restaurant);
    walkIn.setTable(table);

    return walkIn;
  }
}
