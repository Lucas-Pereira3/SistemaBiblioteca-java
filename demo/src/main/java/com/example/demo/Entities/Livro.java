package com.example.demo.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "Livro")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Livro {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private Long id;

@Column(name = "Titulo",length = 150,nullable = false)
private String titulo;

@Column (name = "Autor",length = 100,nullable = false)
private String autor;

@Column (name = "isbn" ,length = 20,nullable = false)
private String isbn;

@Column (name="Quantidade",nullable = false)
private int quantidade;

@Enumerated(EnumType.STRING)
@Column(name="Categoria",length = 30,nullable = false)
private CategoriaLivro Categoria;
}
