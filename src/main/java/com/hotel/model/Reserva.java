package com.hotel.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Clase que representa una reserva en el sistema de hotel.
 * Sigue el principio de responsabilidad única (SRP) al representar únicamente los datos de una reserva.
 */
public class Reserva {
    private String id;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckOut;
    private int numeroHuespedes;
    private EstadoReserva estado;
    private double montoTotal;
    private LocalDate fechaCreacion;
    private String notas;

    /**
     * Enumeración que representa los posibles estados de una reserva.
     */
    public enum EstadoReserva {
        PENDIENTE,
        CONFIRMADA,
        EN_CURSO,
        COMPLETADA,
        CANCELADA,
        NO_SHOW
    }

    /**
     * Constructor para crear una nueva reserva.
     * @param cliente El cliente que realiza la reserva
     * @param habitacion La habitación reservada
     * @param fechaCheckIn Fecha de entrada
     * @param fechaCheckOut Fecha de salida
     * @param numeroHuespedes Número de huéspedes
     * @param notas Notas adicionales de la reserva
     */
    public Reserva(Cliente cliente, Habitacion habitacion, LocalDate fechaCheckIn, 
                  LocalDate fechaCheckOut, int numeroHuespedes, String notas) {
        if (cliente == null || habitacion == null || fechaCheckIn == null || fechaCheckOut == null) {
            throw new IllegalArgumentException("Cliente, habitación y fechas son obligatorios");
        }
        if (fechaCheckIn.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de check-in no puede ser anterior a hoy");
        }
        if (fechaCheckOut.isBefore(fechaCheckIn) || fechaCheckOut.isEqual(fechaCheckIn)) {
            throw new IllegalArgumentException("La fecha de check-out debe ser posterior al check-in");
        }
        if (numeroHuespedes <= 0) {
            throw new IllegalArgumentException("El número de huéspedes debe ser mayor a cero");
        }
        if (numeroHuespedes > habitacion.getCapacidadMaxima()) {
            throw new IllegalArgumentException("El número de huéspedes excede la capacidad de la habitación");
        }
        
        this.id = UUID.randomUUID().toString();
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.numeroHuespedes = numeroHuespedes;
        this.estado = EstadoReserva.PENDIENTE;
        this.fechaCreacion = LocalDate.now();
        this.notas = notas != null ? notas : "";
        this.montoTotal = calcularMontoTotal();
    }

    /**
     * Calcula el monto total de la reserva basado en las noches y el precio por noche.
     * @return El monto total de la reserva
     */
    private double calcularMontoTotal() {
        long noches = java.time.temporal.ChronoUnit.DAYS.between(fechaCheckIn, fechaCheckOut);
        return habitacion.getPrecioPorNoche() * noches;
    }

    /**
     * Confirma la reserva, cambiando su estado a CONFIRMADA.
     * @throws IllegalStateException Si la reserva no está en estado PENDIENTE
     */
    public void confirmar() {
        if (this.estado != EstadoReserva.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden confirmar reservas pendientes");
        }
        this.estado = EstadoReserva.CONFIRMADA;
    }

    /**
     * Cancela la reserva, cambiando su estado a CANCELADA.
     * @param motivo El motivo de la cancelación
     * @throws IllegalStateException Si la reserva ya está en curso, completada o cancelada
     */
    public void cancelar(String motivo) {
        if (this.estado == EstadoReserva.EN_CURSO || this.estado == EstadoReserva.COMPLETADA) {
            throw new IllegalStateException("No se puede cancelar una reserva que ya está en curso o completada");
        }
        if (this.estado == EstadoReserva.CANCELADA) {
            throw new IllegalStateException("La reserva ya está cancelada");
        }
        this.estado = EstadoReserva.CANCELADA;
        this.notas += "\nCancelada: " + (motivo != null ? motivo : "Sin motivo especificado");
    }

    /**
     * Registra el check-in del huésped.
     * @throws IllegalStateException Si la reserva no está confirmada o ya está en curso/completada
     */
    public void registrarCheckIn() {
        if (this.estado != EstadoReserva.CONFIRMADA) {
            throw new IllegalStateException("Solo se puede hacer check-in en reservas confirmadas");
        }
        this.estado = EstadoReserva.EN_CURSO;
        this.fechaCheckIn = LocalDate.now(); // Actualiza la fecha de check-in a la fecha actual
    }

    /**
     * Registra el check-out del huésped.
     * @throws IllegalStateException Si la reserva no está en curso
     */
    public void registrarCheckOut() {
        if (this.estado != EstadoReserva.EN_CURSO) {
            throw new IllegalStateException("Solo se puede hacer check-out de reservas en curso");
        }
        this.estado = EstadoReserva.COMPLETADA;
        this.fechaCheckOut = LocalDate.now(); // Actualiza la fecha de check-out a la fecha actual
        this.montoTotal = calcularMontoTotal(); // Recalcula el monto por si hubo cambios en las fechas
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
        this.montoTotal = calcularMontoTotal(); // Recalcula el monto al cambiar la habitación
    }

    public LocalDate getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(LocalDate fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
        this.montoTotal = calcularMontoTotal(); // Recalcula el monto al cambiar las fechas
    }

    public LocalDate getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(LocalDate fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
        this.montoTotal = calcularMontoTotal(); // Recalcula el monto al cambiar las fechas
    }

    public int getNumeroHuespedes() {
        return numeroHuespedes;
    }

    public void setNumeroHuespedes(int numeroHuespedes) {
        if (numeroHuespedes <= 0) {
            throw new IllegalArgumentException("El número de huéspedes debe ser mayor a cero");
        }
        if (numeroHuespedes > this.habitacion.getCapacidadMaxima()) {
            throw new IllegalArgumentException("El número de huéspedes excede la capacidad de la habitación");
        }
        this.numeroHuespedes = numeroHuespedes;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas != null ? notas : "";
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id='" + id + '\'' +
                ", cliente=" + (cliente != null ? cliente.getNombreCompleto() : "null") +
                ", habitación=" + (habitacion != null ? habitacion.getNumeroHabitacion() : "null") +
                ", checkIn=" + fechaCheckIn +
                ", checkOut=" + fechaCheckOut +
                ", huéspedes=" + numeroHuespedes +
                ", estado=" + estado +
                ", montoTotal=" + montoTotal +
                '}';
    }
}
