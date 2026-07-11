/**
 * Componente de filtros y búsqueda para las tareas.
 * Permite filtrar por estado y buscar por texto en el título.
 * Los filtros se aplican en el backend mediante la API.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
import React from 'react';
import '../styles/components.css';

/**
 * FiltrosTareas - Barra de filtros con selector de estado y campo de búsqueda.
 * 
 * @param {Object} props - Propiedades del componente
 * @param {string} props.filtroEstado - Estado de filtro actual
 * @param {Function} props.onCambiarFiltro - Callback al cambiar el filtro de estado
 * @param {string} props.textoBusqueda - Texto de búsqueda actual
 * @param {Function} props.onCambiarBusqueda - Callback al cambiar el texto de búsqueda
 */
function FiltrosTareas({ filtroEstado, onCambiarFiltro, textoBusqueda, onCambiarBusqueda }) {
  /** Opciones disponibles para el filtro de estado */
  const opcionesEstado = [
    { valor: 'TODAS', etiqueta: 'Todas' },
    { valor: 'PENDIENTE', etiqueta: 'Pendientes' },
    { valor: 'EN_PROGRESO', etiqueta: 'En Progreso' },
    { valor: 'COMPLETADA', etiqueta: 'Completadas' },
    { valor: 'CANCELADA', etiqueta: 'Canceladas' }
  ];

  return (
    <div className="filtros-card">
      {/* Selector de filtro por estado */}
      <div className="filtro-grupo">
        <label htmlFor="filtro-estado" className="filtro-label">
          Filtrar por estado:
        </label>
        <select
          id="filtro-estado"
          className="form-select"
          value={filtroEstado}
          onChange={(e) => onCambiarFiltro(e.target.value)}
        >
          {opcionesEstado.map((opcion) => (
            <option key={opcion.valor} value={opcion.valor}>
              {opcion.etiqueta}
            </option>
          ))}
        </select>
      </div>

      {/* Campo de búsqueda por título */}
      <div className="filtro-grupo">
        <label htmlFor="busqueda" className="filtro-label">
          Buscar:
        </label>
        <input
          type="text"
          id="busqueda"
          className="form-input"
          placeholder="Buscar por título..."
          value={textoBusqueda}
          onChange={(e) => onCambiarBusqueda(e.target.value)}
        />
      </div>
    </div>
  );
}

export default FiltrosTareas;