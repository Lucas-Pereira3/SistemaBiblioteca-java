package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.Entities.StatusMulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MultaDTO {

    private Long id;

    
    private Emprestimo emprestimo;

    private BigDecimal valor;

    
    private StatusMulta status;

    private LocalDateTime dataPagamento;
}
