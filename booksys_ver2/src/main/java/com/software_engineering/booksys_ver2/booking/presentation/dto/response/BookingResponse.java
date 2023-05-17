package com.software_engineering.booksys_ver2.booking.presentation.dto.response;

import com.software_engineering.booksys_ver2.booking.domain.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BookingResponse {

  private Long id;

  private String name;

  private String phoneNumber;

  private int tableNumber;

  private int covers
      ;

  private BookingStatus bookingStatus;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime bookingDateTime;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime arrivalDateTime;
}
