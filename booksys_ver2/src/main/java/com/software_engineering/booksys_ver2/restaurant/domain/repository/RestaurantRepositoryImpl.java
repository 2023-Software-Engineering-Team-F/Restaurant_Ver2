package com.software_engineering.booksys_ver2.restaurant.domain.repository;

import com.software_engineering.booksys_ver2.restaurant.domain.Restaurant;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

  private final EntityManager em;

  @Override
  public void save(Restaurant restaurant) {

    em.persist(restaurant);
  }

  @Override
  public Restaurant findById(Long id) {

    return em.find(Restaurant.class, id);
  }
}
