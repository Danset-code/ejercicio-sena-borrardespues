import React from 'react';
import { useEffect,useState } from 'react';
import { configuracionService } from '../services/configuracionService';

export default function Settings() {
  const [items,setItems]=useState([]);
  useEffect(()=>{configuracionService.getAll().then(setItems).catch(console.error)},[]);
  return <><h1>Configuración ambiental</h1><table className="table">
    <thead><tr><th>Variable</th><th>Mínimo</th><th>Máximo</th><th>Unidad</th></tr></thead>
    <tbody>{items.map(i=><tr key={i.id}><td>{i.variable}</td><td>{i.minimo}</td><td>{i.maximo}</td><td>{i.unidad}</td></tr>)}</tbody>
  </table></>;
}