/**
 * Componente que muestra una tarea individual con sus acciones.
 * Permite ver detalles, cambiar estado, editar y eliminar.
 * Soporta modo edición inline para modificar título y descripción.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
import React, { useState } from 'react';
import '../styles/components.css';

/**
 * TareaItem - Tarjeta individual de una tarea con acciones.
 * 
 * @param {Object} props - Propiedades del componente
 * @param {Object} props.tarea - Datos de la tarea a mostrar
 * @param {number} props.tarea.id - Identificador único
 * @param {string} props.tarea.titulo - Título de la tarea
 * @param {string} props.tarea.descripcion - Descripción de la tarea
 * @param {string} props.tarea.estado - Estado actual
 * @param {string} props.tarea.estadoDescripcion - Descripción legible del estado
 * @param {number} props.tarea.prioridad - Nivel de prioridad (1-3)
 * @param {string} props.tarea.fechaCreacion - Fecha de creación
 * @param {Function} props.onEliminar - Callback para eliminar esta tarea
 * @param {Function} props.onCambiarEstado - Callback para cambiar el estado
 * @param {Function} props.onActualizar - Callback para guardar cambios de edición
 */
function TareaItem({ tarea, onEliminar, onCambiarEstado, onActualizar }) {
  /** Indica si el componente está en modo edición */
  const [editando, setEditando] = useState(false);

  /** Campos editables en modo edición */
  const [tituloEditado, setTituloEditado] = useState(tarea.titulo);
  const [descripcionEditada, setDescripcionEditada] = useState(tarea.descripcion);
  const [prioridadEditada, setPrioridadEditada] = useState(tarea.prioridad);

  /**
   * Retorna la clase CSS correspondiente al nivel de prioridad.
   * Se usa para colorear el indicador visual de prioridad.
   * 
   * @param {number} prioridad - Nivel de prioridad (1, 2 o 3)
   * @returns {string} Clase CSS para el badge de prioridad
   */
  const obtenerClasePrioridad = (prioridad) => {
    switch (prioridad) {
      case 3: return 'prioridad-alta';
      case 2: return 'prioridad-media';
      case 1: return 'prioridad-baja';
      default: return 'prioridad-media';
    }
  };

  /**
   * Retorna la clase CSS correspondiente al estado de la tarea.
   * Cada estado tiene un color diferente para identificación visual.
   * 
   * @param {string} estado - Estado de la tarea
   * @returns {string} Clase CSS para el badge de estado
   */
  const obtenerClaseEstado = (estado) => {
    switch (estado) {
      case 'COMPLETADA': return 'estado-completada';
      case 'EN_PROGRESO': return 'estado-en-progreso';
      case 'CANCELADA': return 'estado-cancelada';
      case 'PENDIENTE':
      default: return 'estado-pendiente';
    }
  };

  /**
   * Formatea la fecha ISO a un formato legible en español.
   * 
   * @param {string} fechaIso - Fecha en formato ISO 8601
   * @returns {string} Fecha formateada (ej: "15/03/2025, 10:30")
   */
  const formatearFecha = (fechaIso) => {
    if (!fechaIso) return '';
    const fecha = new Date(fechaIso);
    return fecha.toLocaleDateString('es-ES', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  };

  /**
   * Guarda los cambios realizados en modo edición.
   * Valida el título y llama al callback de actualización.
   */
  const guardarEdicion = () => {
    if (!tituloEditado.trim()) return;
    onActualizar(tarea.id, {
      titulo: tituloEditado.trim(),
      descripcion: descripcionEditada.trim(),
      prioridad: prioridadEditada
    });
    setEditando(false);
  };

  /**
   * Cancela el modo edición y restaura los valores originales.
   */
  const cancelarEdicion = () => {
    setTituloEditado(tarea.titulo);
    setDescripcionEditada(tarea.descripcion);
    setPrioridadEditada(tarea.prioridad);
    setEditando(false);
  };

  // --- Modo Edición ---
  if (editando) {
    return (
      <div className="tarea-card tarea-editando">
        <div className="tarea-edit-campos">
          <input
            type="text"
            className="form-input"
            value={tituloEditado}
            onChange={(e) => setTituloEditado(e.target.value)}
            maxLength={100}
          />
          <textarea
            className="form-textarea"
            value={descripcionEditada}
            onChange={(e) => setDescripcionEditada(e.target.value)}
            maxLength={500}
            rows={2}
          />
          <select
            className="form-select"
            value={prioridadEditada}
            onChange={(e) => setPrioridadEditada(parseInt(e.target.value))}
          >
            <option value={1}>Baja</option>
            <option value={2}>Media</option>
            <option value={3}>Alta</option>
          </select>
        </div>
        <div className="tarea-acciones">
          <button className="btn btn-success btn-sm" onClick={guardarEdicion}>
            Guardar
          </button>
          <button className="btn btn-secondary btn-sm" onClick={cancelarEdicion}>
            Cancelar
          </button>
        </div>
      </div>
    );
  }

  // --- Modo Visualización ---
  return (
    <div className="tarea-card">
      {/* Cabecera con badges de estado y prioridad */}
      <div className="tarea-cabecera">
        <span className={`badge-estado ${obtenerClaseEstado(tarea.estado)}`}>
          {tarea.estadoDescripcion}
        </span>
        <span className={`badge-prioridad ${obtenerClasePrioridad(tarea.prioridad)}`}>
          Prioridad: {tarea.prioridad === 3 ? 'Alta' : tarea.prioridad === 2 ? 'Media' : 'Baja'}
        </span>
      </div>

      {/* Contenido principal de la tarea */}
      <div className="tarea-contenido">
        <h3 className="tarea-titulo">{tarea.titulo}</h3>
        {tarea.descripcion && (
          <p className="tarea-descripcion">{tarea.descripcion}</p>
        )}
      </div>

      {/* Pie con fecha y acciones */}
      <div className="tarea-pie">
        <span className="tarea-fecha">
          Creada: {formatearFecha(tarea.fechaCreacion)}
        </span>
        <div className="tarea-acciones">
          {/* Botón editar */}
          <button
            className="btn btn-warning btn-sm"
            onClick={() => setEditando(true)}
            title="Editar tarea"
          >
            ✏️ Editar
          </button>

          {/* Botón cambiar estado - muestra la siguiente transición lógica */}
          {tarea.estado === 'PENDIENTE' && (
            <button
              className="btn btn-info btn-sm"
              onClick={() => onCambiarEstado(tarea.id, 'EN_PROGRESO')}
              title="Marcar como En Progreso"
            >
              ▶️ Iniciar
            </button>
          )}
          {tarea.estado === 'EN_PROGRESO' && (
            <button
              className="btn btn-success btn-sm"
              onClick={() => onCambiarEstado(tarea.id, 'COMPLETADA')}
              title="Marcar como Completada"
            >
              ✓ Completar
            </button>
          )}
          {(tarea.estado === 'PENDIENTE' || tarea.estado === 'EN_PROGRESO') && (
            <button
              className="btn btn-danger btn-sm"
              onClick={() => onCambiarEstado(tarea.id, 'CANCELADA')}
              title="Cancelar tarea"
            >
              ✕ Cancelar
            </button>
          )}

          {/* Botón eliminar */}
          <button
            className="btn btn-danger btn-sm"
            onClick={() => {
              if (window.confirm('¿Estás seguro de eliminar esta tarea?')) {
                onEliminar(tarea.id);
              }
            }}
            title="Eliminar tarea"
          >
            🗑️ Eliminar
          </button>
        </div>
      </div>
    </div>
  );
}

export default TareaItem;