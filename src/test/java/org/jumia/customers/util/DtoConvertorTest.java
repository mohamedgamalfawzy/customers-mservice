package org.jumia.customers.util;

import lombok.extern.slf4j.Slf4j;
import org.jumia.customers.dto.CountryDTO;
import org.jumia.customers.entity.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class DtoConvertorTest {

    @Autowired
    private DtoConvertor dtoConvertor;
    @Test
    public void testDtoConversion(){
        Country country=new Country();
        country.setId(2L);
        country.setCode("20");
        country.setName("Egypt");
        country.setRegex("");
        CountryDTO countryDto= dtoConvertor.toDTO(country, CountryDTO.class);
        assertNotNull(countryDto);
        assertEquals(countryDto.getId(),country.getId());
        assertEquals(countryDto.getName(),country.getName());
    }

    @Test
    public void testEntityConversion(){
        CountryDTO countryDto=new CountryDTO();
        countryDto.setId(2L);
        countryDto.setName("Egypt");
        Country country= dtoConvertor.toEntity(countryDto, Country.class);
        assertNotNull(country);
        assertEquals(country.getId(),countryDto.getId());
        assertEquals(country.getName(),countryDto.getName());
        assertNull(country.getCode());
        assertNull(country.getRegex());
    }
}
