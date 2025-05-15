package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LivroDTO;
import com.example.demo.service.LivroService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Tag(name ="Livros", description = "Endpoints para gerenciamento de usuario")
@RestController
@RequestMapping("api/livros")
public class LivroController {
@Autowired
private LivroService livroService;

@Operation(summary = "Lista todos os livros")
@GetMapping("/{id}")
public ResponseEntity<List<LivroDTO>> listarLivros(){
    List<LivroDTO> livros = livroService.listarTodos();
    return ResponseEntity.ok(livros);
}

@Operation(summary = "Busca um livro pelo id")
@GetMapping("/{id}")
public ResponseEntity<LivroDTO> buscarPorID(@PathVariable Long id){
    Optional<LivroDTO> livroDTO= livroService.buscarPorID(id);
    return livroDTO.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
}

@Operation(summary= "Cria um novo livro")
@PostMapping()
public ResponseEntity<ApiResponse<LivroDTO>> criarLivro(@Valid @RequestBody LivroDTO livroDTO){
    try{
        //tenta salvar o livro
        LivroDTO savedLivro=livroService.salvar(livroDTO);

        //Retorna com sucesso o LivroDTO salvo
        ApiResponse<LivroDTO>response= new ApiResponse<>(savedLivro);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }catch(IllegalArgumentException e){
        //Cria um erro com a mensagem expecifica
        ErrorResponse errorResponse= new ErrorResponse("Argumento inválido", e.getMessage());
        ApiResponse<LivroDTO> response= new ApiResponse<>(errorResponse);
        return ResponseEntity.badRequest().body(response);
    }catch(Exception e){
        //Cria um erro generico
        ErrorResponse errorResponse=new ErrorResponse("Erro interno", e.getMessage());
        ApiResponse<LivroDTO> response= new ApiResponse<>(errorResponse);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

@Operation(summary = "Deleta um livro")
@DeleteMapping("/{id}")
public ResponseEntity<Void> deletarLivro(@PathVariable Long id){
    livroService.deletar(id);
    return ResponseEntity.noContent().build();
}

}
