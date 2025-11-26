package com.hotel.model;

/**
 * Clase que representa una habitación estándar del hotel.
 * Sigue el principio de Sustitución de Liskov (LSP) al poder ser usada en lugar de su clase base.
 */
public class HabitacionEstandar extends Habitacion {
    private boolean tieneVistaExterior;
    private boolean tieneAireAcondicionado;
    private boolean tieneCalefaccion;

    public HabitacionEstandar(String numeroHabitacion, double precioPorNoche, int capacidadMaxima, 
                             String descripcion, boolean tieneVistaExterior, 
                             boolean tieneAireAcondicionado, boolean tieneCalefaccion) {
        super(numeroHabitacion, precioPorNoche, capacidadMaxima, descripcion);
        this.tieneVistaExterior = tieneVistaExterior;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCalefaccion = tieneCalefaccion;
    }

    // Implementación de los métodos abstractos
    @Override
    public String getTipo() {
        return "ESTANDAR";
    }

    @Override
    public boolean tieneComodidadesBasicas() {
        return tieneAireAcondicionado && tieneCalefaccion;
    }

    // Getters y Setters específicos
    public boolean isTieneVistaExterior() {
        return tieneVistaExterior;
    }

    public void setTieneVistaExterior(boolean tieneVistaExterior) {
        this.tieneVistaExterior = tieneVistaExterior;
    }

    public boolean isTieneAireAcondicionado() {
        return tieneAireAcondicionado;
    }

    public void setTieneAireAcondicionado(boolean tieneAireAcondicionado) {
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    public boolean isTieneCalefaccion() {
        return tieneCalefaccion;
    }

    public void setTieneCalefaccion(boolean tieneCalefaccion) {
        this.tieneCalefaccion = tieneCalefaccion;
    }

    // Sobrescribir el método toString para incluir información adicional
    @Override
    public String toString() {
        return "HabitacionEstandar{" +
                super.toString() +
                ", tieneVistaExterior=" + tieneVistaExterior +
                ", tieneAireAcondicionado=" + tieneAireAcondicionado +
                ", tieneCalefaccion=" + tieneCalefaccion +
                '}';
    }
}
