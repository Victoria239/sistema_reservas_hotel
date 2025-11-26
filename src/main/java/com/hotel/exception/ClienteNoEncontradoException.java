package com.hotel.exception;

/**
 * Excepción lanzada cuando no se encuentra un cliente en el sistema.
 */
public class ClienteNoEncontradoException extends RuntimeException {
    
    /**
     * Constructor con un mensaje de error.
     * @param message Mensaje descriptivo del error
     */
    public ClienteNoEncontradoException(String message) {
        super(message);
    }
    
    /**
     * Constructor con un mensaje de error y una causa.
     * @param message Mensaje descriptivo del error
     * @param cause La causa de la excepción
     */
    public ClienteNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
