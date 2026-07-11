package com.taskflow.controller;

/*
 * Controlador REST que expone los endpoints de la API de tareas.
 * Maneja las peticiones HTTP (GET, POST, PUT, DELETE) y delega
 * la lógica de negocio al servicio correspondiente.
 * Usa @RestController para que cada método devuelva JSON directamente.
 */
import com.taskflow.dto.TareaRequestDto;
import com.taskflow.dto.TareaResponseDto;
import com.taskflow.model.EstadoTarea;
import com.taskflow.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las operaciones CRUD sobre tareas.
 * Mapea las rutas bajo /api/tareas y sigue las convenciones
 * de RESTful API para los métodos HTTP.
 * 
 * Endpoints disponibles:
 * - GET    /api/tareas          -> Listar todas
 * - GET    /api/tareas/{id}     -> Buscar por ID
 * - POST   /api/tareas          -> Crear nueva
 * - PUT    /api/tareas/{id}     -> Actualizar existente
 * - DELETE /api/tareas/{id}     -> Eliminar
 * - PATCH  /api/tareas/{id}/estado -> Cambiar estado
 * - GET    /api/tareas/filtro?estado=XXX -> Filtrar por estado
 * - GET    /api/tareas/buscar?texto=XXX  -> Buscar por título
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*") // Permite peticiones desde React en desarrollo
public class TareaController {

    /** Servicio inyectado por Spring con la lógica de negocio */
    private final TareaService tareaService;

    /**
     * Constructor con inyección de dependencias.
     * 
     * @param tareaService Servicio de tareas a inyectar
     */
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    /**
     * Endpoint GET /api/tareas - Obtiene todas las tareas.
     * 
     * @return ResponseEntity con lista de tareas y código 200 OK
     */
    @GetMapping
    public ResponseEntity<List<TareaResponseDto>> obtenerTodas() {
        List<TareaResponseDto> tareas = tareaService.obtenerTodasLasTareas();
        return ResponseEntity.ok(tareas);
    }

    /**
     * Endpoint GET /api/tareas/{id} - Obtiene una tarea por su ID.
     * 
     * @param id Identificador único de la tarea
     * @return ResponseEntity con la tarea encontrada o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<TareaResponseDto> obtenerPorId(@PathVariable Long id) {
        TareaResponseDto tarea = tareaService.obtenerTareaPorId(id);
        return ResponseEntity.ok(tarea);
    }

    /**
     * Endpoint POST /api/tareas - Crea una nueva tarea.
     * @Valid activa la validación del DTO antes de pasar al servicio.
     * 
     * @param dto Datos de la nueva tarea (validados)
     * @return ResponseEntity con la tarea creada y código 201 Created
     */
    @PostMapping
    public ResponseEntity<TareaResponseDto> crearTarea(
            @Valid @RequestBody TareaRequestDto dto) {
        TareaResponseDto tareaCreada = tareaService.crearTarea(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaCreada);
    }

    /**
     * Endpoint PUT /api/tareas/{id} - Actualiza una tarea existente.
     * 
     * @param id  Identificador de la tarea a actualizar
     * @param dto Nuevos datos para la tarea
     * @return ResponseEntity con la tarea actualizada y código 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<TareaResponseDto> actualizarTarea(
            @PathVariable Long id,
            @Valid @RequestBody TareaRequestDto dto) {
        TareaResponseDto tareaActualizada = tareaService.actualizarTarea(id, dto);
        return ResponseEntity.ok(tareaActualizada);
    }

    /**
     * Endpoint DELETE /api/tareas/{id} - Elimina una tarea.
     * 
     * @param id Identificador de la tarea a eliminar
     * @return ResponseEntity con código 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint PATCH /api/tareas/{id}/estado - Cambia el estado de una tarea.
     * Se usa PATCH porque es una modificación parcial del recurso.
     * 
     * @param id         Identificador de la tarea
     * @param nuevoEstado Nuevo estado a asignar (enviado como parámetro)
     * @return ResponseEntity con la tarea actualizada
     */
    @PatchMapping("/{id}/estado")
    public ResponseEntity<TareaResponseDto> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoTarea nuevoEstado) {
        TareaResponseDto tareaActualizada = tareaService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(tareaActualizada);
    }

    /**
     * Endpoint GET /api/tareas/filtro - Filtra tareas por estado.
     * 
     * @param estado Estado por el cual filtrar (parámetro de consulta)
     * @return ResponseEntity con lista de tareas filtradas
     */
    @GetMapping("/filtro")
    public ResponseEntity<List<TareaResponseDto>> filtrarPorEstado(
            @RequestParam EstadoTarea estado) {
        List<TareaResponseDto> tareas = tareaService.filtrarPorEstado(estado);
        return ResponseEntity.ok(tareas);
    }

    /**
     * Endpoint GET /api/tareas/buscar - Busca tareas por texto en título.
     * 
     * @param texto Texto a buscar (parámetro de consulta)
     * @return ResponseEntity con lista de tareas que coinciden
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<TareaResponseDto>> buscarPorTitulo(
            @RequestParam String texto) {
        List<TareaResponseDto> tareas = tareaService.buscarPorTitulo(texto);
        return ResponseEntity.ok(tareas);
    }
}