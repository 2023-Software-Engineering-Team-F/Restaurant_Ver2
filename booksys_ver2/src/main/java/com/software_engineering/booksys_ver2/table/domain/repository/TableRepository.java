package com.software_engineering.booksys_ver2.table.domain.repository;

import com.software_engineering.booksys_ver2.table.domain.Table;

public interface TableRepository {

  public void save(Table table);

  public Table findById(Long id);

  public Table findByTableNumber(int tableNumber);
}
