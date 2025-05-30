package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.service.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Reserva", description="Endpoints para gerenciamento de Reserva")
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Operation(summary = "Criar Reserva")
    @PostMapping
    public ReservaDTO criar(@RequestBody ReservaDTO dto) {
        return reservaService.criarReserva(dto);
    }

    @Operation(summary = "Listar todas as Reservas")
    @GetMapping
    public List<ReservaDTO> listar() {
        return reservaService.listarTodas();
    }

    @Operation(summary = "Cancelar Reserva")
    @PutMapping("/{id}/cancelar")
    public ReservaDTO cancelarReserva(@PathVariable Long id) {
    return reservaService.cancelarReserva(id);
    }

    @Operation(summary = "Executar verificação de reservas expiradas")
    @PutMapping("/verificar-expiradas")
    public void verificarExpiradas() {
    reservaService.verificarReservasExpiradas();
    }

}

