package org.jumia.customers.services;

import org.jumia.customers.dto.CountryDTO;
import org.jumia.customers.entity.Country;
import org.jumia.customers.repositories.ICountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CountryServiceTest{
    @Autowired
    ICountryRepository  countryRepository;
    @Autowired
    ICountryService  countryService;
    @Test
    void shouldGetCountryByUserCode(){
            Optional<Country> customerPage=this.countryRepository.findFirstByCode("256");
            assertNotNull(customerPage);
            assertTrue(customerPage.isPresent());
            assertEquals(customerPage.get().getCode(),"256");
    }
    @Test
    void shouldGetAllCountries(){
        List<CountryDTO> countriesList=this.countryService.getAll();
        assertNotNull(countriesList);
        assertNotEquals(countriesList.size(),0);
    }
}
