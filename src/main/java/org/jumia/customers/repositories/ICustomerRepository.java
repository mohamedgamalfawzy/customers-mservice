package org.jumia.customers.repositories;

import org.jumia.customers.entity.Customer;
import org.jumia.customers.entity.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICustomerRepository extends CrudRepository<Customer,Long>,IRepository<Customer> {
    Page<Customer> findCustomersByCountry_Name(String name, Pageable pageable);
    Page<Customer> findCustomersByPhoneState(State phoneState, Pageable pageable);
    Page<Customer> findCustomersByCountry_NameAndPhoneState(String name, State phoneState,Pageable pageable);
    Page<Customer> findCustomersByCountry_Code(String countryCode,Pageable pageable);
    Page<Customer> findCustomersByNameContainsAndPhoneStateAndCountry_Code(String name, State phoneState, String countryCode,Pageable pageable);
}
