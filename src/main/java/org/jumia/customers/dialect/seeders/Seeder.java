package org.jumia.customers.dialect.seeders;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Seeder<T> {
    void seed(List<T> list);
}

