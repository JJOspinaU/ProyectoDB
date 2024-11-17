package com.example.sistemacitasmedicas.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;
    private String nombre;
    private Date fechaNacimiento;
    private String alergias;
    private String condicionesClinicas;
    private String prioridadAtencion = "Normal";
    private String eps;

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCondicionesClinicas() {
        return condicionesClinicas;
    }

    public void setCondicionesClinicas(String condicionesClinicas) {
        this.condicionesClinicas = condicionesClinicas;
    }

    public String getPrioridadAtencion() {
        return prioridadAtencion;
    }

    public void setPrioridadAtencion(String prioridadAtencion) {
        this.prioridadAtencion = prioridadAtencion;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }
}
