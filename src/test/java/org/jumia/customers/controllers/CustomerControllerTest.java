package org.jumia.customers.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jumia.customers.dto.CustomerDto;
import org.jumia.customers.dto.Pagination;
import org.jumia.customers.entity.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenSendRequestWeExpect200StatusForSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders .get( "/customer"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    void whenSendRequestWeExpectListOfCustomers() throws Exception {
        Pagination<CustomerDto> customers=   objectMapper
                .readValue(mockMvc.perform(MockMvcRequestBuilders
                .get( "/customer"))
                 .andReturn().getResponse().getContentAsString(), new TypeReference<Pagination<CustomerDto>>() {});
        assertThat(customers).isNotNull();
        assertThat(customers.getItems()).isNotNull();
        assertThat(customers.getItems().size()).isEqualTo(10);
        assertThat(customers.getPage()).isEqualTo(0);
    }
    @Test
    void whenSendRequestToFilterByPhoneValid() throws Exception {
        Pagination<CustomerDto> customers=   objectMapper
                .readValue(mockMvc.perform((MockMvcRequestBuilders
                        .get("/customer").param("phoneState","Valid")
                        .param("page","0").param("pageSize","20")))
                        .andReturn().getResponse().getContentAsString(), new TypeReference<Pagination<CustomerDto>>() {});
        assertThat(customers).isNotNull();
        assertThat(customers.getItems()).isNotNull();
        assertThat(customers.getPage()).isEqualTo(0);
        customers.getItems().stream().forEach(r-> assertThat(r.getPhoneState()).isEqualTo(State.Valid));
    }
    @Test
    void whenSendRequestToFilterByPhoneInValid() throws Exception {
        Pagination<CustomerDto> customers=   objectMapper
                .readValue(mockMvc.perform((MockMvcRequestBuilders
                        .get("/customer").param("phoneState","INValid")
                        .param("page","0").param("pageSize","20")))
                        .andReturn().getResponse().getContentAsString(), new TypeReference<Pagination<CustomerDto>>() {});
        assertThat(customers).isNotNull();
        assertThat(customers.getItems()).isNotNull();
        assertThat(customers.getPage()).isEqualTo(0);
        customers.getItems().stream().forEach(r-> assertThat(r.getPhoneState()).isEqualTo(State.INValid));
    }
    @Test
    void whenSendRequestToFilterByCountryName() throws Exception {
        Pagination<CustomerDto> customers=   objectMapper
                .readValue(mockMvc.perform((MockMvcRequestBuilders
                        .get("/customer").param("countryName","Morocco")
                        .param("page","0").param("pageSize","20")))
                        .andReturn().getResponse().getContentAsString(), new TypeReference<Pagination<CustomerDto>>() {});
        assertThat(customers).isNotNull();
        assertThat(customers.getItems()).isNotNull();
        assertThat(customers.getPage()).isEqualTo(0);
        customers.getItems().stream().forEach(r-> assertThat(r.getCountryName()).isEqualTo("Morocco"));
    }
    @Test
    void whenSendRequestToFilterByCountryNameAndPhoneValid() throws Exception {
        Pagination<CustomerDto> customers=   objectMapper
                .readValue(mockMvc.perform((MockMvcRequestBuilders
                        .get("/customer").param("countryName","Morocco")
                        .param("phoneState","Valid")
                        .param("page","0").param("pageSize","20")))
                        .andReturn().getResponse().getContentAsString(), new TypeReference<Pagination<CustomerDto>>() {});
        assertThat(customers).isNotNull();
        assertThat(customers.getItems()).isNotNull();
        assertThat(customers.getPage()).isEqualTo(0);
        customers.getItems().stream().forEach(r-> {
            assertThat(r.getCountryName()).isEqualTo("Morocco");
            assertThat(r.getPhoneState()).isEqualTo(State.Valid);
        });
    }
    @Test
    void whenSendRequestToFilterByCountryNameAndPhoneInValid() throws Exception {
        Pagination<CustomerDto> customers=   objectMapper
                .readValue(mockMvc.perform((MockMvcRequestBuilders
                        .get("/customer").param("countryName","Morocco")
                        .param("phoneState","INValid")
                        .param("page","0").param("pageSize","20")))
                        .andReturn().getResponse().getContentAsString(), new TypeReference<Pagination<CustomerDto>>() {});
        assertThat(customers).isNotNull();
        assertThat(customers.getItems()).isNotNull();
        assertThat(customers.getPage()).isEqualTo(0);
        customers.getItems().stream().forEach(r-> {
            assertThat(r.getCountryName()).isEqualTo("Morocco");
            assertThat(r.getPhoneState()).isEqualTo(State.INValid);
        });
    }

}
