package com.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CheckIn {
    private String id;
    private String reservaId;
    private String habitacionId;
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalidaPrevista;
    private BigDecimal depositoGarantia;
    private List<Huesped> huespedes;
    private String observaciones;
    private EstadoCheckIn estado;
    private int capacidadMaxima;
    private boolean titularRegistrado;

    public CheckIn() {
        this.id = UUID.randomUUID().toString();
        this.fechaHoraEntrada = LocalDateTime.now();
        this.huespedes = new ArrayList<>();
        this.estado = EstadoCheckIn.ACTIVO;
        this.capacidadMaxima = Integer.MAX_VALUE;
        this.titularRegistrado = false;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getReservaId() { return reservaId; }
    public void setReservaId(String reservaId) { this.reservaId = reservaId; }
    public String getHabitacionId() { return habitacionId; }
    public void setHabitacionId(String habitacionId) { this.habitacionId = habitacionId; }
    public LocalDateTime getFechaHoraEntrada() { return fechaHoraEntrada; }
    public void setFechaHoraEntrada(LocalDateTime fechaHoraEntrada) { this.fechaHoraEntrada = fechaHoraEntrada; }
    public LocalDateTime getFechaHoraSalidaPrevista() { return fechaHoraSalidaPrevista; }
    public void setFechaHoraSalidaPrevista(LocalDateTime fechaHoraSalidaPrevista) { this.fechaHoraSalidaPrevista = fechaHoraSalidaPrevista; }
    public BigDecimal getDepositoGarantia() { return depositoGarantia; }
    public void setDepositoGarantia(BigDecimal depositoGarantia) { this.depositoGarantia = depositoGarantia; }
    public List<Huesped> getHuespedes() { return huespedes; }
    public void agregarHuesped(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("El huésped no puede ser nulo");
        }
        if (this.huespedes.size() >= capacidadMaxima) {
            throw new IllegalStateException("No se pueden registrar más huéspedes; se alcanzó la capacidad máxima");
        }
        if (huesped.isTitular()) {
            if (this.titularRegistrado) {
                throw new IllegalStateException("Ya existe un huésped titular para este check-in");
            }
            this.titularRegistrado = true;
        }
        this.huespedes.add(huesped);
    }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public EstadoCheckIn getEstado() { return estado; }
    public void setEstado(EstadoCheckIn estado) { this.estado = estado; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor a cero");
        }
        if (capacidadMaxima < this.huespedes.size()) {
            throw new IllegalStateException("La nueva capacidad no puede ser menor a los huéspedes ya registrados");
        }
        this.capacidadMaxima = capacidadMaxima;
    }
    public boolean isTitularRegistrado() { return titularRegistrado; }

    public enum EstadoCheckIn {
        ACTIVO,
        FINALIZADO,
        CANCELADO
    }
}
