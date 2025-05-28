package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Disponibilidade;
import com.example.demo.Entities.Livro;
import com.example.demo.dto.LivroDTO;
import com.example.demo.mapper.LivroMapper;
import com.example.demo.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroMapper livroMapper;

    public List<LivroDTO> listarTodos(){
        return livroMapper.toDTOList(livroRepository.findByDisponibilidade(Disponibilidade.DISPONIVEL));
    }

    public Optional<LivroDTO> buscarPorID(Long id){
        return livroRepository.findById(id).map(livroMapper::toDTO);
    }

    public LivroDTO salvar(LivroDTO livroDTO){
        Livro livro= livroMapper.toEntity(livroDTO);
        return livroMapper.toDTO(livroRepository.save(livro));
    }

    public void deletar(Long id){
        livroRepository.deleteById(id);
    }

    public LivroDTO atualizar(Long id,LivroDTO livroDTO){
        Livro livroExistente= livroRepository.findById(id)
        .orElseThrow(()-> new IllegalArgumentException("Livro não encontrado com ID: "+id));
        livroExistente.setTitulo(livroDTO.getTitulo());
        livroExistente.setAutor(livroDTO.getAutor());
        livroExistente.setIsbn(livroDTO.getIsbn());
        livroExistente.setEditora(livroDTO.getEditora());
        livroExistente.setAno_publicação(livroDTO.getAno_publicação());
        livroExistente.setDisponibilidade(livroDTO.getDisponibilidade());

        Livro livroAtualizado= livroRepository.save(livroExistente);
        return livroMapper.toDTO(livroAtualizado);
    }
}
