package com.taskflow.mapper;

/*
 * Clase encargada de convertir entre entidades Tarea y DTOs.
 * Separa la capa de modelo de la capa de presentación.
 * Usa el patrón Mapper para evitar acoplamiento directo.
 */
import com.taskflow.dto.TareaRequestDto;
import com.taskflow.dto.TareaResponseDto;
import com.taskflow.model.EstadoTarea;
import com.taskflow.model.Tarea;

/**
 * Mapeador que convierte entre la entidad Tarea y los DTOs
 * de request y response. Todos los métodos son estáticos
 * ya que es una clase utilitaria sin estado.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
public class TareaMapper {

    /** Constructor privado para evitar instanciación de clase utilitaria */
    private TareaMapper() {
        throw new UnsupportedOperationException("Clase utilitaria, no instanciar");
    }

    /**
     * Convierte un DTO de request a una entidad Tarea.
     * La entidad resultante tiene estado PENDIENTE por defecto.
     * 
     * @param dto DTO con los datos de entrada del cliente
     * @return Entidad Tarea lista para persistir
     */
    public static Tarea aEntidad(TareaRequestDto dto) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setPrioridad(dto.getPrioridad() != null ? dto.getPrioridad() : 2);
        tarea.setEstado(EstadoTarea.PENDIENTE);
        return tarea;
    }

    /**
     * Convierte una entidad Tarea a un DTO de response.
     * Incluye la descripción legible del estado.
     * 
     * @param tarea Entidad obtenida de la base de datos
     * @return DTO de respuesta para enviar al cliente
     */
    public static TareaResponseDto aResponseDto(Tarea tarea) {
        TareaResponseDto dto = new TareaResponseDto();
        dto.setId(tarea.getId());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setEstado(tarea.getEstado());
        dto.setEstadoDescripcion(tarea.getEstado().getDescripcion());
        dto.setPrioridad(tarea.getPrioridad());
        dto.setFechaCreacion(tarea.getFechaCreacion());
        dto.setFechaActualizacion(tarea.getFechaActualizacion());
        return dto;
    }
}