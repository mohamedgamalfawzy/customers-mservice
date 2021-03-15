package org.jumia.customers.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="customer")
@ApiModel(description="All details about the Customer. ")
public class Customer {
    @Id
    private Long id;
    private String name;
    private String phone;
    private State phoneState;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private  Country country;
}
