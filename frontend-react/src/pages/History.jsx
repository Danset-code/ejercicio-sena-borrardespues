import React from 'react';
import { useEffect,useState } from 'react';
import { medicionService } from '../services/medicionService';

export default function History() {
  const [records,setRecords]=useState([]);
  useEffect(()=>{medicionService.getAll().then(setRecords).catch(console.error)},[]);
  return <><h1>Historial de mediciones</h1><table className="table table-bordered">
    <thead><tr><th>ID</th><th>Sensor</th><th>Valor</th><th>Fecha</th></tr></thead>
    <tbody>{records.map(r=><tr key={r.id}><td>{r.id}</td><td>{r.sensor?.nombre}</td><td>{r.valor}</td><td>{r.fecha}</td></tr>)}</tbody>
  </table></>;
}