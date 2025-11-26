package com.hotel.service;

import com.hotel.dto.ReservaDTO;
import com.hotel.exception.HabitacionNoDisponibleException;
import com.hotel.exception.HabitacionNoEncontradaException;
import com.hotel.exception.ClienteNoEncontradoException;
import com.hotel.exception.ReservaNoEncontradaException;
import com.hotel.model.Reserva.EstadoReserva;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz del servicio para la gestión de reservas.
 * Define las operaciones disponibles para el manejo de reservas en el sistema.
 */
public interface ReservaService {

    /**
     * Crea una nueva reserva en el sistema.
     *
     * @param reservaDTO DTO con los datos de la reserva a crear
     * @return El DTO de la reserva creada
     * @throws HabitacionNoDisponibleException Si la habitación no está disponible para las fechas indicadas
     * @throws HabitacionNoEncontradaException Si la habitación no existe
     * @throws ClienteNoEncontradoException Si el cliente no existe
     */
    ReservaDTO crearReserva(ReservaDTO reservaDTO) 
        throws HabitacionNoDisponibleException, HabitacionNoEncontradaException, ClienteNoEncontradoException;

    /**
     * Busca una reserva por su ID.
     *
     * @param id ID de la reserva a buscar
     * @return El DTO de la reserva encontrada
     * @throws ReservaNoEncontradaException Si la reserva no existe
     */
    ReservaDTO buscarPorId(String id) throws ReservaNoEncontradaException;

    /**
     * Actualiza una reserva existente.
     *
     * @param id ID de la reserva a actualizar
     * @param reservaDTO DTO con los nuevos datos de la reserva
     * @return El DTO de la reserva actualizada
     * @throws ReservaNoEncontradaException Si la reserva no existe
     * @throws HabitacionNoDisponibleException Si la habitación no está disponible para las nuevas fechas
     */
    ReservaDTO actualizarReserva(String id, ReservaDTO reservaDTO) 
        throws ReservaNoEncontradaException, HabitacionNoDisponibleException;

    /**
     * Cancela una reserva existente.
     *
     * @param id ID de la reserva a cancelar
     * @param motivo Motivo de la cancelación
     * @throws ReservaNoEncontradaException Si la reserva no existe
     * @throws IllegalStateException Si la reserva no puede ser cancelada (ya está completada o cancelada)
     */
    void cancelarReserva(String id, String motivo) throws ReservaNoEncontradaException;

    /**
     * Obtiene todas las reservas de un cliente.
     *
     * @param clienteId ID del cliente
     * @return Lista de DTOs de reservas del cliente
     */
    List<ReservaDTO> listarReservasPorCliente(String clienteId);

    /**
     * Obtiene todas las reservas de una habitación.
     *
     * @param habitacionId ID de la habitación
     * @return Lista de DTOs de reservas de la habitación
     */
    List<ReservaDTO> listarReservasPorHabitacion(String habitacionId);

    /**
     * Verifica la disponibilidad de una habitación en un rango de fechas.
     *
     * @param habitacionId ID de la habitación
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return true si la habitación está disponible, false en caso contrario
     */
    boolean verificarDisponibilidad(String habitacionId, LocalDate fechaInicio, LocalDate fechaFin);

    /**
     * Registra el check-in de una reserva.
     *
     * @param id ID de la reserva
     * @return El DTO de la reserva actualizada
     * @throws ReservaNoEncontradaException Si la reserva no existe
     * @throws IllegalStateException Si la reserva no está confirmada
     */
    ReservaDTO registrarCheckIn(String id) throws ReservaNoEncontradaException;

    /**
     * Registra el check-out de una reserva.
     *
     * @param id ID de la reserva
     * @return El DTO de la reserva actualizada
     * @throws ReservaNoEncontradaException Si la reserva no existe
     * @throws IllegalStateException Si la reserva no está en curso
     */
    ReservaDTO registrarCheckOut(String id) throws ReservaNoEncontradaException;

    /**
     * Obtiene todas las reservas en un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de DTOs de reservas en el rango de fechas
     */
    List<ReservaDTO> listarReservasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);

    /**
     * Cambia el estado de una reserva.
     *
     * @param id ID de la reserva
     * @param nuevoEstado Nuevo estado de la reserva
     * @return El DTO de la reserva actualizada
     * @throws ReservaNoEncontradaException Si la reserva no existe
     * @throws IllegalStateException Si el cambio de estado no es válido
     */
    ReservaDTO cambiarEstadoReserva(String id, EstadoReserva nuevoEstado) throws ReservaNoEncontradaException;
}
