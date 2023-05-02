package com.software_engineering.booksys_ver2.customer.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerResponse {

  private Long id;

  private String name;

  private String phoneNumber;
}
