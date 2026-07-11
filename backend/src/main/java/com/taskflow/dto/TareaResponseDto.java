package com.taskflow.dto;

/*
 * DTO para enviar datos de tareas al cliente frontend.
 * Controla exactamente qué información se expone en la API.
 */
import com.taskflow.model.EstadoTarea;
import java.time.LocalDateTime;

/**
 * Objeto de transferencia de datos para las respuestas de la API.
 * Contiene la información de la tarea que se envía al frontend.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
public class TareaResponseDto {

    /** Identificador único de la tarea */
    private Long id;

    /** Título de la tarea */
    private String titulo;

    /** Descripción de la tarea */
    private String descripcion;

    /** Estado actual de la tarea */
    private EstadoTarea estado;

    /** Descripción legible del estado */
    private String estadoDescripcion;

    /** Nivel de prioridad */
    private Integer prioridad;

    /** Fecha de creación */
    private LocalDateTime fechaCreacion;

    /** Fecha de última actualización */
    private LocalDateTime fechaActualizacion;

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}