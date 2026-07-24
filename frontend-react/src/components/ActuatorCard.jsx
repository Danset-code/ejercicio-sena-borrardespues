import React from 'react';
import { useState } from 'react';
import { actuadorService } from '../services/actuadorService';

export default function ActuatorCard({ actuator, onUpdated }) {
  const [loading,setLoading] = useState(false);
  async function toggle() {
    setLoading(true);
    try { await actuadorService.update(actuator.id,{...actuator,activo:!actuator.activo}); onUpdated(); }
    catch(e){ alert('No fue posible actualizar el actuador.'); }
    finally { setLoading(false); }
  }
  return <div className="card shadow-sm"><div className="card-body">
    <h5>{actuator.nombre}</h5><p>Tipo: {actuator.tipo}</p>
    <p>Estado: <strong>{actuator.activo?'ACTIVO':'INACTIVO'}</strong></p>
    <button className="btn btn-primary" onClick={toggle} disabled={loading}>
      {loading?'Actualizando...':actuator.activo?'Apagar':'Encender'}
    </button>
  </div></div>;
}