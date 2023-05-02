package com.software_engineering.booksys_ver2.customer.domain;

import com.software_engineering.booksys_ver2.booking.domain.Booking;
import javax.persistence.*;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Vector;

@Entity
@Table(name = "customers")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

  @Id @GeneratedValue
  @Column(name="customer_id")
  private Long id;

  private String name; // 고객 이름

  private String phoneNumber; // 고객 전화번호 ("-" 을 제외한)

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<Booking> bookings = new Vector<>(); // 고객 예약 목록

  //== 생성 메소드 ==//
  public static Customer createCustomer(String name, String phoneNumber) {

    Customer customer = new Customer();
    customer.setName(name);
    customer.setPhoneNumber(phoneNumber);

    return customer;
  }
}
