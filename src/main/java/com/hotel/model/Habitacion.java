package com.hotel.model;

import com.hotel.room.IRoom;

import java.util.Objects;

/**
 * Clase base abstracta que representa una habitación en el hotel.
 * Sigue el principio de Sustitución de Liskov (LSP) al poder ser extendida por subtipos.
 */
public abstract class Habitacion implements IRoom {
    protected String numeroHabitacion;
    protected double precioPorNoche;
    protected int capacidadMaxima;
    protected boolean disponible;
    protected String descripcion;

    public Habitacion(String numeroHabitacion, double precioPorNoche, int capacidadMaxima, String descripcion) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor a cero");
        }
        if (precioPorNoche < 0) {
            throw new IllegalArgumentException("El precio por noche no puede ser negativo");
        }
        this.numeroHabitacion = numeroHabitacion;
        this.precioPorNoche = precioPorNoche;
        this.capacidadMaxima = capacidadMaxima;
        this.disponible = true; // Por defecto, una habitación nueva está disponible
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        if (precioPorNoche < 0) {
            throw new IllegalArgumentException("El precio por noche no puede ser negativo");
        }
        this.precioPorNoche = precioPorNoche;
    }

    @Override
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor a cero");
        }
        this.capacidadMaxima = capacidadMaxima;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos de negocio
    public void marcarComoOcupada() {
        this.disponible = false;
    }

    public void marcarComoDisponible() {
        this.disponible = true;
    }

    // Métodos abstractos que deben ser implementados por las clases hijas
    @Override
    public abstract String getTipo();

    @Override
    public abstract boolean tieneComodidadesBasicas();

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }

    @Override
    public double calcularPrecioBase() {
        return this.precioPorNoche;
    }

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return Objects.equals(numeroHabitacion, that.numeroHabitacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroHabitacion);
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numeroHabitacion='" + numeroHabitacion + '\'' +
                ", tipo='" + getTipo() + '\'' +
                ", precioPorNoche=" + precioPorNoche +
                ", capacidadMaxima=" + capacidadMaxima +
                ", disponible=" + disponible +
                '}';
    }
}
