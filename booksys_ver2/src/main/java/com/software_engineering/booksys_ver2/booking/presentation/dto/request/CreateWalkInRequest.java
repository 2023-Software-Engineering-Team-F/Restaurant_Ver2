package com.software_engineering.booksys_ver2.booking.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateWalkInRequest {

  private String name;

  private String phoneNumber;

  private int tableNumber;

  private int covers;

}
