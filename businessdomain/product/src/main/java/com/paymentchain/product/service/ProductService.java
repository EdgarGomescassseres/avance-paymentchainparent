/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.product.service;


import com.paymentchain.product.entities.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paymentchain.product.respository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> custo = productRepository.findById(id);
        if (custo.isPresent()) {
            return productRepository.getReferenceById(id);
        } else {
            return null;
        }
    }
    
    public void createProduct(Product customer){
        productRepository.save(customer);
    }
    
    public Product updateProduct(Long id, Product product){
        Product prod = productRepository.findById(id).get();
        if(prod != null){
        prod.setCode(product.getCode());
        prod.setName(product.getName());
        }
        Product save = productRepository.save(prod);
        return save;
    }
    
    public void deleteProductById(Long id){
         Optional<Product> custo = productRepository.findById(id);
        if (custo.isPresent()) {
            productRepository.deleteById(id);
        } 
    }
}
