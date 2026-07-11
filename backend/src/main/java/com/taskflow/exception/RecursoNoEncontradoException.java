package com.taskflow.exception;

/*
 * Excepción personalizada para cuando un recurso no se encuentra
 * en la base de datos. Extiende RuntimeException para ser unchecked.
 */
public class RecursoNoEncontradoException extends RuntimeException {

    /** Tipo del recurso no encontrado (ej: "Tarea") */
    private final String tipoRecurso;

    /** ID del recurso que no se encontró */
    private final Long id;

    /**
     * Constructor que crea el mensaje descriptivo automáticamente.
     * 
     * @param tipoRecurso Nombre del tipo de recurso (ej: "Tarea")
     * @param id          Identificador que no fue encontrado
     */
    public RecursoNoEncontradoException(String tipoRecurso, Long id) {
        super(String.format("%s no encontrada con id: %d", tipoRecurso, id));
        this.tipoRecurso = tipoRecurso;
        this.id = id;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public Long getId() {
        return id;
    }
}