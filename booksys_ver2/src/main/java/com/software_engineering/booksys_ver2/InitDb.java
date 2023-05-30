package com.software_engineering.booksys_ver2;

import com.software_engineering.booksys_ver2.booking.domain.Reservation;
import com.software_engineering.booksys_ver2.customer.domain.Customer;
import com.software_engineering.booksys_ver2.restaurant.domain.Restaurant;
import com.software_engineering.booksys_ver2.table.domain.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

  private final InitService initService;

  /**
   * 애플리케이션 실행 전에 미리 init() 실행
   */
  @PostConstruct
  public void init() {
    initService.dbInit();
  }

  @Component
  @Transactional
  @RequiredArgsConstructor
  static class InitService {

    private final EntityManager em;

    public void dbInit() {

      Restaurant restaurant1 = createRestaurant(10);
      em.persist(restaurant1);

      Table table1 = createTable(1, 4);
      Table table2 = createTable(2, 4);
      Table table3 = createTable(3, 10);
      Table table4 = createTable(4, 4);
      Table table5 = createTable(5, 4);
      Table table6 = createTable(6, 4);
      Table table7 = createTable(7, 4);
      Table table8 = createTable(8, 4);
      Table table9 = createTable(9, 4);
      Table table10 = createTable(10, 4);
      em.persist(table1);
      em.persist(table2);
      em.persist(table3);
      em.persist(table4);
      em.persist(table5);
      em.persist(table6);
      em.persist(table7);
      em.persist(table8);
      em.persist(table9);
      em.persist(table10);

      Customer customer1 = createCustomer("customer1", "01011111111");
      em.persist(customer1);

      Reservation reservation1 = createReservation(customer1, restaurant1, table1, LocalDateTime.now(), 4);
      em.persist(reservation1);

      Customer customer2 = createCustomer("customer2", "01022222222");
      em.persist(customer2);

      Reservation reservation2 = createReservation(customer2, restaurant1, table2, LocalDateTime.now(), 4);
      em.persist(reservation2);

      Customer customer3 = createCustomer("customer2", "01033333333");
      em.persist(customer3);

      Reservation reservation3= createReservation(customer3, restaurant1, table3, LocalDateTime.now(), 10);
      em.persist(reservation3);

    }

    private Customer createCustomer(String name, String phoneNumber) {

      return Customer.createCustomer(name, phoneNumber);
    }

    private Restaurant createRestaurant(int tableQuantity) {

      return Restaurant.createRestaurant(tableQuantity);
    }

    private Table createTable(int tableNumber, int places) {

      return Table.createTable(tableNumber, places);
    }

    private Reservation createReservation(Customer customer, Restaurant restaurant, Table table, LocalDateTime bookingDateTime, int covers) {

      return Reservation.createReservation(customer, restaurant, table, bookingDateTime, covers);
    }

  }
}
