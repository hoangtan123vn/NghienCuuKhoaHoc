package com.onion.controller;


import com.onion.LocalSearchService;
import com.onion.entity.Depot;
import com.onion.repository.DepotRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path="api/localsearch")
public class LocalSeachAPI {

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    LocalSearchService localSearchService;

//    @Autowired
//    CustomerRepository customerRepository;

    @PostMapping("/add/depot")
    public Depot AddDepot(@RequestBody Depot depot){
        return depotRepository.save(depot);
    }

    @GetMapping("/test")
    public String test() throws IOException, ParseException {
        localSearchService.init();
        return "hello";
    }

//    @PostMapping("/add/customer")
//    public Customer AddCustomer(@RequestBody Customer customer){
//        return customerRepository.save(customer);
//    }
}
