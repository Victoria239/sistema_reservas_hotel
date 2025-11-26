package com.hotel.dto;

import com.hotel.model.Habitacion;
import com.hotel.model.HabitacionEstandar;
import com.hotel.model.Suite;

/**
 * Objeto de Transferencia de Datos (DTO) para la entidad Habitación.
 * Permite exponer solo la información necesaria y ocultar detalles de implementación.
 */
public class HabitacionDTO {
    private String numeroHabitacion;
    private String tipo;
    private double precioPorNoche;
    private int capacidadMaxima;
    private boolean disponible;
    private String descripcion;
    
    // Atributos específicos para HabitacionEstandar
    private Boolean tieneVistaExterior;
    private Boolean tieneAireAcondicionado;
    private Boolean tieneCalefaccion;
    
    // Atributos específicos para Suite
    private Boolean tieneJacuzzi;
    private Boolean tieneMinibar;
    private Boolean tieneServicioHabitaciones;
    private Integer numeroHabitaciones;

    // Constructor vacío para frameworks
    public HabitacionDTO() {
    }

    // Constructor a partir de una entidad Habitación
    public HabitacionDTO(Habitacion habitacion) {
        this.numeroHabitacion = habitacion.getNumeroHabitacion();
        this.tipo = habitacion.getTipo();
        this.precioPorNoche = habitacion.getPrecioPorNoche();
        this.capacidadMaxima = habitacion.getCapacidadMaxima();
        this.disponible = habitacion.isDisponible();
        this.descripcion = habitacion.getDescripcion();
        
        // Llenar campos específicos según el tipo de habitación
        if (habitacion instanceof HabitacionEstandar) {
            HabitacionEstandar estandar = (HabitacionEstandar) habitacion;
            this.tieneVistaExterior = estandar.isTieneVistaExterior();
            this.tieneAireAcondicionado = estandar.isTieneAireAcondicionado();
            this.tieneCalefaccion = estandar.isTieneCalefaccion();
        } else if (habitacion instanceof Suite) {
            Suite suite = (Suite) habitacion;
            this.tieneJacuzzi = suite.isTieneJacuzzi();
            this.tieneMinibar = suite.isTieneMinibar();
            this.tieneServicioHabitaciones = suite.isTieneServicioHabitaciones();
            this.numeroHabitaciones = suite.getNumeroHabitaciones();
        }
    }

    // Getters y Setters
    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters para atributos de HabitacionEstandar
    public Boolean getTieneVistaExterior() {
        return tieneVistaExterior;
    }

    public void setTieneVistaExterior(Boolean tieneVistaExterior) {
        this.tieneVistaExterior = tieneVistaExterior;
    }

    public Boolean getTieneAireAcondicionado() {
        return tieneAireAcondicionado;
    }

    public void setTieneAireAcondicionado(Boolean tieneAireAcondicionado) {
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    public Boolean getTieneCalefaccion() {
        return tieneCalefaccion;
    }

    public void setTieneCalefaccion(Boolean tieneCalefaccion) {
        this.tieneCalefaccion = tieneCalefaccion;
    }

    // Getters y Setters para atributos de Suite
    public Boolean getTieneJacuzzi() {
        return tieneJacuzzi;
    }

    public void setTieneJacuzzi(Boolean tieneJacuzzi) {
        this.tieneJacuzzi = tieneJacuzzi;
    }

    public Boolean getTieneMinibar() {
        return tieneMinibar;
    }

    public void setTieneMinibar(Boolean tieneMinibar) {
        this.tieneMinibar = tieneMinibar;
    }

    public Boolean getTieneServicioHabitaciones() {
        return tieneServicioHabitaciones;
    }

    public void setTieneServicioHabitaciones(Boolean tieneServicioHabitaciones) {
        this.tieneServicioHabitaciones = tieneServicioHabitaciones;
    }

    public Integer getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(Integer numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    @Override
    public String toString() {
        return "HabitacionDTO{" +
                "numeroHabitacion='" + numeroHabitacion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precioPorNoche=" + precioPorNoche +
                ", capacidadMaxima=" + capacidadMaxima +
                ", disponible=" + disponible +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
