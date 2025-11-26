package com.hotel.repository;

import com.hotel.model.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el repositorio de clientes.
 * Sigue el principio de Inversión de Dependencias (DIP) al depender de una abstracción.
 */
public interface ClienteRepository {
    
    /**
     * Guarda un cliente en la base de datos.
     * @param cliente El cliente a guardar
     * @return El cliente guardado con su ID generado
     */
    Cliente guardar(Cliente cliente);
    
    /**
     * Busca un cliente por su ID.
     * @param id El ID del cliente a buscar
     * @return Un Optional que contiene el cliente si se encuentra, o vacío si no
     */
    Optional<Cliente> buscarPorId(String id);
    
    /**
     * Busca un cliente por su email.
     * @param email El email del cliente a buscar
     * @return Un Optional que contiene el cliente si se encuentra, o vacío si no
     */
    Optional<Cliente> buscarPorEmail(String email);
    
    /**
     * Obtiene todos los clientes activos.
     * @return Lista de clientes activos
     */
    List<Cliente> listarTodosActivos();
    
    /**
     * Elimina un cliente por su ID (eliminación lógica).
     * @param id El ID del cliente a eliminar
     * @return true si se eliminó correctamente, false si no se encontró el cliente
     */
    boolean eliminar(String id);
    
    /**
     * Verifica si existe un cliente con el email dado.
     * @param email El email a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existePorEmail(String email);
    
    /**
     * Verifica si existe un cliente con el ID dado.
     * @param id El ID a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existePorId(String id);
}
