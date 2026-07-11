/**
 * Componente que renderiza la lista de tareas.
 * Muestra un mensaje si no hay tareas, o delega a TareaItem
 * para renderizar cada tarea individual.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
import React from 'react';
import TareaItem from './TareaItem';
import '../styles/components.css';

/**
 * ListaTareas - Contenedor que mapea el array de tareas a componentes TareaItem.
 * 
 * @param {Object} props - Propiedades del componente
 * @param {Array} props.tareas - Lista de tareas a mostrar
 * @param {Function} props.onEliminarTarea - Callback para eliminar una tarea
 * @param {Function} props.onCambiarEstado - Callback para cambiar estado de una tarea
 * @param {Function} props.onActualizarTarea - Callback para actualizar datos de una tarea
 */
function ListaTareas({ tareas, onEliminarTarea, onCambiarEstado, onActualizarTarea }) {
  /**
   * Si no hay tareas, mostrar mensaje indicando que la lista está vacía.
   */
  if (!tareas || tareas.length === 0) {
    return (
      <div className="lista-vacia">
        <span className="lista-vacia-icono">📋</span>
        <p>No hay tareas para mostrar</p>
        <p className="lista-vacia-subtexto">
          Crea una nueva tarea usando el formulario de arriba
        </p>
      </div>
    );
  }

  /**
   * Renderizar cada tarea como un componente TareaItem.
   * Se pasa la key única (id) para que React optimice el re-render.
   */
  return (
    <div className="lista-tareas">
      <h2 className="lista-titulo">
        Tareas ({tareas.length})
      </h2>
      <div className="lista-items">
        {tareas.map((tarea) => (
          <TareaItem
            key={tarea.id}
            tarea={tarea}
            onEliminar={onEliminarTarea}
            onCambiarEstado={onCambiarEstado}
            onActualizar={onActualizarTarea}
          />
        ))}
      </div>
    </div>
  );
}

export default ListaTareas;