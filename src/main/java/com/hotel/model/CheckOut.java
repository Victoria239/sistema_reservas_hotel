package com.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CheckOut {
    private String id;
    private String checkInId;
    private LocalDateTime fechaHoraSalida;
    private BigDecimal totalEstadia;
    private BigDecimal totalServicios;
    private BigDecimal totalGeneral;
    private String observaciones;
    private String metodoPago;
    private String referenciaPago;
    private EstadoCheckOut estado;

    public CheckOut() {
        this.id = UUID.randomUUID().toString();
        this.fechaHoraSalida = LocalDateTime.now();
        this.totalEstadia = BigDecimal.ZERO;
        this.totalServicios = BigDecimal.ZERO;
        this.totalGeneral = BigDecimal.ZERO;
        this.estado = EstadoCheckOut.PENDIENTE;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getCheckInId() { return checkInId; }
    public void setCheckInId(String checkInId) { this.checkInId = checkInId; }
    public LocalDateTime getFechaHoraSalida() { return fechaHoraSalida; }
    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        if (fechaHoraSalida == null) {
            throw new IllegalArgumentException("La fecha de salida no puede ser nula");
        }
        this.fechaHoraSalida = fechaHoraSalida;
    }
    public BigDecimal getTotalEstadia() { return totalEstadia; }
    public void setTotalEstadia(BigDecimal totalEstadia) {
        this.totalEstadia = validarMontoNoNegativo(totalEstadia, "total de estadía");
        calcularTotal();
    }
    public BigDecimal getTotalServicios() { return totalServicios; }
    public void setTotalServicios(BigDecimal totalServicios) {
        this.totalServicios = validarMontoNoNegativo(totalServicios, "total de servicios");
        calcularTotal();
    }
    public BigDecimal getTotalGeneral() { return totalGeneral; }
    private void setTotalGeneral(BigDecimal totalGeneral) { this.totalGeneral = totalGeneral; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) {
        if (metodoPago == null || metodoPago.isBlank()) {
            throw new IllegalArgumentException("El método de pago es obligatorio");
        }
        this.metodoPago = metodoPago;
    }
    public String getReferenciaPago() { return referenciaPago; }
    public void setReferenciaPago(String referenciaPago) { this.referenciaPago = referenciaPago; }
    public EstadoCheckOut getEstado() { return estado; }
    public boolean estaLiquidado() { return this.estado == EstadoCheckOut.LIQUIDADO; }

    public void calcularTotal() {
        BigDecimal estadia = this.totalEstadia != null ? this.totalEstadia : BigDecimal.ZERO;
        BigDecimal servicios = this.totalServicios != null ? this.totalServicios : BigDecimal.ZERO;
        setTotalGeneral(estadia.add(servicios));
    }

    public void liquidar(BigDecimal totalEstadia, BigDecimal totalServicios, String metodoPago, String referenciaPago) {
        if (estaLiquidado()) {
            throw new IllegalStateException("El check-out ya fue liquidado");
        }
        setTotalEstadia(totalEstadia);
        setTotalServicios(totalServicios);
        setMetodoPago(metodoPago);
        setReferenciaPago(referenciaPago);
        calcularTotal();
        this.estado = EstadoCheckOut.LIQUIDADO;
    }

    private BigDecimal validarMontoNoNegativo(BigDecimal monto, String campo) {
        if (monto == null) {
            throw new IllegalArgumentException("El " + campo + " es obligatorio");
        }
        if (monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El " + campo + " no puede ser negativo");
        }
        return monto;
    }

    public enum EstadoCheckOut {
        PENDIENTE,
        LIQUIDADO
    }
}
