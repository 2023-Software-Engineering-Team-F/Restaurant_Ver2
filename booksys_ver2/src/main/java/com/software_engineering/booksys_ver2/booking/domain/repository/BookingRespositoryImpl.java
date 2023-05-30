package com.software_engineering.booksys_ver2.booking.domain.repository;

import com.software_engineering.booksys_ver2.booking.domain.Booking;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingRespositoryImpl implements BookingRepository {

  private final EntityManager em;

  @Override
  public void save(Booking booking) {

    em.persist(booking);
  }

  @Override
  public Booking findById(Long id) {

    return em.find(Booking.class, id);
  }

  @Override
  public List<Booking> findByCustomerName(String customerName) {

    return em.createQuery("select distinct b from Booking b" +
            " join fetch b.customer c where c.name = :customerName", Booking.class)
        .setParameter("customerName", customerName)
        .getResultList();
  }

  @Override
  public List<Booking> findAll() {

    return em.createQuery("SELECT b FROM Booking b", Booking.class)
        .getResultList();
  }

  @Override
  public void deleteById(Long id) {

    Booking findBooking = em.find(Booking.class, id);
    em.remove(findBooking);
  }
}
