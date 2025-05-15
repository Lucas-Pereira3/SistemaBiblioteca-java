package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmprestimoDTO {

    private Long id;

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "O ID do livro é obrigatório")
    private Long livroId;

    private LocalDate dataEmprestimo;

    @NotNull(message = "A data de devolução é obrigatória")
    @FutureOrPresent(message = "A data de devolução deve ser hoje ou no futuro")
    private LocalDate dataDevolucao;

    private String status;
}
