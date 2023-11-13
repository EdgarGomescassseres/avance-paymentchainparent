/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.customer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.entities.CustomerProduct;
import com.paymentchain.customer.respository.CustomerRepository;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.print.attribute.standard.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepository customerRepository;
    
    private final WebClient.Builder webClientBuilder;
    
    public CustomerService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
// Crear una instancia de HttpClient de Reactor Netty
    HttpClient client = HttpClient.create()
            // Establece un tiempo máximo de conexión de 5 segundos (5000 milisegundos).
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            // Activa la opción SO_KEEPALIVE en el socket del canal para mantener la conexión abierta.
            .option(ChannelOption.SO_KEEPALIVE, true)
            // Establece el tiempo máximo de inactividad (TCP_KEEPIDLE) a 300 segundos (5 minutos).
            .option(EpollChannelOption.TCP_KEEPIDLE, 300)
            // Establece el intervalo entre las transmisiones de paquetes de mantenimiento a 60 segundos.
            .option(EpollChannelOption.TCP_KEEPINTVL, 60)
            // Establecer un tiempo máximo de respuesta global para todas las solicitudes
            .responseTimeout(Duration.ofSeconds(1))
            // Establece un tiempo máximo de espera de 1 segundo para obtener una respuesta del servidor.
            .doOnConnected(connection -> {
                // Agrega un manejador de tiempo de espera de lectura de 5 segundos (5000 milisegundos).
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                // Agrega un manejador de tiempo de espera de escritura de 5000 microsegundos (5 milisegundos).
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MICROSECONDS));
            });
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public Customer getCustomerById(Long id) {
        Optional<Customer> custo = customerRepository.findById(id);
        if (custo.isPresent()) {
            return customerRepository.getReferenceById(id);
        } else {
            return null;
        }
    }
    
    public void createCustomer(Customer customer) {
        customer.getProducts().forEach(product -> product.setCustomer(customer));
        customerRepository.save(customer);
    }
    
    public Customer updateCustomer(Long id, Customer customer) {
        Customer cust = customerRepository.findById(id).get();
        if (cust != null) {
            cust.setCode(customer.getCode());
            cust.setName(customer.getName());
        }
        Customer save = customerRepository.save(cust);
        return save;
    }
    
    public void DeleteCustomerById(Long id) {
        Optional<Customer> custo = customerRepository.findById(id);
        if (custo.isPresent()) {
            customerRepository.deleteById(id);
        }
    }
    
    public Customer findByCode(String code) {
        Customer customer = customerRepository.findByCode(code);
        List<CustomerProduct> products = customer.getProducts();
        products.forEach(x ->{
            String productName = getProductName(x.getProductId());
            x.setProductName(productName);
        });
        return customer;
    }
    
    public Customer findByIban(String iban) {
        Customer c = customerRepository.findByAccount(iban);
        return c;
    }
    
    private String getProductName(long id) { 
        WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl("http://localhost:8083/product")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8083/product"))
                .build();
        
        JsonNode block = build.method(HttpMethod.GET).uri("/" + id)
                .retrieve().bodyToMono(JsonNode.class).block();
        
        String name = block.get("name").asText();
        return name;
    }
    
}
