package com.hotel.service;

import com.hotel.dto.ClienteDTO;
import com.hotel.exception.ClienteNoEncontradoException;

import java.util.List;

/**
 * Interfaz para el servicio de gestión de clientes.
 * Define las operaciones de negocio disponibles para el módulo de clientes.
 */
public interface ClienteService {
    
    /**
     * Crea un nuevo cliente en el sistema.
     * @param clienteDTO Datos del cliente a crear
     * @return El cliente creado
     * @throws IllegalArgumentException Si los datos del cliente no son válidos
     */
    ClienteDTO crearCliente(ClienteDTO clienteDTO);
    
    /**
     * Obtiene un cliente por su ID.
     * @param id ID del cliente a buscar
     * @return El cliente encontrado
     * @throws ClienteNoEncontradoException Si no se encuentra el cliente
     */
    ClienteDTO obtenerClientePorId(String id) throws ClienteNoEncontradoException;
    
    /**
     * Obtiene un cliente por su email.
     * @param email Email del cliente a buscar
     * @return El cliente encontrado
     * @throws ClienteNoEncontradoException Si no se encuentra el cliente
     */
    ClienteDTO obtenerClientePorEmail(String email) throws ClienteNoEncontradoException;
    
    /**
     * Obtiene todos los clientes activos.
     * @return Lista de clientes activos
     */
    List<ClienteDTO> listarTodosLosClientesActivos();
    
    /**
     * Actualiza la información de un cliente existente.
     * @param id ID del cliente a actualizar
     * @param clienteDTO Datos actualizados del cliente
     * @return El cliente actualizado
     * @throws ClienteNoEncontradoException Si no se encuentra el cliente
     */
    ClienteDTO actualizarCliente(String id, ClienteDTO clienteDTO) throws ClienteNoEncontradoException;
    
    /**
     * Elimina un cliente del sistema (eliminación lógica).
     * @param id ID del cliente a eliminar
     * @throws ClienteNoEncontradoException Si no se encuentra el cliente
     */
    void eliminarCliente(String id) throws ClienteNoEncontradoException;
    
    /**
     * Verifica si un cliente con el email dado ya existe.
     * @param email Email a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existeClienteConEmail(String email);
    
    /**
     * Verifica si un cliente con el ID dado existe y está activo.
     * @param id ID del cliente a verificar
     * @return true si existe y está activo, false en caso contrario
     */
    boolean existeClienteActivoPorId(String id);
}
