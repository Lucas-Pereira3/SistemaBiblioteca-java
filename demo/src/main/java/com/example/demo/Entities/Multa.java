package com.example.demo.Entities;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "Multa")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Multa {

       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;

    @Column(name = "valor", precision  = 18, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private StatusMulta status;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    public BigDecimal valormulta(){
        int dias= Period.between(emprestimo.getDataDevolucao(), LocalDate.now()).getDays();
        if(dias>1){
            BigDecimal multapordia= new BigDecimal("2.50");
            return multapordia.multiply(BigDecimal.valueOf(dias));
        }
        return BigDecimal.ZERO;
    }

}
