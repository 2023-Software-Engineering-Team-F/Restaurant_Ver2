package com.software_engineering.booksys_ver2.customer.application;

import com.software_engineering.booksys_ver2.customer.domain.Customer;
import com.software_engineering.booksys_ver2.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

  private final CustomerRepository customerRepository;

  // 고객 정보 생성
  @Transactional
  public Long createCustomer(String name, String phoneNumber) {

    Customer customer = Customer.createCustomer(name, phoneNumber);
    customerRepository.save(customer);

    return customer.getId();
  }

  // 고객 번호를 이용한 회원 조회
  public Customer findById(Long customerId) {
    return customerRepository.findById(customerId);
  }

  // 이름을 이용한 회원 조회
  public Customer findByName(String name) {
    return customerRepository.findByName(name);
  }

  // 전화번호를 이용한 회원 조회
  public Customer findByPhoneNumber(String phoneNumber) {
    return customerRepository.findByPhoneNumber(phoneNumber);
  }

  // 전체 고객 조회
  public List<Customer> findAll() {

    return customerRepository.findAll();
  }

}
