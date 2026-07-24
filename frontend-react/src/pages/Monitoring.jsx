import React from 'react';
import { useEffect,useState } from 'react';
import { medicionService } from '../services/medicionService';

export default function Monitoring() {
  const [data,setData]=useState([]);
  useEffect(()=>{medicionService.getAll().then(setData).catch(console.error)},[]);
  return <><h1>Monitoreo ambiental</h1><table className="table table-striped">
    <thead><tr><th>Fecha</th><th>Sensor</th><th>Valor</th></tr></thead>
    <tbody>{data.map(m=><tr key={m.id}><td>{m.fecha}</td><td>{m.sensor?.nombre}</td><td>{m.valor}</td></tr>)}</tbody>
  </table></>;
}