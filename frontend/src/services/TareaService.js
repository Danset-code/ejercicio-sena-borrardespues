/**
 * Servicio de comunicación con la API REST del backend.
 * Centraliza todas las llamadas HTTP usando Axios.
 * La URL base se configura mediante el proxy de package.json.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
import axios from 'axios';

/** URL base de la API REST de tareas */
const API_URL = '/api/tareas';

/**
 * Obtiene tareas del backend con filtros opcionales.
 * 
 * @param {string} [estado=null] - Filtro por estado (PENDIENTE, EN_PROGRESO, etc.)
 * @param {string} [texto=null] - Texto de búsqueda en títulos
 * @returns {Promise<Array>} Lista de tareas obtenidas del backend
 */
export async function obtenerTareas(estado = null, texto = null) {
  try {
    let respuesta;
    if (texto) {
      // Usar endpoint de búsqueda por título
      respuesta = await axios.get(`${API_URL}/buscar`, {
        params: { texto }
      });
    } else if (estado && estado !== 'TODAS') {
      // Usar endpoint de filtro por estado
      respuesta = await axios.get(`${API_URL}/filtro`, {
        params: { estado }
      });
    } else {
      // Obtener todas las tareas sin filtro
      respuesta = await axios.get(API_URL);
    }
    return respuesta.data;
  } catch (error) {
    console.error('Error en obtenerTareas:', error);
    throw error;
  }
}

/**
 * Crea una nueva tarea en el backend.
 * 
 * @param {Object} tareaDatos - Datos de la tarea a crear
 * @param {string} tareaDatos.titulo - Título de la tarea (obligatorio)
 * @param {string} tareaDatos.descripcion - Descripción opcional
 * @param {number} tareaDatos.prioridad - Prioridad 1=Baja, 2=Media, 3=Alta
 * @returns {Promise<Object>} Tarea creada con ID generado
 */
export async function crearTarea(tareaDatos) {
  try {
    const respuesta = await axios.post(API_URL, tareaDatos);
    return respuesta.data;
  } catch (error) {
    console.error('Error en crearTarea:', error);
    throw error;
  }
}

/**
 * Actualiza una tarea existente en el backend.
 * 
 * @param {number} id - ID de la tarea a actualizar
 * @param {Object} tareaDatos - Nuevos datos para la tarea
 * @returns {Promise<Object>} Tarea actualizada
 */
export async function actualizarTarea(id, tareaDatos) {
  try {
    const respuesta = await axios.put(`${API_URL}/${id}`, tareaDatos);
    return respuesta.data;
  } catch (error) {
    console.error('Error en actualizarTarea:', error);
    throw error;
  }
}

/**
 * Elimina una tarea del backend por su ID.
 * 
 * @param {number} id - ID de la tarea a eliminar
 * @returns {Promise<void>} Respuesta vacía (204 No Content)
 */
export async function eliminarTarea(id) {
  try {
    await axios.delete(`${API_URL}/${id}`);
  } catch (error) {
    console.error('Error en eliminarTarea:', error);
    throw error;
  }
}

/**
 * Cambia el estado de una tarea.
 * Usa el endpoint PATCH para modificación parcial.
 * 
 * @param {number} id - ID de la tarea
 * @param {string} nuevoEstado - Nuevo estado (PENDIENTE, EN_PROGRESO, COMPLETADA, CANCELADA)
 * @returns {Promise<Object>} Tarea con el estado actualizado
 */
export async function cambiarEstadoTarea(id, nuevoEstado) {
  try {
    const respuesta = await axios.patch(`${API_URL}/${id}/estado`, null, {
      params: { nuevoEstado }
    });
    return respuesta.data;
  } catch (error) {
    console.error('Error en cambiarEstadoTarea:', error);
    throw error;
  }
}