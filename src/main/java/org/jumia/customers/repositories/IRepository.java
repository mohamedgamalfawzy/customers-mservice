package org.jumia.customers.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRepository<TEntity> {
    Page<TEntity> findAll(Pageable pageable);
    List<TEntity> findByName(String name);
}
