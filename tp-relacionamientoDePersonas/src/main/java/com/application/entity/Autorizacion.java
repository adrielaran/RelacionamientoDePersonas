package com.application.entity;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "autorizacion")
public class Autorizacion {
    public Autorizacion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Autorizacion(Integer id, Persona autorizante, Persona autorizado, Estado estado) {
        this.id = id;
        this.autorizante = autorizante;
        this.autorizado = autorizado;
        this.estado = estado;
    }

    public Persona getAutorizante() {
        return autorizante;
    }

    public void setAutorizante(Persona autorizante) {
        this.autorizante = autorizante;
    }

    public Persona getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Persona autorizado) {
        this.autorizado = autorizado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "autorizante_ID")
    private Persona autorizante;
    @ManyToOne
    @JoinColumn(name = "autorizado_ID")
    private Persona autorizado;
    @Column
    private Estado estado;

    public Autorizacion(Persona autorizante, Persona autorizado, Estado estado) {
        this.autorizante = autorizante;
        this.autorizado = autorizado;
        this.estado = estado;
    }
}
