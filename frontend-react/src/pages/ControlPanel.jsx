import React from 'react';
import { useEffect,useState } from 'react';
import ActuatorCard from '../components/ActuatorCard';
import { actuadorService } from '../services/actuadorService';

export default function ControlPanel() {
  const [items,setItems]=useState([]);
  const load=()=>actuadorService.getAll().then(setItems).catch(console.error);
  useEffect(()=>{load()},[]);
  return <><h1>Control de actuadores</h1><div className="row g-3">
    {items.map(a=><div className="col-md-4" key={a.id}><ActuatorCard actuator={a} onUpdated={load}/></div>)}
  </div></>;
}