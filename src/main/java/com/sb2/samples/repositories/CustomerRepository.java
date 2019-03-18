package com.sb2.samples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb2.samples.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
