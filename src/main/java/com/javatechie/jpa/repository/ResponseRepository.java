package com.javatechie.jpa.repository;

import com.javatechie.jpa.dto.CustomerProductDto;
import com.javatechie.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Customer, Integer> {

  public List<CustomerProductDto> findByIdAndName(Integer id, String name);

}
