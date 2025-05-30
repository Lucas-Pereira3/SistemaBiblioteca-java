package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.dto.MultaDTO;

import com.example.demo.service.MultaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;




@Tag(name = "Multa", description="Endpoints para gerenciamento de multas")
@RestController
@RequestMapping("/api/multa")
public class MultaController {
    @Autowired
    private MultaService multaService;
    
    @Operation(summary = "Cria uma nova multa", description = "Insere uma nova multa ao cliente")
    @PostMapping("/registrar/{emprestimoId}")
    public ResponseEntity<?> registrarMulta(@PathVariable Long emprestimoId){
        return ResponseEntity.ok(multaService.registrarMulta(emprestimoId)); 
    }   
    
    @Operation(summary = "Lista todas as multas")
    @GetMapping
    public ResponseEntity<List<MultaDTO>> ListarMultas(){
        return ResponseEntity.ok(multaService.listarTodos());
    }
    
    @Operation(summary = "Busca uma mulra pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<MultaDTO> buscarporId(@PathVariable Long id){
        Optional<MultaDTO> multa= multaService.multaporId(id);
        return multa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Lista todas as multas que ainda não foram pagas")
    @GetMapping("/Não_Pagos")
    public ResponseEntity<List<MultaDTO>> ListarNaoPagos(){
        List<MultaDTO> naopagos= multaService.ListarNãoPagos();
        return ResponseEntity.ok(naopagos);
    }
}
