package com.example.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.API_Cliente.Modelo.ClienteModelo;
import com.example.API_Cliente.Modelo.PedidoModelo;
import com.example.Repository.ClienteRepository;
import com.example.Repository.PedidoRepository;
import com.example.dto.PedidoDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ClienteRepository clienteRepo;

    public List<PedidoDTO> listarPedidosPorCliente(Integer idCliente) {
        return pedidoRepo.findByClienteIdCliente(idCliente).stream()
            .map(p -> new PedidoDTO(p.getIdPedido(), p.getCliente().getId_Cliente(), p.getFechaPedido(), p.getEstado(), p.getMontoTotal()))
            .collect(Collectors.toList());
    }

    public PedidoDTO crearPedido(Integer idCliente, PedidoDTO pedidoDTO) {
        ClienteModelo cliente = clienteRepo.findById(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + idCliente));

        PedidoModelo pedido = new PedidoModelo();
        pedido.setCliente(cliente);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setEstado(pedidoDTO.getEstado());
        pedido.setMontoTotal(pedidoDTO.getMontoTotal());

        PedidoModelo nuevoPedido = pedidoRepo.save(pedido);

        return new PedidoDTO(nuevoPedido.getIdPedido(), cliente.getId_Cliente(), nuevoPedido.getFechaPedido(), nuevoPedido.getEstado(), nuevoPedido.getMontoTotal());
    }

    public PedidoDTO buscarPedidoPorId(Integer idPedido) {
        PedidoModelo pedido = pedidoRepo.findById(idPedido)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + idPedido));

        return new PedidoDTO(pedido.getIdPedido(), pedido.getCliente().getId_Cliente(), pedido.getFechaPedido(), pedido.getEstado(), pedido.getMontoTotal());
    }
}

