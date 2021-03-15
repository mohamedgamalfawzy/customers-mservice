package org.jumia.customers.dialect.seeders;

import org.jumia.customers.config.CountrySeeder;
import org.jumia.customers.entity.Country;
import org.jumia.customers.repositories.ICountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SeederTest {
    @Autowired
    ICountryRepository countryRepository;
    @Test
    public void shouldSuccessEnableSeederAndSeedingCountry(){
        Optional<Country> country=this.countryRepository.findById(1L);
        assertTrue(country.isPresent());
        assertNotNull(country.get());
        assertEquals(1L,country.get().getId(),"ID field not equal to the inserted ID. [Expected]: 1L --- [Actual] : "+country.get().getId() );
        assertEquals("Cameron",country.get().getName(),"Name field not equal to the inserted Name. [Expected]: Cameron --- [Actual] : "+country.get().getName() );
        assertEquals("237",country.get().getCode(),"Code field not equal to the inserted Code. [Expected]: 237 --- [Actual] : "+country.get().getCode());
        assertEquals("\\(237\\)\\ ?[2368]\\d{7,8}$",country.get().getRegex(),"Regex field not equal to the inserted Regex. [Expected]: \\(237\\)\\ ?[2368]\\d{7,8}$ --- [Actual] : "+country.get().getRegex());

    }
    @Test
    public void shouldCountrySeedListEqualToDbSize(){
        Seeder<Country> countrySeeder=new CountrySeeder();
        List<Country> list=new ArrayList<>();
        countrySeeder.seed(list);
        assertEquals(list.size(),countryRepository.count());
    }

}
