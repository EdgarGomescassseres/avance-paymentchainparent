/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.customer.controller;

import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.service.CustomerService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

/**
 *
 * @author sotobotero
 */
@RestController
@RequestMapping("/customer")
public class CustomerRestController {
    
    @Autowired
    CustomerService customerService;
    
    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAll() {
      List<Customer> listCusto = customerService.getAllCustomers();
      return ResponseEntity.status(HttpStatus.OK).body(listCusto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
      Customer customerId = customerService.getCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerId);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Customer customer) {
        Customer prod = customerService.updateCustomer(id, customer);
        return ResponseEntity.status(HttpStatus.OK).body(prod);
    }
    
    @PostMapping("/")
    public ResponseEntity<Boolean> post(@RequestBody Customer customer) {
      customerService.createCustomer(customer);
      return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        customerService.DeleteCustomerById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Boolean.TRUE);
         
    }
    
     @GetMapping("/full")
    public Customer getByCode(@RequestParam String code) {
      return customerService.findByCode(code);
      
    }
}
