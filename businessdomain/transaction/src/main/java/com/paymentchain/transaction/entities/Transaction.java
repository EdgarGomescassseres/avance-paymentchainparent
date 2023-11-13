/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.transaction.entities;


import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author edgar
 */
@Entity
@Data
public class Transaction {
    
    @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
   private long id;
   private String reference;
   private String ibanAccount;
   @DateTimeFormat(pattern = "dd/MM/uuuu HH:mm")
   private LocalDateTime date;
   private double amount;
   private double fee;   
   private String description;
   private String status;
   private String channel;  
}
