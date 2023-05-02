package com.software_engineering.booksys_ver2.customer.domain.repository;

import com.software_engineering.booksys_ver2.customer.domain.Customer;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

  private final EntityManager em;

  @Override
  public void save(Customer member) {

    em.persist(member);
  }

  @Override
  public Customer findById(Long id) {

    return em.find(Customer.class, id);
  }

  @Override
  public Customer findByName(String name) {

    return em.createQuery("select c from Customer c where c.name = :name", Customer.class)
        .setParameter("name", name)
        .getSingleResult();
  }

  @Override
  public Customer findByPhoneNumber(String phoneNumber) {

    return em.createQuery("select c from Customer c where c.phoneNumber = :phoneNumber", Customer.class)
        .setParameter("phoneNumber", phoneNumber)
        .getSingleResult();
  }


  @Override
  public List<Customer> findAll() {

    return em.createQuery("select c from Customer c", Customer.class)
        .getResultList();
  }

  @Override
  public void deleteById(Long id) {

    Customer customer = em.find(Customer.class, id);
    em.remove(customer);
  }
}
