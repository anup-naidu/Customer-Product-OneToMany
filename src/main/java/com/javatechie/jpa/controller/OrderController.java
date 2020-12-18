package com.javatechie.jpa.controller;

import com.javatechie.jpa.dto.CustomerProductDto;
import com.javatechie.jpa.dto.ProductDto;
import com.javatechie.jpa.entity.Customer;
import com.javatechie.jpa.exception.CustomRestException;
import com.javatechie.jpa.exception.ResourceNotFoundException;
import com.javatechie.jpa.repository.CustomerRepository;
import com.javatechie.jpa.repository.ProductRepository;
import com.javatechie.jpa.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PropertySource("classpath:messages.properties")
public class OrderController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ResponseRepository responseRepository;

    @Value("${custId.message}")
    String custIdMessage;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @PostMapping("/placeOrder")
    public Customer placeOrder(@RequestBody CustomerProductDto request) throws CustomRestException {
       if(request.getCustomer().getId()==null)
       {
           throw new CustomRestException(this.messageSource.getMessage("custId.message",new Object[]{request.getCustomer().getId()},null));
       }

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

    /*@GetMapping("/getCustOrder")//get a single record of a customer and his order by customer id.
    public List<CustomerProductDto> getSingleOrder(@RequestParam (required = true) Integer id , @RequestParam (required = true) String name  ) {
        return responseRepository.findByIdAndName(id, name);
    }*/

    @GetMapping("/getCustOrder")//get a single record of a customer and his order by customer id.
    public List<CustomerProductDto> getSingleOrder(@RequestParam (required = true) Integer id , @RequestParam (required = true) String name  ) {
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

    @PutMapping("/updateOrder/{id}")//update a single record for the requested id.
    public Customer updateOrder(@RequestBody CustomerProductDto request, @PathVariable Integer id) {
        request.getCustomer().setId(id);
        return customerRepository.save(request.getCustomer());
    }


    }
