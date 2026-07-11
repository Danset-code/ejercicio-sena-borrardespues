/**
 * Componente raíz de la aplicación TaskFlow.
 * Orquesta los componentes de formulario, filtros y lista de tareas.
 * Maneja el estado global de las tareas y las operaciones CRUD.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
import React, { useState, useEffect, useCallback } from 'react';
import TareaFormulario from './components/TareaFormulario';
import ListaTareas from './components/ListarTareas';
import FiltrosTareas from './components/FiltrosTareas';
import { obtenerTareas, crearTarea, eliminarTarea, cambiarEstadoTarea, actualizarTarea } from './services/TareaService';
import './App.css';

/**
 * Componente App - Controlador principal de la interfaz.
 * Gestiona el ciclo de vida de las tareas: carga, creación,
 * actualización, eliminación y filtrado.
 */
function App() {
  // --- Estados del componente ---

  /** Lista de tareas obtenidas del backend */
  const [tareas, setTareas] = useState([]);

  /** Estado de carga para mostrar indicador mientras se obtienen datos */
  const [cargando, setCargando] = useState(true);

  /** Mensaje de error si falla alguna operación */
  const [error, setError] = useState(null);

  /** Filtro de estado activo ('TODAS' o un EstadoTarea específico) */
  const [filtroEstado, setFiltroEstado] = useState('TODAS');

  /** Texto de búsqueda para filtrar por título */
  const [textoBusqueda, setTextoBusqueda] = useState('');

  // --- Efectos ---

  /**
   * Efecto que carga las tareas al montar el componente.
   * Se ejecuta una sola vez gracias al array de dependencias vacío.
   */
  useEffect(() => {
    cargarTareas();
  }, []);

  /**
   * Efecto que recarga las tareas cuando cambian los filtros.
   * Esto permite que el filtrado se haga en el backend.
   */
  useEffect(() => {
    cargarTareas();
  }, [filtroEstado, textoBusqueda]);

  // --- Funciones de negocio ---

  /**
   * Carga las tareas desde el backend aplicando los filtros activos.
   * Maneja errores de conexión y actualiza estados de carga.
   */
  const cargarTareas = useCallback(async () => {
    setCargando(true);
    setError(null);
    try {
      let datos;
      if (textoBusqueda.trim()) {
        // Si hay texto de búsqueda, usar endpoint de búsqueda
        datos = await obtenerTareas(null, textoBusqueda.trim());
      } else if (filtroEstado !== 'TODAS') {
        // Si hay filtro de estado, usar endpoint de filtro
        datos = await obtenerTareas(filtroEstado);
      } else {
        // Sin filtros, obtener todas las tareas
        datos = await obtenerTareas();
      }
      setTareas(datos);
    } catch (err) {
      setError('Error al cargar las tareas. Verifica que el backend esté ejecutándose.');
      console.error('Error al cargar tareas:', err);
    } finally {
      setCargando(false);
    }
  }, [filtroEstado, textoBusqueda]);

  /**
   * Crea una nueva tarea y actualiza la lista.
   * 
   * @param tareaDatos - Objeto con título, descripción y prioridad
   */
  const manejarCrearTarea = async (tareaDatos) => {
    try {
      await crearTarea(tareaDatos);
      await cargarTareas(); // Recargar para obtener el ID generado
    } catch (err) {
      setError('Error al crear la tarea.');
      console.error('Error al crear tarea:', err);
    }
  };

  /**
   * Elimina una tarea por su ID y actualiza la lista.
   * 
   * @param id - Identificador único de la tarea a eliminar
   */
  const manejarEliminarTarea = async (id) => {
    try {
      await eliminarTarea(id);
      await cargarTareas();
    } catch (err) {
      setError('Error al eliminar la tarea.');
      console.error('Error al eliminar tarea:', err);
    }
  };

  /**
   * Cambia el estado de una tarea y actualiza la lista.
   * 
   * @param id - Identificador de la tarea
   * @param nuevoEstado - Nuevo estado a asignar (PENDIENTE, EN_PROGRESO, COMPLETADA, CANCELADA)
   */
  const manejarCambiarEstado = async (id, nuevoEstado) => {
    try {
      await cambiarEstadoTarea(id, nuevoEstado);
      await cargarTareas();
    } catch (err) {
      setError('Error al cambiar el estado de la tarea.');
      console.error('Error al cambiar estado:', err);
    }
  };

  /**
   * Actualiza los datos editables de una tarea.
   * 
   * @param id - Identificador de la tarea
   * @param tareaDatos - Nuevos datos (título, descripción, prioridad)
   */
  const manejarActualizarTarea = async (id, tareaDatos) => {
    try {
      await actualizarTarea(id, tareaDatos);
      await cargarTareas();
    } catch (err) {
      setError('Error al actualizar la tarea.');
      console.error('Error al actualizar tarea:', err);
    }
  };

  // --- Renderizado ---

  return (
    <div className="app-container">
      {/* Encabezado de la aplicación */}
      <header className="app-header">
        <h1>TaskFlow</h1>
        <p>Sistema de Gestión de Tareas</p>
      </header>

      <main className="app-main">
        {/* Formulario para crear nuevas tareas */}
        <section className="app-section">
          <TareaFormulario onCrearTarea={manejarCrearTarea} />
        </section>

        {/* Filtros y búsqueda */}
        <section className="app-section">
          <FiltrosTareas
            filtroEstado={filtroEstado}
            onCambiarFiltro={setFiltroEstado}
            textoBusqueda={textoBusqueda}
            onCambiarBusqueda={setTextoBusqueda}
          />
        </section>

        {/* Mensaje de error si existe */}
        {error && (
          <div className="mensaje-error">
            <span>{error}</span>
            <button onClick={() => setError(null)}>✕</button>
          </div>
        )}

        {/* Lista de tareas con indicador de carga */}
        <section className="app-section">
          {cargando ? (
            <div className="cargando">Cargando tareas...</div>
          ) : (
            <ListaTareas
              tareas={tareas}
              onEliminarTarea={manejarEliminarTarea}
              onCambiarEstado={manejarCambiarEstado}
              onActualizarTarea={manejarActualizarTarea}
            />
          )}
        </section>
      </main>

      {/* Pie de página */}
      <footer className="app-footer">
        <p>TaskFlow v1.0.0 — Spring Boot + React</p>
      </footer>
    </div>
  );
}

export default App;