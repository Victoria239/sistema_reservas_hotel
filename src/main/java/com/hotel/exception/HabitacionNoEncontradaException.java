package com.hotel.exception;

/**
 * Excepción lanzada cuando no se encuentra una habitación en el sistema.
 */
public class HabitacionNoEncontradaException extends RuntimeException {
    
    /**
     * Constructor con un mensaje de error.
     * @param message Mensaje descriptivo del error
     */
    public HabitacionNoEncontradaException(String message) {
        super(message);
    }
    
    /**
     * Constructor con un mensaje de error y una causa.
     * @param message Mensaje descriptivo del error
     * @param cause La causa de la excepción
     */
    public HabitacionNoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
