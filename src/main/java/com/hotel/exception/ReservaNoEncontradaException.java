package com.hotel.exception;

/**
 * Excepción lanzada cuando no se encuentra una reserva en el sistema.
 */
public class ReservaNoEncontradaException extends RuntimeException {
    
    /**
     * Constructor con un mensaje de error.
     * @param message Mensaje descriptivo del error
     */
    public ReservaNoEncontradaException(String message) {
        super(message);
    }
    
    /**
     * Constructor con un mensaje de error y una causa.
     * @param message Mensaje descriptivo del error
     * @param cause La causa de la excepción
     */
    public ReservaNoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor con el ID de la reserva no encontrada.
     * @param reservaId ID de la reserva no encontrada
     */
    public ReservaNoEncontradaException(String reservaId, boolean isId) {
        super(String.format("No se encontró ninguna reserva con el ID: %s", reservaId));
    }
}
