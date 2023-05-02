package com.software_engineering.booksys_ver2.restaurant.domain;

import com.software_engineering.booksys_ver2.booking.domain.Booking;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Vector;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

  @Id
  @GeneratedValue

  private Long id;

  private int tableQuantity; // 해당 레스토랑의 테이블 갯수

  @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
  private List<Booking> bookings = new Vector<>(); // 해당 레스토랑의 예약 목록

  //== 생성 메소드 ==//

  public static Restaurant createRestaurant(int tableQuantity) {

    Restaurant restaurant = new Restaurant();
    restaurant.setTableQuantity(tableQuantity);

    return restaurant;
  }
}
