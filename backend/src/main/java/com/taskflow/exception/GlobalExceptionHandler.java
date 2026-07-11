package com.taskflow.exception;

/*
 * Manejador global de excepciones de la aplicación.
 * Captura excepciones y las convierte a respuestas HTTP
 * estructuradas con códigos de estado apropiados.
 * Usa @RestControllerAdvice para interceptar todas las excepciones.
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones que captura errores en toda la API
 * y devuelve respuestas JSON con formato consistente.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de recurso no encontrado.
     * Devuelve HTTP 404 Not Found con detalles del error.
     * 
     * @param ex Excepción lanzada cuando no se encuentra un recurso
     * @return Respuesta con estructura de error y código 404
     */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(
            RecursoNoEncontradoException ex) {
        
        Map<String, Object> cuerpoRespuesta = new HashMap<>();
        cuerpoRespuesta.put("timestamp", LocalDateTime.now());
        cuerpoRespuesta.put("estado", HttpStatus.NOT_FOUND.value());
        cuerpoRespuesta.put("error", "Recurso no encontrado");
        cuerpoRespuesta.put("mensaje", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuerpoRespuesta);
    }

    /**
     * Maneja excepciones de validación de datos (@Valid).
     * Devuelve HTTP 400 Bad Request con los campos que fallaron.
     * 
     * @param ex Excepción de validación de argumentos
     * @return Respuesta con lista de errores de cada campo
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacion(
            MethodArgumentNotValidException ex) {
        
        Map<String, Object> cuerpoRespuesta = new HashMap<>();
        cuerpoRespuesta.put("timestamp", LocalDateTime.now());
        cuerpoRespuesta.put("estado", HttpStatus.BAD_REQUEST.value());
        cuerpoRespuesta.put("error", "Error de validación");
        
        // Recopilar errores de cada campo
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        cuerpoRespuesta.put("campos", errores);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cuerpoRespuesta);
    }

    /**
     * Maneja cualquier excepción no capturada específicamente.
     * Devuelve HTTP 500 Internal Server Error.
     * 
     * @param ex Excepción genérica no esperada
     * @return Respuesta genérica de error interno
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarErrorGenerico(Exception ex) {
        
        Map<String, Object> cuerpoRespuesta = new HashMap<>();
        cuerpoRespuesta.put("timestamp", LocalDateTime.now());
        cuerpoRespuesta.put("estado", HttpStatus.INTERNAL_SERVER_ERROR.value());
        cuerpoRespuesta.put("error", "Error interno del servidor");
        cuerpoRespuesta.put("mensaje", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cuerpoRespuesta);
    }
}