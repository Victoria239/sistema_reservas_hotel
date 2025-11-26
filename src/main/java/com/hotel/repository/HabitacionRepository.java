package com.hotel.repository;

import com.hotel.model.Habitacion;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el repositorio de habitaciones.
 * Proporciona métodos para acceder y manipular datos de habitaciones.
 */
public interface HabitacionRepository {
    
    /**
     * Guarda o actualiza una habitación en la base de datos.
     * @param habitacion La habitación a guardar o actualizar
     * @return La habitación guardada con su ID generado
     */
    Habitacion guardar(Habitacion habitacion);
    
    /**
     * Busca una habitación por su número.
     * @param numeroHabitacion El número de la habitación a buscar
     * @return Un Optional que contiene la habitación si se encuentra, o vacío si no
     */
    Optional<Habitacion> buscarPorNumero(String numeroHabitacion);
    
    /**
     * Obtiene todas las habitaciones disponibles.
     * @return Lista de habitaciones disponibles
     */
    List<Habitacion> listarTodasDisponibles();
    
    /**
     * Obtiene todas las habitaciones de un tipo específico.
     * @param tipo El tipo de habitación a buscar (ej. "ESTANDAR", "SUITE")
     * @return Lista de habitaciones del tipo especificado
     */
    List<Habitacion> listarPorTipo(String tipo);
    
    /**
     * Elimina una habitación por su número.
     * @param numeroHabitacion El número de la habitación a eliminar
     * @return true si se eliminó correctamente, false si no se encontró la habitación
     */
    boolean eliminar(String numeroHabitacion);
    
    /**
     * Verifica si existe una habitación con el número especificado.
     * @param numeroHabitacion El número de habitación a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existePorNumero(String numeroHabitacion);
    
    /**
     * Actualiza la disponibilidad de una habitación.
     * @param numeroHabitacion El número de la habitación a actualizar
     * @param disponible El nuevo estado de disponibilidad
     * @return true si se actualizó correctamente, false si no se encontró la habitación
     */
    boolean actualizarDisponibilidad(String numeroHabitacion, boolean disponible);
    
    /**
     * Busca habitaciones por capacidad máxima.
     * @param capacidad La capacidad mínima requerida
     * @return Lista de habitaciones con capacidad igual o mayor a la especificada
     */
    List<Habitacion> buscarPorCapacidad(int capacidad);
}
