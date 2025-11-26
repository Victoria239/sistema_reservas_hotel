package com.hotel.model;

import java.util.Objects;

/**
 * Clase que representa a un cliente del hotel.
 * Sigue el principio de Responsabilidad Única (SRP) al tener una única razón para cambiar.
 */
public class Cliente {
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private boolean activo;

    // Constructor vacío para JPA/ORM
    public Cliente() {
    }

    public Cliente(String id, String nombre, String email, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.activo = true; // Por defecto, un cliente nuevo está activo
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
    
    /**
     * Obtiene el nombre completo del cliente.
     * @return El nombre completo del cliente
     */
    public String getNombreCompleto() {
        return this.nombre; // En este caso, nombre ya es el nombre completo
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

    // Métodos de negocio
    public void desactivar() {
        this.activo = false;
    }

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) &&
               Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "Cliente{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", email='" + email + '\'' +
               ", activo=" + activo +
               '}';
    }
}
