import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';

export default function Navbar() {
  const navigate = useNavigate();
  return <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
    <div className="container">
      <NavLink className="navbar-brand" to="/dashboard">AgroMonitor</NavLink>
      <div className="navbar-nav">
        <NavLink className="nav-link" to="/dashboard">Dashboard</NavLink>
        <NavLink className="nav-link" to="/monitoreo">Monitoreo</NavLink>
        <NavLink className="nav-link" to="/control">Control</NavLink>
        <NavLink className="nav-link" to="/historial">Historial</NavLink>
        <NavLink className="nav-link" to="/alertas">Alertas</NavLink>
        <NavLink className="nav-link" to="/configuracion">Configuración</NavLink>
        <button className="btn btn-outline-light ms-2" onClick={()=>navigate('/login')}>Salir</button>
      </div>
    </div>
  </nav>;
}