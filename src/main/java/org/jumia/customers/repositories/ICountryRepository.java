package org.jumia.customers.repositories;

import org.jumia.customers.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICountryRepository extends CrudRepository<Country,Long>,IRepository {
    Optional<Country> findFirstByCode(String countryCode);
}
