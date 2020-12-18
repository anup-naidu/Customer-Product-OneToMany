package com.javatechie.jpa.service;

import com.javatechie.jpa.dto.CustomerProductDto;
import com.javatechie.jpa.dto.ProductDto;
import com.javatechie.jpa.entity.Customer;
import com.javatechie.jpa.exception.CustomRestException;
import com.javatechie.jpa.exception.ResourceNotFoundException;
import com.javatechie.jpa.repository.CustomerRepository;
import com.javatechie.jpa.repository.ProductRepository;
import com.javatechie.jpa.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ResponseRepository responseRepository;


    public Customer addOrder(CustomerProductDto request)  {
        return customerRepository.save(request.getCustomer());
    }

    public List<Customer> getAllOrders(){
        return customerRepository.findAll();
    }

    public List<CustomerProductDto> getOrderById(Integer id , String name  ) {
        List custOrderList = null;
        try {
            custOrderList =  responseRepository.findByIdAndName(id, name);
            System.out.println("custListsize:"+custOrderList.size());
            if(custOrderList.size()<=0){
                System.out.println("inside....");
                throw new ResourceNotFoundException("Customer/Order not found with id :" + id);
            }
        }catch(ResourceNotFoundException e){
            throw new ResourceNotFoundException("Customer/Order not found with id :" + id);
        }finally {
            return custOrderList;
        }
    }

    public Customer updateOrder(CustomerProductDto request, Integer id) {
        request.getCustomer().setId(id);
        return customerRepository.save(request.getCustomer());
    }

}
