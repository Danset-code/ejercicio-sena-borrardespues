package com.taskflow.model;

/*
 * Entidad JPA que representa una tarea en el sistema.
 * Mapeada a la tabla "tareas" en la base de datos.
 * Usa @Entity para que Spring Data JPA la reconozca como entidad.
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Clase entidad que modela una tarea del sistema TaskFlow.
 * Cada instancia de esta clase corresponde a un registro
 * en la tabla "tareas" de la base de datos.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
@Entity
@Table(name = "tareas")
public class Tarea {

    /** Identificador único auto-generado de la tarea */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Título de la tarea, obligatorio y máximo 100 caracteres */
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede exceder 100 caracteres")
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    /** Descripción detallada de la tarea, máximo 500 caracteres */
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    @Column(name = "descripcion", length = 500)
    private String descripcion;

    /** Estado actual de la tarea, por defecto PENDIENTE */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoTarea estado = EstadoTarea.PENDIENTE;

    /** Prioridad de la tarea (1=Baja, 2=Media, 3=Alta), por defecto Media */
    @Column(name = "prioridad", nullable = false)
    private Integer prioridad = 2;

    /** Fecha y hora de creación, se establece automáticamente */
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    /** Fecha y hora de última actualización */
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    /**
     * Constructor por defecto requerido por JPA.
     * Inicializa la fecha de creación al momento actual.
     */
    public Tarea() {
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * Constructor completo para crear una tarea con todos sus datos.
     * 
     * @param titulo       Título de la tarea
     * @param descripcion  Descripción detallada
     * @param estado       Estado inicial de la tarea
     * @param prioridad    Nivel de prioridad (1-3)
     */
    public Tarea(String titulo, String descripcion, EstadoTarea estado, Integer prioridad) {
        this();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
    }

    // --- Getters y Setters (estándar camelCase) ---

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

    /**
     * Actualiza la fecha de modificación al momento actual.
     * Se llama automáticamente antes de persistir cambios.
     */
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * Método de conveniencia para marcar la tarea como actualizada.
     * Establece la fecha de actualización al momento actual.
     */
    public void marcarActualizada() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}