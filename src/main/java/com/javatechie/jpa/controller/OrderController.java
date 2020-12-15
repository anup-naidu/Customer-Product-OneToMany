package com.javatechie.jpa.controller;

import com.javatechie.jpa.dto.CustomerProductDto;
import com.javatechie.jpa.dto.ProductDto;
import com.javatechie.jpa.entity.Customer;
import com.javatechie.jpa.repository.CustomerRepository;
import com.javatechie.jpa.repository.ProductRepository;
import com.javatechie.jpa.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @PostMapping("/placeOrder")
    public Customer placeOrder(@RequestBody CustomerProductDto request){
       return customerRepository.save(request.getCustomer());
    }

    @GetMapping("/findAllOrders")
    public List<Customer> findAllOrders(){
        return customerRepository.findAll();
    }

    @GetMapping("/getInfo")
    public List<ProductDto> getJoinInformation(){
        return customerRepository.getJoinInformation();
    }

    @GetMapping("/getCustOrder")//get a single record of a customer and his order by customer id.
    public List<CustomerProductDto> getSingleOrder(@RequestParam (required = true) Integer id , @RequestParam (required = true) String name  ) {
        return responseRepository.findByIdAndName(id, name);
    }

    @PutMapping("/updateOrder/{id}")//update a single record for the requested id.
    public Customer updateOrder(@RequestBody CustomerProductDto request, @PathVariable Integer id) {
        request.getCustomer().setId(id);
        return customerRepository.save(request.getCustomer());
    }


    }
