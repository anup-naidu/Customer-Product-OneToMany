package com.javatechie.jpa.controller;

import com.javatechie.jpa.dto.CustomerProductDto;
import com.javatechie.jpa.entity.Customer;
import com.javatechie.jpa.exception.CustomRestException;
import com.javatechie.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@PropertySource("classpath:messages.properties")
public class OrderController {
    @Autowired
    private CustomerService customerService;

    @Value("${custId.message}")
    String custIdMessage;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @PostMapping(value = "/placeOrder")
    public Customer placeOrder(@RequestBody CustomerProductDto request) throws CustomRestException {
      /* if(request.getCustomer().getId()==null)
       {
           throw new CustomRestException(this.messageSource.getMessage("custId.message",new Object[]{request.getCustomer().getId()},null));
       }*/
        return customerService.addOrder(request);
    }

    @GetMapping("/findAllOrders")
    public List<Customer> findAllOrders(){
        return customerService.getAllOrders();
    }

    @GetMapping("/getCustOrder")
    public List<CustomerProductDto> getSingleOrder(@RequestParam (required = true) Integer id , @RequestParam (required = true) String name  ) {
        List custOrderList = customerService.getOrderById(id, name);
        return custOrderList;
    }

    @PutMapping("/updateOrder/{id}")
    public Customer updateOrder(@RequestBody CustomerProductDto request, @PathVariable Integer id) {
        return customerService.updateOrder(request,id);
    }

}
