package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.dto.EmprestimoDTO;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

    @Mappings({
        @Mapping(source = "cliente.id", target = "clienteId"),
        @Mapping(source = "livro.id", target = "livroId")
    })
    EmprestimoDTO toDTO(Emprestimo emprestimo);

    @Mappings({
        @Mapping(target = "cliente.id", source = "clienteId"),
        @Mapping(target = "livro.id", source = "livroId"),
        
    })
    Emprestimo toEntity(EmprestimoDTO emprestimoDTO);

    List<EmprestimoDTO> toDTOList(List<Emprestimo> emprestimos);
    
}
