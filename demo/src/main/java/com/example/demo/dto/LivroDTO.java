package com.example.demo.dto;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import com.example.demo.Entities.CategoriaLivro;
import com.example.demo.Entities.Disponibilidade;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LivroDTO {
private Long id;

@NotBlank(message = "O titulo é obrigatorio")
@Size(min = 3, max = 150, message = "O titulo deve ter entre 3 a 150 caracteres")
private String titulo;

@NotBlank(message = "O nome do autor é obrigatorio")
@Size(min = 3, max = 100, message = "O nome do autor deve ter entre 3 a 100 caracteres")
private String autor;

@NotBlank(message = "A isbn é obrigatorio")
@Size(min=10, max = 20, message = "A isbn do livro deve ter entre 10 a 20 caracteres")
private String isbn;

@NotBlank(message = "A quantidade tem que ser no minimo 1")
private int quantidade;

@NotBlank(message = "A categoria do livro é obrigatoria")
@Size(min = 3, max = 20, message = "A categoria deve ter entre 3 a 20 caracteres")
private CategoriaLivro categoria;

@NotBlank(message = "O nome da Editora é obrigatorio")
@Size(min=3, max=100)
private String editora;

@NotBlank(message = "O ano de publicação do livro é obrigatorio")
private LocalDate ano_publicação;

@NotBlank(message="A disponibilidade do livro é obrigatoria")
private Disponibilidade disponibilidade;
}
