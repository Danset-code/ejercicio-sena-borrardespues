package com.taskflow.service.impl;

/*
 * Implementación concreta del servicio de tareas.
 * Contiene toda la lógica de negocio: validaciones, transformaciones
 * y orquestación entre el repositorio y los mapeadores.
 * La anotación @Service la registra como bean de Spring.
 */
import com.taskflow.dto.TareaRequestDto;
import com.taskflow.dto.TareaResponseDto;
import com.taskflow.exception.RecursoNoEncontradoException;
import com.taskflow.mapper.TareaMapper;
import com.taskflow.model.EstadoTarea;
import com.taskflow.model.Tarea;
import com.taskflow.repository.TareaRepository;
import com.taskflow.service.TareaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de gestión de tareas.
 * Coordina las operaciones entre el repositorio (datos) y los DTOs (presentación).
 * Usa @Transactional para garantizar la integridad de las operaciones de escritura.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
@Service
@Transactional
public class TareaServiceImpl implements TareaService {

    /** Repositorio inyectado por Spring para acceso a datos */
    private final TareaRepository tareaRepository;

    /**
     * Constructor con inyección de dependencias por constructor
     * (recomendado por Spring sobre @Autowired en campos).
     * 
     * @param tareaRepository Repositorio de tareas inyectado
     */
    public TareaServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    /**
     * {@inheritDoc}
     * Obtiene todas las tareas y las convierte a DTOs usando Stream API.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TareaResponseDto> obtenerTodasLasTareas() {
        return tareaRepository.findAll().stream()
                .map(TareaMapper::aResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     * Busca por ID y lanza excepción personalizada si no existe.
     */
    @Override
    @Transactional(readOnly = true)
    public TareaResponseDto obtenerTareaPorId(Long id) {
        Tarea tarea = buscarTareaPorId(id);
        return TareaMapper.aResponseDto(tarea);
    }

    /**
     * {@inheritDoc}
     * Convierte el DTO a entidad, lo persiste y devuelve el DTO de respuesta.
     */
    @Override
    public TareaResponseDto crearTarea(TareaRequestDto dto) {
        // Convertir DTO a entidad
        Tarea tarea = TareaMapper.aEntidad(dto);
        
        // Persistir en la base de datos (save() devuelve la entidad con ID generado)
        Tarea tareaGuardada = tareaRepository.save(tarea);
        
        // Convertir entidad guardada a DTO de respuesta
        return TareaMapper.aResponseDto(tareaGuardada);
    }

    /**
     * {@inheritDoc}
     * Actualiza solo los campos editables: título, descripción y prioridad.
     * No modifica el estado ni las fechas de creación.
     */
    @Override
    public TareaResponseDto actualizarTarea(Long id, TareaRequestDto dto) {
        // Buscar la tarea existente (lanza excepción si no existe)
        Tarea tarea = buscarTareaPorId(id);
        
        // Actualizar campos permitidos
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        if (dto.getPrioridad() != null) {
            tarea.setPrioridad(dto.getPrioridad());
        }
        
        // Marcar como actualizada y guardar
        tarea.marcarActualizada();
        Tarea tareaActualizada = tareaRepository.save(tarea);
        
        return TareaMapper.aResponseDto(tareaActualizada);
    }

    /**
     * {@inheritDoc}
     * Elimina la tarea de la base de datos por su ID.
     */
    @Override
    public void eliminarTarea(Long id) {
        // Verificar que existe antes de eliminar
        if (!tareaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Tarea", id);
        }
        tareaRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     * Permite transicionar el estado de una tarea.
     * Ejemplo: PENDIENTE -> EN_PROGRESO -> COMPLETADA
     */
    @Override
    public TareaResponseDto cambiarEstado(Long id, EstadoTarea nuevoEstado) {
        Tarea tarea = buscarTareaPorId(id);
        
        // Cambiar el estado
        tarea.setEstado(nuevoEstado);
        tarea.marcarActualizada();
        
        Tarea tareaActualizada = tareaRepository.save(tarea);
        return TareaMapper.aResponseDto(tareaActualizada);
    }

    /**
     * {@inheritDoc}
     * Filtra tareas usando el método derivado del repositorio.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TareaResponseDto> filtrarPorEstado(EstadoTarea estado) {
        return tareaRepository.findByEstadoOrderByPrioridadDesc(estado).stream()
                .map(TareaMapper::aResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     * Búsqueda parcial insensible a mayúsculas/minúsculas.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TareaResponseDto> buscarPorTitulo(String texto) {
        return tareaRepository.findByTituloContainingIgnoreCase(texto).stream()
                .map(TareaMapper::aResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Método privado auxiliar que busca una tarea por ID
     * y lanza una excepción personalizada si no existe.
     * Evita repetir la lógica de búsqueda en múltiples métodos.
     * 
     * @param id Identificador de la tarea
     * @return Entidad Tarea encontrada
     * @throws RecursoNoEncontradoException si la tarea no existe
     */
    private Tarea buscarTareaPorId(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Tarea", id));
    }
}