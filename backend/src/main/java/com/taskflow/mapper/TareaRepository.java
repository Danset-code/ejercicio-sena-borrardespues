package com.taskflow.repository;

/*
 * Interfaz de repositorio Spring Data JPA para la entidad Tarea.
 * Provee métodos CRUD automáticos sin necesidad de implementación.
 * Spring genera la implementación en tiempo de ejecución usando el ORM.
 */
import com.taskflow.model.EstadoTarea;
import com.taskflow.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA para operaciones de acceso a datos de la entidad Tarea.
 * JpaRepository<Tarea, Long> proporciona:
 * - save(), findById(), findAll(), deleteById(), etc.
 * La derivación de queries por nombre de método permite crear
 * consultas SQL solo definiendo el nombre del método.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    /**
     * Busca todas las tareas que coincidan con un estado específico.
     * Spring Data JPA traduce esto a: SELECT * FROM tareas WHERE estado = ?
     * 
     * @param estado Estado por el cual filtrar
     * @return Lista de tareas con el estado dado
     */
    List<Tarea> findByEstado(EstadoTarea estado);

    /**
     * Busca tareas cuyo título contenga el texto indicado (búsqueda parcial).
     * Spring Data JPA traduce esto a: SELECT * FROM tareas WHERE titulo LIKE %?%
     * 
     * @param titulo Texto a buscar dentro del título
     * @return Lista de tareas que coinciden con la búsqueda
     */
    List<Tarea> findByTituloContainingIgnoreCase(String titulo);

    /**
     * Busca tareas por estado y las ordena por prioridad descendente.
     * Las de mayor prioridad (3=Alta) aparecen primero.
     * 
     * @param estado Estado por el cual filtrar
     * @return Lista ordenada por prioridad descendente
     */
    List<Tarea> findByEstadoOrderByPrioridadDesc(EstadoTarea estado);
}