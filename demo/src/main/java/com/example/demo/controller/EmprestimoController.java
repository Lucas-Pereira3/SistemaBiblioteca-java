package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmprestimoDTO;
import com.example.demo.service.EmprestimoService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Empréstimos", description = "Endpoints para gerenciamento de empréstimos de livros")
@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Operation(summary = "Registrar um novo empréstimo")
    @PostMapping
    public ResponseEntity<ApiResponse<EmprestimoDTO>> registrarEmprestimo(@Valid @RequestBody EmprestimoDTO dto) {
        try {
            EmprestimoDTO salvo = emprestimoService.registrarEmprestimo(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(salvo));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(new ErrorResponse("Erro", e.getMessage())));
        }
    }

    @Operation(summary = "Atualizar status do empréstimo")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<EmprestimoDTO>> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            EmprestimoDTO atualizado = emprestimoService.atualizarStatus(id, status);
            return ResponseEntity.ok(new ApiResponse<>(atualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(new ErrorResponse("Erro", e.getMessage())));
        }
    }

    @Operation(summary = "Listar empréstimos por cliente")
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<EmprestimoDTO>> listarPorCliente(@PathVariable Long clienteId) {
        List<EmprestimoDTO> lista = emprestimoService.listarPorCliente(clienteId);
        return ResponseEntity.ok(lista);
    }

    /*@Operation(summary = "Listar empréstimos atrasados")
    @GetMapping("/atrasados")
    public ResponseEntity<List<EmprestimoDTO>> listarAtrasados() {
        //List<EmprestimoDTO> atrasados = emprestimoService.listarAtrasados();
        return ResponseEntity.ok(atrasados);
    }*/

    @Operation(summary = "Registrar devolução de um livro")
    @PatchMapping("/{id}/devolver")
    public ResponseEntity<ApiResponse<EmprestimoDTO>> registrarDevolucao(@PathVariable Long id) {
        try {
            EmprestimoDTO devolvido = emprestimoService.registrarDevolucao(id);
            return ResponseEntity.ok(new ApiResponse<>(devolvido));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(new ErrorResponse("Erro", e.getMessage())));
        }
    }

    @Operation(summary = "Buscar empréstimo por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> buscarPorId(@PathVariable Long id) {
        return emprestimoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
