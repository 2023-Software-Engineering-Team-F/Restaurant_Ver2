package com.software_engineering.booksys_ver2.customer.presentation;

import com.software_engineering.booksys_ver2.customer.application.CustomerService;
import com.software_engineering.booksys_ver2.customer.domain.Customer;
import com.software_engineering.booksys_ver2.customer.presentation.dto.response.CustomerResponse;
import com.software_engineering.booksys_ver2.customer.presentation.dto.response.CustomerListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Api(tags = "고객 관련 API")
public class CustomerController {

  private final CustomerService customerService;

  /**
   * 전체 고객 조회
   */
  @GetMapping("/customer/list")
  @ApiOperation(value = "전체 고객 조회 API", notes = "")
  public CustomerListResponse<List<CustomerResponse>> listCustomer() {

    List<Customer> customers = customerService.findAll();
    List<CustomerResponse> customersResponse = customers.stream().map(c -> new CustomerResponse(c.getId(), c.getName(), c.getPhoneNumber()))
        .collect(Collectors.toList());

    return new CustomerListResponse<>(customersResponse.size(), customersResponse);
  }


}
