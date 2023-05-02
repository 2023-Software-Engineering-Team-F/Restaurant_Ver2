package com.software_engineering.booksys_ver2.table.application;

import com.software_engineering.booksys_ver2.table.domain.Table;
import com.software_engineering.booksys_ver2.table.domain.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TableService {

  private final TableRepository tableRepository;

  @Transactional
  public Long createTable(int tableNumber, int customerCount) {

    Table table = Table.createTable(tableNumber, customerCount);
    tableRepository.save(table);

    return table.getId();
  }

  public Table findById(Long id) {

    return tableRepository.findById(id);
  }

  public Table findByTableNumber(int tableNumber) {

    return tableRepository.findByTableNumber(tableNumber);
  }

}
