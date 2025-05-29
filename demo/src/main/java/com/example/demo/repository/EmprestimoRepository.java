package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.Entities.StatusEmprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

    List<Emprestimo> findByClienteId(Long clienteId);
    List<Emprestimo> findByStatus(StatusEmprestimo status);
    

}
