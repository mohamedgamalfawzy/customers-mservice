package org.jumia.customers.dto;

import lombok.Getter;
import lombok.Setter;
import org.jumia.customers.entity.State;

@Getter
@Setter
public class CustomerDto {
    public Long id;
    public String name;
    public String countryName;
    public String countryCode;
    public String phone;
    private State phoneState;
}
