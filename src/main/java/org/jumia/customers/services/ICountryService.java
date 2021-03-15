package org.jumia.customers.services;

import org.jumia.customers.dto.CountryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICountryService {
    List<CountryDTO> getAll();
}
