package com.onion.request;

import com.onion.entity.Customer;
import com.onion.entity.Vehicles;
import lombok.Data;

import java.util.List;

@Data
public class LSRequest {
    List<Vehicles> vehiclesList;
    List<Customer> customers;
}
