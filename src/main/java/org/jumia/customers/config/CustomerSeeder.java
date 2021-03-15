package org.jumia.customers.config;

import org.jumia.customers.Constants;
import org.jumia.customers.dialect.seeders.Seeder;
import org.jumia.customers.entity.Country;
import org.jumia.customers.entity.Customer;
import org.jumia.customers.entity.State;
import org.jumia.customers.repositories.ICountryRepository;
import org.jumia.customers.repositories.ICustomerRepository;
import org.jumia.customers.util.IValidator;
import org.jumia.customers.util.RegexHelper;
import org.jumia.customers.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

@Component
public class CustomerSeeder implements Seeder<Customer> {
    @Autowired
    ICustomerRepository customerRepository;

    @Autowired
    ICountryRepository countryRepository;

    @Override
    public void seed(List<Customer> list) {
        Pageable pageable= PageRequest.of(0,100);
        Page<Customer> customers=customerRepository.findAll(pageable);
        customers.toList().stream().forEach(customer -> {
            Matcher matcher= RegexHelper.pattern(Constants.COUNTRY_CODE_PATTERN).matcher(customer.getPhone());
            if (matcher.find()){
                String countryCode = matcher.group(0).substring(1, matcher.group(0).length() - 1);
                Optional<Country> country=countryRepository.findFirstByCode(countryCode);
                customer.setCountry(country.isPresent() ?  country.get() : null);
                IValidator<String> validator= Validator.from(r->  RegexHelper.pattern(country.get().getRegex()).matcher(r).matches());
                customer.setPhoneState(validator.validate(customer.getPhone())? State.Valid: State.INValid );
            }
            list.add(customer);
        });
    }
}
