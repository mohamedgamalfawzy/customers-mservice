package org.jumia.customers.config;


import org.jumia.customers.dialect.seeders.Seeder;
import org.jumia.customers.entity.Country;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountrySeeder implements Seeder<Country> {
    @Override
    public void seed(List<Country> list) {
        list.add(new Country(1L,"Cameron","237","\\(237\\)\\ ?[2368]\\d{7,8}$"));
        list.add(new Country(2L,"Ethiopia","251","\\(251\\)\\ ?[1-59]\\d{8}$"));
        list.add(new Country(3L,"Morocco","212","\\(212\\)\\ ?[5-9]\\d{8}$"));
        list.add(new Country(4L,"Mozambique","258","\\(258\\)\\ ?[28]\\d{7,8}$"));
        list.add(new Country(5L,"Uganda","256","\\(256\\)\\ ?\\d{9}$"));
    }
}
