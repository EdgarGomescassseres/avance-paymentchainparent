/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import lombok.Data;

/**
 *
 * @author edgar
 */
@Data
public class TransactionNotValidException extends RuntimeException {
    private String code;

    public TransactionNotValidException(String message, String code) {
        super(message);
        this.code = code;
    }
    
    
    
}
