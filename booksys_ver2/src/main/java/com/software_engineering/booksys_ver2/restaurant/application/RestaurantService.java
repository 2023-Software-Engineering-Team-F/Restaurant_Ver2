package com.software_engineering.booksys_ver2.restaurant.application;

import com.software_engineering.booksys_ver2.restaurant.domain.Restaurant;
import com.software_engineering.booksys_ver2.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

  private final RestaurantRepository restaurantRepository;

  @Transactional
  public Long createRestaurant(int tableQuantity) {

    Restaurant restaurant = Restaurant.createRestaurant(tableQuantity);
    restaurantRepository.save(restaurant);

    return restaurant.getId();
  }
}
