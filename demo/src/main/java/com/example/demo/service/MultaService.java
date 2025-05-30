package com.example.demo.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.Entities.Multa;
import com.example.demo.Entities.StatusMulta;
import com.example.demo.dto.MultaDTO;
import com.example.demo.mapper.MultaMapper;
import com.example.demo.repository.EmprestimoRepository;
import com.example.demo.repository.MultaRepository;

@Service
public class MultaService {

    @Autowired
    private MultaRepository multaRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private MultaMapper multaMapper;

    public Multa registrarMulta(Long emprestimoid){
        Emprestimo emprestimo= emprestimoRepository.findById(emprestimoid)
        .orElseThrow(()-> new RuntimeException("Emprestimo não encontrado"));

        if(multaRepository.findByEmprestimoId(emprestimoid).isPresent()){
            throw new RuntimeException("multa já registrada para esse emprestimo");
        }

        Multa multa= new Multa();

        multa.setEmprestimo(emprestimo);

        BigDecimal valorCalculado = multa.valormulta();
        multa.setValor(valorCalculado !=null ? valorCalculado : BigDecimal.valueOf(2.50));

        multa.setStatus(StatusMulta.NAO_PAGO);

        return multaRepository.save(multa);
    }

    public List<MultaDTO> listarTodos(){
        return multaMapper.toDTOList(multaRepository.findAll());
    }

    public Optional<MultaDTO> multaporId(Long id){
        return multaRepository.findById(id).map(multaMapper::toDTO);
    }

    public List<MultaDTO> ListarNãoPagos(){
        return multaRepository.findByStatus(StatusMulta.NAO_PAGO)
        .stream().map(multaMapper::toDTO).collect(Collectors.toList());
    }
}
