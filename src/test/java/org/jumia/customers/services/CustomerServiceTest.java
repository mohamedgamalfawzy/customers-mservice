package org.jumia.customers.services;

import lombok.extern.slf4j.Slf4j;
import org.jumia.customers.Constants;
import org.jumia.customers.dto.CustomerDto;
import org.jumia.customers.dto.Pagination;
import org.jumia.customers.entity.Customer;
import org.jumia.customers.entity.State;
import org.jumia.customers.repositories.ICustomerRepository;
import org.jumia.customers.util.RegexHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    ICustomerRepository customerRepository;
    @Autowired
    ICustomerService customerService;
    @Test
    public  void testShouldPhoneMatches(){
        Customer customer=getCustomerTestInstance();
        Matcher matcher= RegexHelper.pattern(Constants.COUNTRY_CODE_PATTERN).matcher(customer.getPhone());
        assertNotNull(matcher);
        assertTrue(matcher.find());;
        assertFalse(matcher.matches());
    }
    @Test
    public void shouldSaveCustomerWithoutCountry(){
        Customer customer=getCustomerTestInstance();
        try {
            Customer c= customerRepository.save(customer);
        }catch (Exception e){
            fail("There should be a constraints on the country field. [Actual] customer object saved. [Expected] Customer object should not saved");
        }
    }
    @Test void shouldPageWithFindAllSuccess(){
        Pagination<CustomerDto> customer =this.customerService.search(null,null,0,10);
        assertNotNull(customer);
        assertNotNull(customer.getItems());
        assertEquals(customer.getItems().size() ,10);
    }
    @Test void shouldPaginationReturnItemsAndPaginationsDetails(){
        Pagination<CustomerDto> customer =this.customerService.search(null,null,0,10);
        assertEquals(customer.getPage(),0);
        assertEquals(customer.getTotalPages(),customer.getTotalPages());
        assertEquals(customer.getPageSize(),10);
        assertNotNull(customer.getItems());
    }
    @Test
    public  void shouldSearchForCustomerByCountryName(){
        Pagination<CustomerDto> customerPage=this.customerService.search(Optional.of("Ethiopia"),State.ALL,0,10);
        assertNotNull(customerPage);
        assertNotNull(customerPage.getItems());
        assertNotEquals(customerPage.getItems().size(),0);
        customerPage.getItems().stream().forEach(r-> assertEquals(r.getCountryName().toLowerCase(),"Ethiopia".toLowerCase()));
    }

    @Test void shouldSearchForPhoneValidPhone(){
        Pagination<CustomerDto> customerPage=this.customerService.search(null,State.Valid,0,10);
        assertNotNull(customerPage);
        assertNotNull(customerPage.getItems());
        assertNotEquals(customerPage.getItems().size(),0);
        customerPage.getItems().stream().forEach(r-> assertEquals(r.getPhoneState(),State.Valid));
    }

    @Test void shouldSearchForPhoneInValidPhone(){
        Pagination<CustomerDto> customerPage=this.customerService.search(null,State.INValid,0,10);
        assertNotNull(customerPage);
        assertNotNull(customerPage.getItems());
        assertNotEquals(customerPage.getItems().size(),0);
        customerPage.getItems().stream().forEach(r-> assertEquals(r.getPhoneState(),State.INValid));
    }
    @Test void shouldReturnEmpty(){
        Pagination<CustomerDto> customerPage=this.customerService.search(Optional.of("France"),State.INValid,0,10);
        assertNotNull(customerPage);
        assertEquals(customerPage.getItems().size(),0);
    }

    @Test void testFullSearchWithAllAttributes(){
        Pagination<CustomerDto> customerPage=this.customerService.search(Optional.of("Ethiopia"),State.Valid,0,10);
        assertNotNull(customerPage);
        assertNotNull(customerPage.getItems());
        assertNotEquals(customerPage.getItems().size(),0);
        customerPage.getItems().stream().forEach(r-> {assertEquals(r.getPhoneState(),State.Valid);assertEquals(r.getCountryName(),"Ethiopia");} );
    }

    private Customer getCustomerTestInstance(){
        Customer customer =new Customer();
        customer.setId(300L);
        customer.setName("Test Customer Without Country");
        customer.setPhone("(212) 6007989253");
        customer.setCountry(null);
        customer.setPhoneState(State.INValid);
        return customer;
    }
}
