package com.application.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table (name = "persona")
@EqualsAndHashCode(of = {"id"})
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    public Persona(Integer id, String nombre, String telefono, String nacimiento, String ciudad, String localidad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.nacimiento = nacimiento;
        this.ciudad = ciudad;
        this.localidad = localidad;
        this.rol = Rol.Usuario;
        this.email = email;
    }

    private String telefono;

    @OneToMany(mappedBy = "autorizado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autorizacion> autorizaciones;

    private String nacimiento;
    private String ciudad;
    private String localidad;
    private String foto;
    public Persona() {}
    private String password;

    private Rol rol = Rol.Usuario;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    private void prePersist() {
     //   this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public Persona(Integer id, String nombre, String telefono, String ciudad, String localidad, String nacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.localidad = localidad;
        this.foto = foto;
        this.nacimiento = nacimiento;
    }


    public Persona(Integer id, String nombre, String ciudad, String telefono, String localidad) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean getActive() {
        return true;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
