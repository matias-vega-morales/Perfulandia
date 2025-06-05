package com.example.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.API_Cliente.Modelo.ClienteModelo;
import com.example.API_Cliente.Modelo.PedidoModelo;
import com.example.Repository.ClienteRepository;
import com.example.Repository.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepo;
    private final PedidoRepository pedidoRepo;

    public ClienteModelo crearCliente(ClienteModelo cliente) {
        return clienteRepo.save(cliente);
    }

    public ClienteModelo obtenerClientePorId(Integer id) {
        return clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    public ClienteModelo actualizarCliente(Integer id, ClienteModelo nuevoCliente) {
        ClienteModelo actual = obtenerClientePorId(id);
        actual.setNombre_completo(nuevoCliente.getNombre_completo());
        actual.setRut(nuevoCliente.getRut());
        actual.setDireccion(nuevoCliente.getDireccion());
        actual.setTelefono(nuevoCliente.getTelefono());
        return clienteRepo.save(actual);
    }

    public List<PedidoModelo> obtenerHistorialDeCompras(Integer idCliente) {
        return pedidoRepo.findByClienteIdCliente(idCliente);
    }
}
