package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Disponibilidade;
import com.example.demo.Entities.Livro;
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    List<Livro> findByDisponibilidade(Disponibilidade disponibilidade);
    Optional<Livro> findByTitulo(String titulo);
}
