package com.software_engineering.booksys_ver2.booking.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingListResponse<T> {

  private int count;
  private T data;
}
