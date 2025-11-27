package com.hotel.exception;

/**
 * Excepción lanzada cuando una habitación no está disponible para las fechas solicitadas.
 */
public class HabitacionNoDisponibleException extends RuntimeException {
    
    /**
     * Constructor con un mensaje de error.
     * @param message Mensaje descriptivo del error
     */
    public HabitacionNoDisponibleException(String message) {
        super(message);
    }
    
    /**
     * Constructor con un mensaje de error y una causa.
     * @param message Mensaje descriptivo del error
     * @param cause La causa de la excepción
     */
    public HabitacionNoDisponibleException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor con formato para facilitar la creación de mensajes.
     * @param habitacionId ID de la habitación no disponible
     * @param fechaInicio Fecha de inicio del período no disponible
     * @param fechaFin Fecha de fin del período no disponible
     */
    public HabitacionNoDisponibleException(String habitacionId, String fechaInicio, String fechaFin) {
        super(String.format("La habitación %s no está disponible para las fechas %s a %s", 
                          habitacionId, fechaInicio, fechaFin));
    }
}
