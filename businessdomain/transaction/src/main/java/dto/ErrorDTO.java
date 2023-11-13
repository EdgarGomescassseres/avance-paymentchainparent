/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author edgar
 */
@Data
@Builder
public class ErrorDTO {
    private String code;
    private String message;
}
