package com.taskflow.dto;

/*
 * DTO (Data Transfer Object) para recibir datos de creación/edición
 * de tareas desde el cliente frontend. Separa la entrada de la entidad.
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Objeto de transferencia de datos para las peticiones de creación
 * y actualización de tareas. Validado con anotaciones de Jakarta Validation.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
public class TareaRequestDto {

    /** Título de la tarea (obligatorio, máximo 100 caracteres) */
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede exceder 100 caracteres")
    private String titulo;

    /** Descripción opcional de la tarea (máximo 500 caracteres) */
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    /** Prioridad de la tarea: 1=Baja, 2=Media (por defecto), 3=Alta */
    private Integer prioridad = 2;

    // --- Getters y Setters ---

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

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }
}