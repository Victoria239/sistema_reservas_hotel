package com.hotel.dto;

/**
 * Objeto de Transferencia de Datos (DTO) para la entidad Cliente.
 * Se utiliza para transferir datos entre capas sin exponer la entidad completa.
 */
public class ClienteDTO {
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private boolean activo;

    // Constructor vacío para frameworks
    public ClienteDTO() {
    }

    public ClienteDTO(String id, String nombre, String email, String telefono, String direccion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.activo = activo;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", email='" + email + '\'' +
               ", activo=" + activo +
               '}';
    }
}
