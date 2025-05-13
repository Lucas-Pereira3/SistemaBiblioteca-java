package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public List<ClienteDTO> listarTodos(){
        return clienteMapper.toDTOList(clienteRepository.findAll());
    }

    public Optional<ClienteDTO> buscarPorId(Long id){
        return clienteRepository.findById(id).map(clienteMapper::toDTO);
    }

    public ClienteDTO salvar(ClienteDTO clienteDTO){
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        return clienteMapper.toDTO(clienteRepository.save(cliente));
    }

    public void deletar(Long id){
        clienteRepository.deleteById(id);
    }

    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO){
        Cliente clienteExistente = clienteRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));

        clienteDTO.setId(id);
        Cliente clienteAtualizado = clienteMapper.toEntity(clienteDTO);
        return clienteMapper.toDTO(clienteRepository.save(clienteAtualizado));
    }
}
