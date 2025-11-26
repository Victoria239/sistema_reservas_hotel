package com.hotel.dto;

import com.hotel.model.Reserva;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Objeto de Transferencia de Datos (DTO) para la entidad Reserva.
 * Permite exponer solo la información necesaria y ocultar detalles de implementación.
 */
public class ReservaDTO {
    private String id;
    private String clienteId;
    private String clienteNombre;
    private String habitacionId;
    private String habitacionNumero;
    private String tipoHabitacion;
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckOut;
    private int numeroHuespedes;
    private String estado;
    private double montoTotal;
    private LocalDate fechaCreacion;
    private String notas;
    private int noches;
    private double precioPorNoche;

    // Constructor vacío para frameworks
    public ReservaDTO() {
    }

    // Constructor a partir de una entidad Reserva
    public ReservaDTO(Reserva reserva) {
        this.id = reserva.getId();
        this.clienteId = reserva.getCliente() != null ? reserva.getCliente().getId() : null;
        this.clienteNombre = reserva.getCliente() != null ? reserva.getCliente().getNombre() : "";
        this.habitacionId = reserva.getHabitacion().getNumeroHabitacion();
        this.habitacionNumero = reserva.getHabitacion().getNumeroHabitacion();
        this.tipoHabitacion = reserva.getHabitacion().getTipo();
        this.fechaCheckIn = reserva.getFechaCheckIn();
        this.fechaCheckOut = reserva.getFechaCheckOut();
        this.numeroHuespedes = reserva.getNumeroHuespedes();
        this.estado = reserva.getEstado().name();
        this.montoTotal = reserva.getMontoTotal();
        this.fechaCreacion = reserva.getFechaCreacion();
        this.notas = reserva.getNotas();
        this.noches = (int) java.time.temporal.ChronoUnit.DAYS.between(fechaCheckIn, fechaCheckOut);
        this.precioPorNoche = reserva.getHabitacion().getPrecioPorNoche();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(String habitacionId) {
        this.habitacionId = habitacionId;
    }

    public String getHabitacionNumero() {
        return habitacionNumero;
    }

    public void setHabitacionNumero(String habitacionNumero) {
        this.habitacionNumero = habitacionNumero;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public LocalDate getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(LocalDate fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
        if (fechaCheckIn != null && fechaCheckOut != null) {
            this.noches = (int) java.time.temporal.ChronoUnit.DAYS.between(fechaCheckIn, fechaCheckOut);
        }
    }

    public LocalDate getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(LocalDate fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
        if (fechaCheckIn != null && fechaCheckOut != null) {
            this.noches = (int) java.time.temporal.ChronoUnit.DAYS.between(fechaCheckIn, fechaCheckOut);
        }
    }

    public int getNumeroHuespedes() {
        return numeroHuespedes;
    }

    public void setNumeroHuespedes(int numeroHuespedes) {
        this.numeroHuespedes = numeroHuespedes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getNoches() {
        return noches;
    }

    public void setNoches(int noches) {
        this.noches = noches;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservaDTO that = (ReservaDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "id='" + id + '\'' +
                ", clienteId='" + clienteId + '\'' +
                ", clienteNombre='" + clienteNombre + '\'' +
                ", habitacionId='" + habitacionId + '\'' +
                ", habitacionNumero='" + habitacionNumero + '\'' +
                ", tipoHabitacion='" + tipoHabitacion + '\'' +
                ", fechaCheckIn=" + fechaCheckIn +
                ", fechaCheckOut=" + fechaCheckOut +
                ", numeroHuespedes=" + numeroHuespedes +
                ", estado='" + estado + '\'' +
                ", montoTotal=" + montoTotal +
                ", noches=" + noches +
                ", precioPorNoche=" + precioPorNoche +
                '}';
    }
}
