package com.onion.request;

import com.onion.entity.Customer;
import lombok.Data;

import java.util.List;

@Data
public class LSRequest {
    List<Customer> customers;
}
