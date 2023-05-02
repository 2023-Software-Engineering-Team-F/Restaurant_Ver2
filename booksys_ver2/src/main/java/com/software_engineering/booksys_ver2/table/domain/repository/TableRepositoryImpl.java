package com.software_engineering.booksys_ver2.table.domain.repository;

import com.software_engineering.booksys_ver2.table.domain.Table;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TableRepositoryImpl implements TableRepository {

  private final EntityManager em;

  @Override
  public void save(Table table) {

    em.persist(table);
  }

  @Override
  public Table findById(Long id) {

    return em.find(Table.class, id);
  }

  @Override
  public Table findByTableNumber(int tableNumber) {

    return em.createQuery("select t from Table t where t.tableNumber = :tableNumber", Table.class)
        .setParameter("tableNumber", tableNumber)
        .getSingleResult();
  }
}
