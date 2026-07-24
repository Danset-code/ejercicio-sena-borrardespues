package com.cultivo.monitoreo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "configuracion_ambiental")
public class ConfiguracionAmbiental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String variable;
    private Double minimo;
    private Double maximo;
    private String unidad;
    private Boolean alertaActiva = true;

    public ConfiguracionAmbiental() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Double getMinimo() {
        return minimo;
    }

    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }

    public Double getMaximo() {
        return maximo;
    }

    public void setMaximo(Double maximo) {
        this.maximo = maximo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Boolean getAlertaActiva() {
        return alertaActiva;
    }

    public void setAlertaActiva(Boolean alertaActiva) {
        this.alertaActiva = alertaActiva;
    }
}