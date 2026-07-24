import React from 'react';
import { useEffect,useState } from 'react';
import AlertCard from '../components/AlertCard';
import { alertaService } from '../services/alertaService';

export default function Alerts() {
  const [alerts,setAlerts]=useState([]);
  useEffect(()=>{alertaService.getAll().then(setAlerts).catch(console.error)},[]);
  return <><h1>Alertas ambientales</h1>{alerts.map(a=><AlertCard key={a.id} alert={a}/>)}</>;
}