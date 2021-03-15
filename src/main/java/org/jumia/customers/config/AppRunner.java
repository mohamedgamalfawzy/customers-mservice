package org.jumia.customers.config;

import lombok.extern.slf4j.Slf4j;
import org.jumia.customers.dialect.seeders.Seed;
import org.jumia.customers.dialect.seeders.EnableSeeder;
import org.jumia.customers.dialect.seeders.SeederLevel;
import org.jumia.customers.repositories.ICountryRepository;
import org.jumia.customers.repositories.ICustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component

@EnableSeeder(
     level = SeederLevel.AFTER,
     seeders = {
                  @Seed(seederClass = CountrySeeder.class,classRepository = ICountryRepository.class),
                  @Seed(seederClass = CustomerSeeder.class,classRepository = ICustomerRepository.class)
               }
)
public class AppRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
    }
}
