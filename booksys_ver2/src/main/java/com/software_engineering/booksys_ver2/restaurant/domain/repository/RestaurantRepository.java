package com.software_engineering.booksys_ver2.restaurant.domain.repository;

import com.software_engineering.booksys_ver2.restaurant.domain.Restaurant;

public interface RestaurantRepository {

  public void save(Restaurant restaurant);

  public Restaurant findById(Long id);
}
