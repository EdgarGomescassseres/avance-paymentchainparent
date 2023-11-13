/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.product.controller;


import com.paymentchain.product.entities.Product;
import com.paymentchain.product.service.ProductService;
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

/**
 *
 * @author sotobotero
 */
@RestController
@RequestMapping("/product")
public class ProductRestController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
      List<Product> listCusto = productService.getAllProducts();
      return ResponseEntity.status(HttpStatus.OK).body(listCusto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
      Product customerId = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerId);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product prod = productService.updateProduct(id, product);
        return ResponseEntity.status(HttpStatus.OK).body(prod);
    }
    
    @PostMapping("/")
    public ResponseEntity<Boolean> post(@RequestBody Product customer) {
      productService.createProduct(customer);
      return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Boolean.TRUE);
         
    }
    
}
