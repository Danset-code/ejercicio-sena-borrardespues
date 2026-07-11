/**
 * Componente de formulario para crear nuevas tareas.
 * Valida los campos antes de enviar al servicio.
 * Usa estados controlados para cada campo del formulario.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
import React, { useState } from 'react';
import '../styles/components.css';

/**
 * TareaFormulario - Formulario para ingresar datos de una nueva tarea.
 * 
 * @param {Object} props - Propiedades del componente
 * @param {Function} props.onCrearTarea - Callback ejecutado al enviar el formulario
 */
function TareaFormulario({ onCrearTarea }) {
  // --- Estados del formulario ---

  /** Título de la nueva tarea */
  const [titulo, setTitulo] = useState('');

  /** Descripción de la nueva tarea */
  const [descripcion, setDescripcion] = useState('');

  /** Prioridad seleccionada (1=Baja, 2=Media, 3=Alta) */
  const [prioridad, setPrioridad] = useState(2);

  /** Mensaje de error de validación local */
  const [errorValidacion, setErrorValidacion] = useState('');

  /**
   * Maneja el envío del formulario.
   * Valida que el título no esté vacío, luego llama al callback padre.
   * 
   * @param {Event} evento - Evento de submit del formulario
   */
  const manejarSubmit = async (evento) => {
    evento.preventDefault();
    setErrorValidacion('');

    // Validación: el título es obligatorio
    if (!titulo.trim()) {
      setErrorValidacion('El título es obligatorio');
      return;
    }

    // Validación: longitud máxima del título
    if (titulo.trim().length > 100) {
      setErrorValidacion('El título no puede exceder 100 caracteres');
      return;
    }

    // Construir objeto con los datos de la tarea
    const tareaDatos = {
      titulo: titulo.trim(),
      descripcion: descripcion.trim(),
      prioridad: parseInt(prioridad)
    };

    // Enviar al componente padre y limpiar formulario
    await onCrearTarea(tareaDatos);
    setTitulo('');
    setDescripcion('');
    setPrioridad(2);
  };

  // --- Renderizado del formulario ---
  return (
    <div className="formulario-card">
      <h2 className="formulario-titulo">Nueva Tarea</h2>
      
      <form onSubmit={manejarSubmit} className="formulario-tarea">
        {/* Campo de título */}
        <div className="form-grupo">
          <label htmlFor="titulo" className="form-label">
            Título *
          </label>
          <input
            type="text"
            id="titulo"
            className="form-input"
            placeholder="Escribe el título de la tarea..."
            value={titulo}
            onChange={(e) => setTitulo(e.target.value)}
            maxLength={100}
          />
        </div>

        {/* Campo de descripción */}
        <div className="form-grupo">
          <label htmlFor="descripcion" className="form-label">
            Descripción
          </label>
          <textarea
            id="descripcion"
            className="form-textarea"
            placeholder="Describe la tarea (opcional)..."
            value={descripcion}
            onChange={(e) => setDescripcion(e.target.value)}
            maxLength={500}
            rows={3}
          />
        </div>

        {/* Selector de prioridad */}
        <div className="form-grupo">
          <label htmlFor="prioridad" className="form-label">
            Prioridad
          </label>
          <select
            id="prioridad"
            className="form-select"
            value={prioridad}
            onChange={(e) => setPrioridad(e.target.value)}
          >
            <option value={1}>Baja</option>
            <option value={2}>Media</option>
            <option value={3}>Alta</option>
          </select>
        </div>

        {/* Mensaje de error de validación */}
        {errorValidacion && (
          <p className="form-error">{errorValidacion}</p>
        )}

        {/* Botón de envío */}
        <button type="submit" className="btn btn-primary">
          Crear Tarea
        </button>
      </form>
    </div>
  );
}

export default TareaFormulario;