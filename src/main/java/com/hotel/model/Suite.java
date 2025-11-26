package com.hotel.model;

/**
 * Clase que representa una suite de lujo en el hotel.
 * Sigue el principio de Sustitución de Liskov (LSP) al poder ser usada en lugar de su clase base.
 */
public class Suite extends Habitacion {
    private boolean tieneJacuzzi;
    private boolean tieneMinibar;
    private boolean tieneServicioHabitaciones;
    private int numeroHabitaciones;

    public Suite(String numeroHabitacion, double precioPorNoche, int capacidadMaxima, 
                String descripcion, boolean tieneJacuzzi, boolean tieneMinibar, 
                boolean tieneServicioHabitaciones, int numeroHabitaciones) {
        super(numeroHabitacion, precioPorNoche, capacidadMaxima, descripcion);
        this.tieneJacuzzi = tieneJacuzzi;
        this.tieneMinibar = tieneMinibar;
        this.tieneServicioHabitaciones = tieneServicioHabitaciones;
        this.numeroHabitaciones = numeroHabitaciones;
    }

    // Implementación de los métodos abstractos
    @Override
    public String getTipo() {
        return "SUITE";
    }

    @Override
    public boolean tieneComodidadesBasicas() {
        return tieneJacuzzi && tieneMinibar && tieneServicioHabitaciones;
    }

    // Sobrescribir el método para incluir comodidades adicionales
    @Override
    public double calcularPrecioBase() {
        double precioBase = super.calcularPrecioBase();
        // Añadir recargos por comodidades adicionales
        if (tieneJacuzzi) {
            precioBase += 50.0; // Recargo por jacuzzi
        }
        if (tieneServicioHabitaciones) {
            precioBase += 30.0; // Recargo por servicio a la habitación
        }
        if (numeroHabitaciones > 1) {
            precioBase += (numeroHabitaciones - 1) * 40.0; // Recargo por habitaciones adicionales
        }
        return precioBase;
    }

    // Getters y Setters específicos
    public boolean isTieneJacuzzi() {
        return tieneJacuzzi;
    }

    public void setTieneJacuzzi(boolean tieneJacuzzi) {
        this.tieneJacuzzi = tieneJacuzzi;
    }

    public boolean isTieneMinibar() {
        return tieneMinibar;
    }

    public void setTieneMinibar(boolean tieneMinibar) {
        this.tieneMinibar = tieneMinibar;
    }

    public boolean isTieneServicioHabitaciones() {
        return tieneServicioHabitaciones;
    }

    public void setTieneServicioHabitaciones(boolean tieneServicioHabitaciones) {
        this.tieneServicioHabitaciones = tieneServicioHabitaciones;
    }

    public int getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(int numeroHabitaciones) {
        if (numeroHabitaciones < 1) {
            throw new IllegalArgumentException("El número de habitaciones debe ser al menos 1");
        }
        this.numeroHabitaciones = numeroHabitaciones;
    }

    // Método específico de la suite
    public void solicitarServicioHabitacion(String servicio) {
        if (!tieneServicioHabitaciones) {
            throw new IllegalStateException("Esta suite no tiene servicio a la habitación");
        }
        System.out.println("Solicitando servicio de habitación: " + servicio);
    }

    // Sobrescribir el método toString para incluir información adicional
    @Override
    public String toString() {
        return "Suite{" +
                super.toString() +
                ", tieneJacuzzi=" + tieneJacuzzi +
                ", tieneMinibar=" + tieneMinibar +
                ", tieneServicioHabitaciones=" + tieneServicioHabitaciones +
                ", numeroHabitaciones=" + numeroHabitaciones +
                '}';
    }
}
