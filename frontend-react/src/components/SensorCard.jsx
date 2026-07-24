import React from 'react';
export default function SensorCard({ name, value, unit }) {
  return <div className="card shadow-sm h-100"><div className="card-body">
    <h5>{name}</h5><div className="display-6">{value ?? '--'} {unit}</div>
    <span className="badge bg-success">Activo</span>
  </div></div>;
}