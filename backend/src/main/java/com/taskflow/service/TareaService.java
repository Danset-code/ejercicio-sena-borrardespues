package com.taskflow.service;

/*
 * Interfaz que define el contrato del servicio de tareas.
 * Aplica el principio de inversión de dependencias (DIP):
 * el controlador depende de una abstracción, no de una implementación.
 */
import com.taskflow.dto.TareaRequestDto;
import com.taskflow.dto.TareaResponseDto;
import com.taskflow.model.EstadoTarea;
import java.util.List;

/**
 * Interfaz del servicio de gestión de tareas.
 * Define todas las operaciones de negocio disponibles para las tareas.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
public interface TareaService {

    /**
     * Obtiene la lista completa de todas las tareas del sistema.
     * 
     * @return Lista de DTOs con todas las tareas
     */
    List<TareaResponseDto> obtenerTodasLasTareas();

    /**
     * Busca una tarea específica por su identificador único.
     * 
     * @param id Identificador de la tarea a buscar
     * @return DTO con los datos de la tarea encontrada
     * @throws com.taskflow.exception.RecursoNoEncontradoException si no existe
     */
    TareaResponseDto obtenerTareaPorId(Long id);

    /**
     * Crea una nueva tarea en el sistema con estado PENDIENTE.
     * 
     * @param dto DTO con los datos de la nueva tarea
     * @return DTO con la tarea creada (incluye ID generado)
     */
    TareaResponseDto crearTarea(TareaRequestDto dto);

    /**
     * Actualiza los datos de una tarea existente.
     * Solo modifica título, descripción y prioridad (no el estado).
     * 
     * @param id  Identificador de la tarea a actualizar
     * @param dto DTO con los nuevos datos
     * @return DTO con la tarea actualizada
     * @throws com.taskflow.exception.RecursoNoEncontradoException si no existe
     */
    TareaResponseDto actualizarTarea(Long id, TareaRequestDto dto);

    /**
     * Elimina una tarea del sistema por su identificador.
     * 
     * @param id Identificador de la tarea a eliminar
     * @throws com.taskflow.exception.RecursoNoEncontradoException si no existe
     */
    void eliminarTarea(Long id);

    /**
     * Cambia el estado de una tarea (ej: de PENDIENTE a EN_PROGRESO).
     * 
     * @param id     Identificador de la tarea
     * @param nuevoEstado Nuevo estado a asignar
     * @return DTO con la tarea actualizada
     * @throws com.taskflow.exception.RecursoNoEncontradoException si no existe
     */
    TareaResponseDto cambiarEstado(Long id, EstadoTarea nuevoEstado);

    /**
     * Filtra las tareas por un estado específico.
     * 
     * @param estado Estado por el cual filtrar
     * @return Lista de tareas que coinciden con el filtro
     */
    List<TareaResponseDto> filtrarPorEstado(EstadoTarea estado);

    /**
     * Busca tareas cuyo título contenga un texto dado.
     * 
     * @param texto Texto a buscar en los títulos
     * @return Lista de tareas que coinciden
     */
    List<TareaResponseDto> buscarPorTitulo(String texto);
}