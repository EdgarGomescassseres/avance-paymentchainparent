/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.transaction.controller;


import com.paymentchain.transaction.entities.Transaction;
import com.paymentchain.transaction.service.TransactionService;
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

/**
 *
 * @author sotobotero
 */
@RestController
@RequestMapping("/transaction")
public class TransactionRestController {
    
    @Autowired
    private TransactionService transactionService;
    
    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getAllProducts() {
      List<Transaction> listCusto = transactionService.getAllTransaction();
      return ResponseEntity.status(HttpStatus.OK).body(listCusto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getProductById(@PathVariable Long id) {
      Transaction customerId = transactionService.getTransactionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerId);
    }
    
    @GetMapping("/Accound")
    public ResponseEntity<Transaction> getByAccound(@RequestParam String accound){
        Transaction t = transactionService.getTransactionByAccound(accound);
        return ResponseEntity.status(HttpStatus.OK).body(t);
    }
    
//    @PutMapping("/{id}")
//    public ResponseEntity<Transaction> updateProduct(@PathVariable Long id, @RequestBody Transaction product) {
//        Transaction prod = transactionService.updateProduct(id, product);
//        return ResponseEntity.status(HttpStatus.OK).body(prod);
//    }
    
    @PostMapping("/")
    public ResponseEntity<Boolean> post(@RequestBody Transaction customer) {
      transactionService.createTransaction(customer);
      return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        transactionService.deleteTransactionById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Boolean.TRUE);
         
    }
    
}
