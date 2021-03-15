package org.jumia.customers.services;

import org.jumia.customers.dto.CountryDTO;
import org.jumia.customers.entity.Country;
import org.jumia.customers.repositories.ICountryRepository;
import org.jumia.customers.util.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService implements ICountryService{
    @Autowired
    ICountryRepository countryRepository;
    private  DtoConvertor dtoConvertor;

    public CountryService(final ICountryRepository countryRepository, DtoConvertor dtoConvertor) {
        this.countryRepository = countryRepository;
        this.dtoConvertor = dtoConvertor;
    }
    @Override
    public List<CountryDTO> getAll() {
      return ((List<Country>)this.countryRepository.findAll()).stream().map(r-> dtoConvertor.toDTO(r, CountryDTO.class)).collect(Collectors.toList());
    }

}
