package com.hotel.service.impl;

import com.hotel.dto.ClienteDTO;
import com.hotel.exception.ClienteNoEncontradoException;
import com.hotel.model.Cliente;
import com.hotel.repository.ClienteRepository;
import com.hotel.service.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de gestión de clientes.
 * Sigue el principio de Responsabilidad Única (SRP) al tener una única razón para cambiar.
 */
@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        // Validar que el email no esté en uso
        if (clienteRepository.existePorEmail(clienteDTO.getEmail())) {
            throw new IllegalArgumentException("Ya existe un cliente con el email: " + clienteDTO.getEmail());
        }

        // Convertir DTO a entidad
        Cliente cliente = new Cliente(
                UUID.randomUUID().toString(),
                clienteDTO.getNombre(),
                clienteDTO.getEmail(),
                clienteDTO.getTelefono(),
                clienteDTO.getDireccion()
        );

        // Guardar en la base de datos
        Cliente clienteGuardado = clienteRepository.guardar(cliente);
        
        // Convertir entidad a DTO y retornar
        return convertirADTO(clienteGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO obtenerClientePorId(String id) throws ClienteNoEncontradoException {
        return clienteRepository.buscarPorId(id)
                .map(this::convertirADTO)
                .orElseThrow(() -> new ClienteNoEncontradoException("No se encontró el cliente con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO obtenerClientePorEmail(String email) throws ClienteNoEncontradoException {
        return clienteRepository.buscarPorEmail(email)
                .map(this::convertirADTO)
                .orElseThrow(() -> new ClienteNoEncontradoException("No se encontró el cliente con email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> listarTodosLosClientesActivos() {
        return clienteRepository.listarTodosActivos().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO actualizarCliente(String id, ClienteDTO clienteDTO) throws ClienteNoEncontradoException {
        // Verificar que el cliente existe
        Cliente clienteExistente = clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("No se puede actualizar. Cliente no encontrado con ID: " + id));
        
        // Verificar si el nuevo email ya está en uso por otro cliente
        if (!clienteExistente.getEmail().equals(clienteDTO.getEmail()) && 
            clienteRepository.existePorEmail(clienteDTO.getEmail())) {
            throw new IllegalArgumentException("Ya existe otro cliente con el email: " + clienteDTO.getEmail());
        }
        
        // Actualizar datos del cliente
        clienteExistente.setNombre(clienteDTO.getNombre());
        clienteExistente.setEmail(clienteDTO.getEmail());
        clienteExistente.setTelefono(clienteDTO.getTelefono());
        clienteExistente.setDireccion(clienteDTO.getDireccion());
        
        // Guardar cambios
        Cliente clienteActualizado = clienteRepository.guardar(clienteExistente);
        return convertirADTO(clienteActualizado);
    }

    @Override
    public void eliminarCliente(String id) throws ClienteNoEncontradoException {
        // Verificar que el cliente existe
        Cliente cliente = clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("No se puede eliminar. Cliente no encontrado con ID: " + id));
        
        // Eliminación lógica
        cliente.desactivar();
        clienteRepository.guardar(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeClienteConEmail(String email) {
        return clienteRepository.existePorEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeClienteActivoPorId(String id) {
        return clienteRepository.buscarPorId(id)
                .map(Cliente::isActivo)
                .orElse(false);
    }

    // Método auxiliar para convertir entidad a DTO
    private ClienteDTO convertirADTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        BeanUtils.copyProperties(cliente, dto);
        return dto;
    }
}
