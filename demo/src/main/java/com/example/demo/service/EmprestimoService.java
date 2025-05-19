package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cliente;
import com.example.demo.Entities.Emprestimo;
import com.example.demo.Entities.Livro;
import com.example.demo.Entities.StatusEmprestimo;
import com.example.demo.dto.EmprestimoDTO;
import com.example.demo.mapper.EmprestimoMapper;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EmprestimoRepository;
import com.example.demo.repository.LivroRepository;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoMapper emprestimoMapper;

    public Emprestimo registrarEmprestimo(EmprestimoDTO dto) {
    if (dto.getClienteId() == null || dto.getLivroId() == null) {
        throw new IllegalArgumentException("clienteId e livroId não podem ser nulos");
    }

    Cliente cliente = clienteRepository.findById(dto.getClienteId())
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    Livro livro = livroRepository.findById(dto.getLivroId())
        .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

    Emprestimo emprestimo = new Emprestimo();
    emprestimo.setCliente(cliente);
    emprestimo.setLivro(livro);
    emprestimo.setDataEmprestimo(dto.getDataEmprestimo());
    emprestimo.setDataDevolucao(dto.getDataDevolucao());
    emprestimo.setStatus(StatusEmprestimo.valueOf(dto.getStatus().toUpperCase()));


    return emprestimoRepository.save(emprestimo);
}

    public EmprestimoDTO atualizarStatus(Long id, String status) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado com ID: " + id));

        emprestimo.setStatus(StatusEmprestimo.valueOf(status.toUpperCase()));
        return emprestimoMapper.toDTO(emprestimoRepository.save(emprestimo));
    }

    public List<EmprestimoDTO> listarPorCliente(Long clienteId) {
        return emprestimoRepository.findByClienteId(clienteId).stream()
                .map(emprestimoMapper::toDTO)
                .collect(Collectors.toList());
    }

   

    public EmprestimoDTO registrarDevolucao(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado com ID: " + id));

        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo.setStatus(StatusEmprestimo.CONCLUIDO);

        return emprestimoMapper.toDTO(emprestimoRepository.save(emprestimo));
    }

    public Optional<EmprestimoDTO> buscarPorId(Long id) {
        return emprestimoRepository.findById(id).map(emprestimoMapper::toDTO);
    }
}
