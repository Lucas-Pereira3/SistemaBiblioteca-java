package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.Entities.StatusReserva;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservaDTO {
    private Long id;
    private Long clienteId;
    private Long livroId;
    private LocalDateTime dataReserva;
    private StatusReserva status;
}

