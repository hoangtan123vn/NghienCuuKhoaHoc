package com.onion.controller;


import com.onion.entity.Customer;
import com.onion.entity.Vehicles;
import com.onion.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalSeachAPI {

    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping("/customers")
    public Customer TaoKhachHang(@RequestBody Customer customer)
    {
        return customerRepository.save(customer);
    }
}
