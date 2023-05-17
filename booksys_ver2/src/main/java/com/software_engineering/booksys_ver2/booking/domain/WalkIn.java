package com.software_engineering.booksys_ver2.booking.domain;

import com.software_engineering.booksys_ver2.booking.domain.enums.BookingStatus;
import com.software_engineering.booksys_ver2.customer.domain.Customer;
import com.software_engineering.booksys_ver2.restaurant.domain.Restaurant;
import com.software_engineering.booksys_ver2.table.domain.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity(name = "walkIns")
@Getter @Setter
@javax.persistence.Table(name = "walkIns")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
@DiscriminatorValue("WALKIN")
public class WalkIn extends Booking {

  public String getDetails() {

    return "Walk-in (" + getTable().getPlaces() + "명)" ;
  }

  //==생성 메소드==//
  public static WalkIn createWalkIn(Customer customer, Restaurant restaurant, Table table, int covers) {

    WalkIn walkIn = new WalkIn();
    walkIn.setBookingStatus(BookingStatus.BOOK);
    walkIn.setBookingDateTime(LocalDateTime.now());
    walkIn.setArrivalDateTime(LocalDateTime.now());
    walkIn.setCustomer(customer);
    walkIn.setRestaurant(restaurant);
    walkIn.setTable(table);
    walkIn.setCovers(covers);

    return walkIn;
  }
}
