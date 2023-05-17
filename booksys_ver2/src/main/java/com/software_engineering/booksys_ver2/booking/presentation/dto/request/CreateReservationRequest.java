package com.software_engineering.booksys_ver2.booking.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreateReservationRequest {

  private String name;

  private String phoneNumber;

  private int tableNumber;

  private int covers;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime bookingDateTime;

}
