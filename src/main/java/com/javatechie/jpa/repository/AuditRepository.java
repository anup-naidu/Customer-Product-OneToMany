package com.javatechie.jpa.repository;

import com.javatechie.jpa.dto.ProductDto;
import com.javatechie.jpa.entity.AuditLog;
import com.javatechie.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuditRepository extends JpaRepository<AuditLog,Integer> {


}

