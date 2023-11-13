package com.paymentchain.transaction.service;


import com.paymentchain.transaction.entities.Transaction;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paymentchain.transaction.respository.TransactionRepository;
import exception.TransactionNotValidException;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> custo = transactionRepository.findById(id);
        if (custo.isPresent()) {
            return transactionRepository.getReferenceById(id);
        } else {
            return null;
        }
    }
    
    public void createTransaction(Transaction transaction){
        LocalDateTime fechaEnviada = transaction.getDate();
        LocalDateTime fechaActual = LocalDateTime.now();
        if(transaction.getAmount() == 0){
         throw new TransactionNotValidException("¡UPSS! El monto no puede ser sero", "p-400"); 
        }
        if(transaction.getFee() > 0){
           transaction.setAmount(transaction.getAmount() - transaction.getFee());
        }
        if(fechaEnviada.isAfter(fechaActual)){
            transaction.setStatus("01");
        } else {
            transaction.setStatus("02");
        }
        transactionRepository.save(transaction);
    }
    
//    public Transaction updateTransaction(Long id, Transaction transaction){
//        Transaction prod = transactionRepository.findById(id).get();
//        if(prod != null){
//        prod.setCode(product.getCode());
//        prod.setName(product.getName());
//        }
//        Transaction save = transactionRepository.save(prod);
//        return save;
//    }
    
    public void deleteTransactionById(Long id){
         Optional<Transaction> custo = transactionRepository.findById(id);
        if (custo.isPresent()) {
            transactionRepository.deleteById(id);
        } 
    }
    
    public Transaction getTransactionByAccound(String Accound) {
        return transactionRepository.getByibanAccount(Accound);
    }
}
