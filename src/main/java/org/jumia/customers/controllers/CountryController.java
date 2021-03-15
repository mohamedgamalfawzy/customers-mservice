package org.jumia.customers.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jumia.customers.dto.CountryDTO;
import org.jumia.customers.services.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/country")
@Api(tags = "country")
public class CountryController {

    @Autowired
    ICountryService countryService;
    @ApiOperation(value = "Get All Countries" , notes = "Retrieve all the countries data stored in DB")
    @GetMapping
    public List<CountryDTO> getAll(){
        return countryService.getAll();
    }
}
