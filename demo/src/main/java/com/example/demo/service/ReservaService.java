package com.example.demo.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cliente;
import com.example.demo.Entities.Livro;
import com.example.demo.Entities.Reserva;
import com.example.demo.Entities.StatusReserva;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.mapper.ReservaMapper;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.LivroRepository;
import com.example.demo.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ReservaMapper reservaMapper;

    public ReservaDTO criarReserva(ReservaDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Livro livro = livroRepository.findById(dto.getLivroId())
            .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livro.getQuantidade() <= 0) {
            throw new RuntimeException("Livro indisponível no estoque");
        }

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setLivro(livro);
        reserva.setDataReserva(LocalDateTime.now());
        reserva.setStatus(StatusReserva.ATIVA);

        reservaRepository.save(reserva);

        // Atualiza o estoque
        livro.setQuantidade(livro.getQuantidade() - 1);
        livroRepository.save(livro);

        return reservaMapper.toDTO(reserva);
    }

    public List<ReservaDTO> listarTodas() {
        return reservaMapper.toDTOList(reservaRepository.findAll());
    }

    public ReservaDTO cancelarReserva(Long reservaId) {
        Reserva reserva = reservaRepository.findById(reservaId)
            .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        if (reserva.getStatus() != StatusReserva.ATIVA) {
            throw new RuntimeException("Apenas reservas ativas podem ser canceladas");
        }

        reserva.setStatus(StatusReserva.CANCELADA);
        reservaRepository.save(reserva);

        // Devolve o livro para o estoque
        Livro livro = reserva.getLivro();
        livro.setQuantidade(livro.getQuantidade() + 1);
        livroRepository.save(livro);

        return reservaMapper.toDTO(reserva);
    }

    public void verificarReservasExpiradas() {
        List<Reserva> todas = reservaRepository.findAll();

        LocalDateTime agora = LocalDateTime.now();
        for (Reserva reserva : todas) {
            if (reserva.getStatus() == StatusReserva.ATIVA &&
                Duration.between(reserva.getDataReserva(), agora).toHours() >= 24) {

                reserva.setStatus(StatusReserva.EXPIRADA);
                reservaRepository.save(reserva);

                // Devolve o livro ao estoque
                Livro livro = reserva.getLivro();
                livro.setQuantidade(livro.getQuantidade() + 1);
                livroRepository.save(livro);
            }
        }
    }

}

