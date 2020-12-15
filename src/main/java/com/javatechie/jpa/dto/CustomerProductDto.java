package com.javatechie.jpa.dto;

import com.javatechie.jpa.entity.Customer;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerProductDto {

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Customer customer;
}
