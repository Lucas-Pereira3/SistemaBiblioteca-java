package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.Entities.Reserva;
import com.example.demo.dto.ReservaDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "livro.id", target = "livroId")
    ReservaDTO toDTO(Reserva reserva);

    @Mapping(source = "clienteId", target = "cliente.id")
    @Mapping(source = "livroId", target = "livro.id")
    Reserva toEntity(ReservaDTO dto);

    List<ReservaDTO> toDTOList(List<Reserva> reservas);
}