package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.EmprestimoDTO;
import com.example.demo.service.ClienteService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @Operation(summary = "Lista todos os clientes")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @Operation(summary = "Busca cliente po ID", description = "Retorna os detalhes de um cliente específico.")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id){
        Optional<ClienteDTO> cliente = clienteService.buscarPorId(id);
        return cliente.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Criar novo cliente", description = "Registra um novo cliente no sistema.")
    @PostMapping
    public ResponseEntity<ApiResponse<ClienteDTO>> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO){
         try {
            ClienteDTO savedCliente = clienteService.salvar(clienteDTO);
            ApiResponse<ClienteDTO>response= new ApiResponse<>(savedCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);  
        } catch (IllegalArgumentException e){
            ErrorResponse errorResponse= new ErrorResponse("Argumento inválido", e.getMessage());
        ApiResponse<ClienteDTO> response= new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary = "Atualiza um cliente", description = "Atualiza os dados de um cliente existente.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteDTO>> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO){
        try{
            ClienteDTO atualizado = clienteService.atualizar(id, clienteDTO);
            return ResponseEntity.ok(new ApiResponse<>(atualizado));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ApiResponse<>(new ErrorResponse("Erro", e.getMessage())));
        }
    }

    @Operation(summary = "Deleta um cliente", description = "Remove um cliente do sistema.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
