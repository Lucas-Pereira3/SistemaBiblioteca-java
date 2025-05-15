package com.example.demo.Entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "emprestimos")
@NoArgsConstructor
@AllArgsConstructor

public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

     @Column(name = "data_emprestimo")
     private LocalDate dataEmprestimo;

     @Column(name = "data_devolucao")
     private LocalDate dataDevolucao;

     @Enumerated(EnumType.STRING)
     @Column(name = "status", length = 30, nullable = false)
     private StatusEmprestimo status;

     @OneToOne(mappedBy = "emprestimo", cascade = CascadeType.ALL, orphanRemoval = true)
     private Multa  multa;
}
