package org.jumia.customers.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jumia.customers.dto.CustomerDto;
import org.jumia.customers.dto.Pagination;
import org.jumia.customers.entity.State;
import org.jumia.customers.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@Slf4j
@RestController
@RequestMapping(value = "/customer")
@Api(tags = "customer")
public class CustomerController {

    @Autowired
    ICustomerService customerService;;
    @ApiOperation(value = "Search customer", notes = "Search for customers based on country name, phone state/status, and retrieving a full list of customers if filters disabled ")
    @GetMapping
    public Pagination<CustomerDto> search(@RequestParam(required = false) Optional<String> countryName,
                                          @RequestParam(required = false) State phoneState,
                                          @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageSize)
    {
        return customerService.search(countryName,phoneState,page,pageSize);
    }
}
