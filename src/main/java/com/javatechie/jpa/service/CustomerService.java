package com.javatechie.jpa.service;

import com.javatechie.jpa.dto.CustomerProductDto;
import com.javatechie.jpa.entity.Customer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {

    public Customer addOrder(CustomerProductDto request);

    public List<Customer> getAllOrders();

    public List<CustomerProductDto> getOrderById(Integer id , String name  );

    public Customer updateOrder(CustomerProductDto request,Integer id);



}
