package org.jumia.customers.services;

import org.jumia.customers.dto.CustomerDto;
import org.jumia.customers.dto.Pagination;
import org.jumia.customers.entity.Customer;
import org.jumia.customers.entity.State;
import org.jumia.customers.repositories.ICustomerRepository;
import org.jumia.customers.util.DtoConvertor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerService implements ICustomerService{

    private final ICustomerRepository customerRepository;
    private final  DtoConvertor dtoConvertor;

    public CustomerService(final ICustomerRepository customerRepository,final DtoConvertor dtoConvertor) {
        this.customerRepository = customerRepository;
        this.dtoConvertor = dtoConvertor;
    }

    @Override
    public Pagination<CustomerDto> search(Optional<String> countryName, State phoneState, int page, int pageSize) {
        Pageable pageable= PageRequest.of(page,pageSize);
        Page<Customer> customers= identifyQueryType(countryName,phoneState,pageable);
        return  dtoConvertor.toPagination(customers,customers.toList().stream().map(r-> dtoConvertor.toDTO(r,CustomerDto.class)).collect(Collectors.toList()));
    }
    private Page<Customer> identifyQueryType(Optional<String> countryName, State phoneState,Pageable pageable){
        boolean isName=countryName!=null && countryName.isPresent() && !countryName.get().isEmpty() && !countryName.get().equalsIgnoreCase("null"),
                isPhoneState =phoneState !=null && phoneState !=State.ALL,
                isBoth = isName && isPhoneState;


        if (isBoth){
            return  this.customerRepository.findCustomersByCountry_NameAndPhoneState(countryName.get(),phoneState,pageable);
        }else if (isName){
            return this.customerRepository.findCustomersByCountry_Name(countryName.get(),pageable);
        }else if (isPhoneState) {
            return this.customerRepository.findCustomersByPhoneState(phoneState,pageable);
        } else{
            return this.customerRepository.findAll(pageable);
        }
    }
}
