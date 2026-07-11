package com.taskflow.model;

/*
 * Enumerado que define los posibles estados de una tarea.
 * Cada estado representa una fase en el ciclo de vida de la tarea.
 * Se usa UPPER_SNAKE_CASE según el estándar Java para constantes/enum.
 */
public enum EstadoTarea {

    /** La tarea fue creada pero aún no se ha comenzado a trabajar */
    PENDIENTE("Pendiente"),

    /** El usuario está trabajando activamente en la tarea */
    EN_PROGRESO("En Progreso"),

    /** La tarea ha sido completada exitosamente */
    COMPLETADA("Completada"),

    /** La tarea fue cancelada y no se completará */
    CANCELADA("Cancelada");

    /** Descripción legible del estado */
    private final String descripcion;

    /**
     * Constructor del enumerado que recibe la descripción.
     * 
     * @param descripcion Texto descriptivo del estado
     */
    EstadoTarea(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la descripción legible del estado.
     * 
     * @return Descripción del estado
     */
    public String getDescripcion() {
        return descripcion;
    }
}