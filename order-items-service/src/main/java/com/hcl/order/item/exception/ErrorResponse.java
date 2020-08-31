package com.hcl.order.item.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse 
{
 
    private String message;
 
    private List<String> details;
    
}
