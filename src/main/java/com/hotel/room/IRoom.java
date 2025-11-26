package com.hotel.room;

/**
 * Interfaz que define el comportamiento común de todas las habitaciones del hotel.
 * Sigue el principio de Segregación de Interfaces (ISP) al definir solo los métodos necesarios.
 */
public interface IRoom {
    
    /**
     * Obtiene el tipo de habitación.
     * @return el tipo de habitación
     */
    String getTipo();
    
    /**
     * Obtiene la capacidad máxima de huéspedes.
     * @return capacidad máxima
     */
    int getCapacidadMaxima();
    
    /**
     * Verifica si la habitación tiene las comodidades básicas.
     * @return true si tiene comodidades básicas, false en caso contrario
     */
    boolean tieneComodidadesBasicas();
    
    /**
     * Obtiene una descripción detallada de la habitación.
     * @return descripción de la habitación
     */
    String getDescripcion();
    
    /**
     * Calcula el precio base de la habitación.
     * @return precio base
     */
    double calcularPrecioBase();
}
