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

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="table_id")
  private Long id;

  private int tableNumber; // 테이블 번호

  private int places; // 테이블 좌석 수

  @OneToOne(fetch = LAZY)
  @JoinColumn(name = "booking_id")
  private Booking booking; // 테이블에 할당된 예약

  public static Table createTable(int tableNumber, int places) {

    Table table = new Table();
    table.setTableNumber(tableNumber);
    table.setPlaces(places);

    return table;
  }
}
