package com.software_engineering.booksys_ver2.table.domain;

import com.software_engineering.booksys_ver2.booking.domain.Booking;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static javax.persistence.FetchType.LAZY;

@Entity
@javax.persistence.Table(name = "tables")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Table {

  @Id
  @GeneratedValue
  private Long id;

  private int tableNumber; // 테이블 번호

  private int customerCount; // 식사 인원

  @OneToOne(fetch = LAZY)
  private Booking booking; // 테이블에 할당된 예약

  public static Table createTable(int tableNumber, int customerCount) {

    Table table = new Table();
    table.setTableNumber(tableNumber);
    table.setCustomerCount(customerCount);

    return table;
  }
}
