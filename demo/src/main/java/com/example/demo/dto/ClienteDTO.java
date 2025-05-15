package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatorio")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;
    
    @NotBlank(message = "E-mail invalido")
    @Size(message = "O e-mail é obrigatorio")
    private String email;

    @NotBlank(message = "O Telefone é obrigatorio")
    @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
    private String telefone;

    @NotBlank(message = "O endereço é obrigatorio")
    private String endereco;

    @NotBlank(message = "O documento é obrigatorio")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    private String documento;
}
