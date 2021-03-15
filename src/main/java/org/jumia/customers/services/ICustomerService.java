package org.jumia.customers.services;

import org.jumia.customers.dto.CustomerDto;
import org.jumia.customers.dto.Pagination;
import org.jumia.customers.entity.State;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ICustomerService {
  Pagination<CustomerDto> search(Optional<String> countryName, State phoneState, int page, int pageSize);
}
