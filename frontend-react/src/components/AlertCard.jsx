import React from 'react';
export default function AlertCard({ alert }) {
  const color = alert.prioridad === 'ALTA' ? 'danger' : 'warning';
  return <div className={`alert alert-${color}`}>
    <strong>{alert.variable}</strong>: {alert.valor}<br/>
    Prioridad: {alert.prioridad} | Estado: {alert.estado}
  </div>;
}